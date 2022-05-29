package com.planner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.planner.model.DashEventsBean;
import com.planner.utils.JDBCUtils;

public class DashDao {
	private static final String DASHBOARD_EVENTS_BY_USER = "SELECT E.id, U.Fname , E.Name, L.City, E.Description from Events E INNER JOIN Locations L ON E.LocationId = L.id INNER JOIN Users U ON U.id = E.CreatedBy WHERE E.CreatedBy = ?";
	
	public List<DashEventsBean> getAllDashboardEventsByUser(int userId) {
		List<DashEventsBean> events = new ArrayList<>();
		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(DASHBOARD_EVENTS_BY_USER)) {
			preparedStatement.setInt(1, userId);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				events.add(new DashEventsBean(rs.getString("Name"), rs.getString("City"), rs.getString("Description"), rs.getInt("id")));
			}

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		}
		for(DashEventsBean eve: events) {
		    System.out.println(eve);  // Will invoke overrided `toString()` method
		}
		return events;
	}
}
