package com.planner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.planner.model.LoginBean;
import com.planner.utils.JDBCUtils;

public class LoginDao {

	public int validate(LoginBean loginBean) throws ClassNotFoundException {
		int id = 0;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from Users where Email = ? and Pwd = ? ")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		}
		return id;
	}
}
