package com.planner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.planner.model.LoginBean;
import com.planner.model.LoginRespBean;
import com.planner.utils.JDBCUtils;

public class LoginDao {

	public LoginRespBean validate(LoginBean loginBean) throws ClassNotFoundException {
		LoginRespBean resp = null;
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from Users where Email = ? and Pwd = ? ")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				resp = new LoginRespBean(rs.getInt("id"), rs.getString("Fname"));
			}

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		}
		return resp;
	}
}
