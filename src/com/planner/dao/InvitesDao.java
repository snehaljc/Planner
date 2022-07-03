package com.planner.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Blob;
import com.planner.model.DashEventsBean;
import com.planner.utils.JDBCUtils;

public class InvitesDao {
	private static final String GET_INVITITION_LIST_BY_UID = "SELECT E.id, E.Name, L.City, E.Description, E.Image from Events E INNER JOIN Locations L ON E.LocationId = L.id where E.id in (select EventId from eventinvites where UserId = ? and accepted=0);";
	private static final String ACCEPT_INVITE = "update eventinvites set accepted=1 where EventId=? and UserId=?;";

	public List<DashEventsBean> getAllInvitedEventsByUser(int userId) throws IOException {
		List<DashEventsBean> events = new ArrayList<>();
		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_INVITITION_LIST_BY_UID)) {
			preparedStatement.setInt(1, userId);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			Blob blob = null;
			while(rs.next()) {
				blob = (Blob) rs.getBlob("Image");
				events.add(new DashEventsBean(rs.getString("Name"), DashDao.blobToString(blob), rs.getString("City"), rs.getString("Description"), rs.getInt("id")));
			}

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		}
		return events;
	}
	
	public Integer accepteInvite(Integer userId, Integer eventId) throws IOException {
		Integer rs = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(ACCEPT_INVITE)) {
			preparedStatement.setInt(2, userId);
			preparedStatement.setInt(1, eventId);
			System.out.println(preparedStatement);
			rs = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		}
		return rs;
	}
}
