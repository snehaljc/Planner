package com.planner.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.cj.jdbc.Blob;
import com.planner.model.EventsBean;
import com.planner.model.UsersBean;
import com.planner.utils.JDBCUtils;
import java.util.stream.Collectors;
public class eventsDao {
	private static final String INSERT_EVENT_QUEERY = "insert into Events (Name, LocationId, CreatedBy, Address, DateTime, Description, Image, Fee, Public) values (?,?,?,?,?,?,?,?,?);";
	private static final String UPDATE_EVENT_QUEERY = "update Events set Name=?, Address=?, DateTime=?, Description=?, Fee=?, LastModifiedOn=now() where id=?;";
	private static final String INSERT_USERS_QUERY = "insert into eventinvites (EventId, UserId) values ";
	private static final String DELETE_USERS_QUERY = "delete from eventinvites where EventId=?";
	private static final String GET_EVENT_BY_ID = "SELECT E.id, E.Name, E.Address, E.DateTime, L.City, E.Description, E.Fee, E.Public from Events E INNER JOIN Locations L ON E.LocationId = L.id where E.id = ?;";
	private static final String GET_ALL_LOCATIONS_QUEERY = "select id, CONCAT(city, ' - ', area) as location from locations;";
	private static final String GET_ALL_USERS_QUEERY = "select id, CONCAT(Fname, ' ', Lname) as user from users;";
	private static final String GET_ALL_USERS_JSON_QUEERY = "select id, CONCAT(Fname, ' ', Lname) as user, email from users;";
	private static final String GET_ALL_INVITED_USERS_FOR_EDIT = "select GROUP_CONCAT(U.id) as id from USERS U INNER JOIN eventinvites E on U.id = E.UserId where EventId=?;";
	private static final String GET_ALL_INVITED_USERS_BYID_QUERY = "select CONCAT(U.Fname, ' ', U.Lname) as user, U.email, E.Accepted from USERS U INNER JOIN eventinvites E on U.id = E.UserId where EventId=?;";
	private static final String GET_EVENT_BY_ID_QUERY = "SELECT E.Name, CONCAT(L.city, '-', L.area) as Location, E.Description, E.Image, E.Address, E.DateTime, E.Description, E.Fee, E.Public, E.CreatedOn, E.LastModifiedOn from Events E INNER JOIN Locations L ON E.LocationId = L.id where E.id = ?;";
	public int addEvent(EventsBean eb, InputStream photo, List<String> users) {
		int result = 0;
		Long eventid = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_EVENT_QUEERY, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, eb.getName());
			preparedStatement.setInt(3, eb.getCreatedBy());
			preparedStatement.setInt(2, eb.getCity());
			preparedStatement.setString(4, eb.getAddress());
			preparedStatement.setObject(5, eb.getDatetime());
			preparedStatement.setString(6, eb.getDescription());
			preparedStatement.setBlob(7, photo);
			preparedStatement.setInt(8, eb.getFee());
			preparedStatement.setBoolean(9, eb.getIspublic());
			result = preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
			   eventid = rs.getLong(1);
			}
			
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		
		List<String> qryparam = new ArrayList<String>();
		if(eventid != null && users != null) {
			for (String uid : users) {
				qryparam.add("(" + eventid + "," + uid + ")");
			}
			try (Connection connection = JDBCUtils.getConnection();
					PreparedStatement preparedStatement = connection
							.prepareStatement(INSERT_USERS_QUERY + qryparam.stream().collect(
						            Collectors.joining(","))+";")) {
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				JDBCUtils.printSQLException(e);
			}
		}
		return result;
	}
	
	public int updateEvent(EventsBean eb, List<String> users, Integer eventid) {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(UPDATE_EVENT_QUEERY)) {
			preparedStatement.setString(1, eb.getName());
			preparedStatement.setString(2, eb.getAddress());
			preparedStatement.setObject(3, eb.getDatetime());
			preparedStatement.setString(4, eb.getDescription());
			preparedStatement.setInt(5, eb.getFee());
			preparedStatement.setInt(6, eventid);
			result = preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_USERS_QUERY)) {
			preparedStatement.setInt(1, eventid);
		    preparedStatement.executeUpdate();
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		
		List<String> qryparam = new ArrayList<String>();
		if(eventid != null && users != null) {
			for (String uid : users) {
				qryparam.add("(" + eventid + "," + uid + ")");
			}
			try (Connection connection = JDBCUtils.getConnection();
					PreparedStatement preparedStatement = connection
							.prepareStatement(INSERT_USERS_QUERY + qryparam.stream().collect(
						            Collectors.joining(","))+";")) {
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				JDBCUtils.printSQLException(e);
			}
		}
		return result;
	}
	
	public EventsBean getEventByIdForEdit(Integer id) {
		EventsBean eb = new EventsBean();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_EVENT_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				eb.setName(result.getString("Name"));
				//eb.setLocation(result.getString("Location"));
				eb.setAddress(result.getString("Address"));
				eb.setDatetime(result.getTimestamp("DateTime"));
				eb.setDescription(result.getString("Description"));
				eb.setFee(result.getInt("Fee"));
				eb.setIspublic(result.getBoolean("Public"));
				}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return eb;
	}
	
	public HashMap<Integer, String> getAllLocations() {
		HashMap<Integer, String> locations = new HashMap<Integer, String>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_ALL_LOCATIONS_QUEERY)) {
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				locations.put(result.getInt("id"), result.getString("location"));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return locations;
	}
	
	public HashMap<Integer, String> getAllUsers() {
		HashMap<Integer, String> users = new HashMap<Integer, String>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_ALL_USERS_QUEERY)) {
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				users.put(result.getInt("id"), result.getString("user"));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return users;
	}
	
	public ArrayList<UsersBean> getAllUsersForJson() {
		ArrayList<UsersBean> users = new ArrayList<UsersBean>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_ALL_USERS_JSON_QUEERY)) {
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				users.add(new UsersBean(result.getString("user"), result.getString("email"), String.valueOf(result.getInt("id"))));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return users;
	}
	
	public ArrayList<UsersBean> getAllInvitedUsers(Integer id) {
		ArrayList<UsersBean> users = new ArrayList<UsersBean>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_ALL_INVITED_USERS_BYID_QUERY)) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				users.add(new UsersBean(result.getString("user"), result.getString("email"), result.getBoolean("Accepted")));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return users;
	}
	
	public String getAllInvitedUsersForEdit(Integer id) {
		String users = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_ALL_INVITED_USERS_FOR_EDIT)) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				users = result.getString("id");
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return users;
	}
	
	public EventsBean getEventById(Integer id) throws IOException {
		EventsBean eb = new EventsBean();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_EVENT_BY_ID_QUERY)) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
			eb.setName(result.getString("Name"));
			eb.setLocation(result.getString("Location"));
			eb.setAddress(result.getString("Address"));
			eb.setDatetime(result.getTimestamp("DateTime"));
			eb.setDescription(result.getString("Description"));
			eb.setImage(DashDao.blobToString((Blob) result.getBlob("Image")));
			eb.setFee(result.getInt("Fee"));
			eb.setIspublic(result.getBoolean("Public"));
			eb.setCreatedOn(result.getTimestamp("CreatedOn"));
			eb.setModifiedOn(result.getTimestamp("LastModifiedOn"));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return eb;
	}
}
