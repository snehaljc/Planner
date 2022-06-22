package com.planner.model;

import java.util.HashMap;


public class NewEventParamBean {
HashMap<Integer, String> locations;
HashMap<Integer, String> users;
public HashMap<Integer, String> getLocations() {
	return locations;
}
public void setLocations(HashMap<Integer, String> locations) {
	this.locations = locations;
}
public HashMap<Integer, String> getUsers() {
	return users;
}
public void setUsers(HashMap<Integer, String> users) {
	this.users = users;
}
public NewEventParamBean(HashMap<Integer, String> locations, HashMap<Integer, String> users) {
	super();
	this.locations = locations;
	this.users = users;
}

}
