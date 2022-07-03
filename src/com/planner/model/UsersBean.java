package com.planner.model;

public class UsersBean {
private Integer id;
private String user, email, sid;
private boolean accepted;
public String getSid() {
	return sid;
}
public void setSid(String sid) {
	this.sid = sid;
}
public UsersBean(String user, String email, boolean accepted) {
	super();
	this.user = user;
	this.email = email;
	this.accepted = accepted;
}
public boolean isAccepted() {
	return accepted;
}
public void setAccepted(boolean accepted) {
	this.accepted = accepted;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getEmail() {
	return email;
}
public UsersBean(String user, String email, String sid) {
	super();
	this.user = user;
	this.email = email;
	this.sid = sid;
}
public void setEmail(String email) {
	this.email = email;
}
public UsersBean(Integer id, String user, String email) {
	super();
	this.id = id;
	this.user = user;
	this.email = email;
}


}
