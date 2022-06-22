package com.planner.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.mysql.cj.jdbc.Blob;
import com.planner.model.DashEventsBean;
import com.planner.utils.JDBCUtils;

public class DashDao {
	private static final String DASHBOARD_EVENTS_BY_USER = "SELECT E.id, U.Fname , E.Name, L.City, E.Description, E.Image from Events E INNER JOIN Locations L ON E.LocationId = L.id INNER JOIN Users U ON U.id = E.CreatedBy WHERE E.CreatedBy = ?";
	
	private static final String DELETE_EVENTS_BY_ID = "delete from Events where id = ?";
	public List<DashEventsBean> getAllDashboardEventsByUser(int userId) throws IOException {
		List<DashEventsBean> events = new ArrayList<>();
		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(DASHBOARD_EVENTS_BY_USER)) {
			preparedStatement.setInt(1, userId);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			Blob blob = null;
			while(rs.next()) {
				blob = (Blob) rs.getBlob("Image");
				events.add(new DashEventsBean(rs.getString("Name"), blobToString(blob), rs.getString("City"), rs.getString("Description"), rs.getInt("id")));
			}

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		}
//		for(DashEventsBean eve: events) {
//		    System.out.println(eve);  // Will invoke overrided `toString()` method
//		}
		return events;
	}
	
	public int deleteEventById(int eventId) {
		int rs = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_EVENTS_BY_ID)) {
			preparedStatement.setInt(1, eventId);

			System.out.println(preparedStatement);
			rs = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		}
		return rs;
	}
	
	public String blobToString(Blob blob) throws SQLException, IOException {	
		if(blob == null) {
			return null;
		}
		InputStream inputStream = blob.getBinaryStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		 
		while ((bytesRead = inputStream.read(buffer)) != -1) {
		    outputStream.write(buffer, 0, bytesRead);
		}
		 
		byte[] imageBytes = outputStream.toByteArray();
		 
		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		 
		inputStream.close();
		outputStream.close();
		return base64Image;
	}
}

