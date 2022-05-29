package com.planner.model;

public class LoginRespBean {
	private String userName;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LoginRespBean(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	public LoginRespBean() {
		// TODO Auto-generated constructor stub
	}


	
}
