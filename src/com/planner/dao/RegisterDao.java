package com.planner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.planner.model.RegisterBean;
import com.planner.utils.JDBCUtils;

public class RegisterDao {
	
	public int RegisterUser(RegisterBean registerBean) {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into Users (Fname, Lname, Email, Pwd) values (?, ?, ?, ?);")) {
			preparedStatement.setString(1, registerBean.getFname());
			preparedStatement.setString(2, registerBean.getLname());
			preparedStatement.setString(3, registerBean.getEmail());
			preparedStatement.setString(4, registerBean.getPassword());
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
}
