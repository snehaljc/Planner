package com.planner.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.planner.model.EventsBean;
import com.planner.utils.JDBCUtils;

public class eventsDao {
	private static final String INSERT_EVENT_QUEERY = "insert into Events (Name, LocationId, CreatedBy, Address, DateTime, Description, Image, Fee, Public) values (?,?,?,?,?,?,?,?,?);";
	private static final String GET_ALL_LOCATIONS_QUEERY = "select id, CONCAT(city, ' - ', area) as location from locations;";
	private static final String GET_ALL_USERS_QUEERY = "select id, CONCAT(Fname, ' ', Lname) as user from users;";
	public int addEvent(EventsBean eb, InputStream photo, List<String> users) {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_EVENT_QUEERY)) {
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
			   long userid = rs.getLong(1);
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return result;
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
}
