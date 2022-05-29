package com.planner.model;

public class DashEventsBean {
	private String name;
	private String city;
	private String description;
	private int eventId;
	public DashEventsBean(String name, String city, String description, int eventId) {
		super();
		this.name = name;
		this.city = city;
		this.description = description;
		this.eventId = eventId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	@Override
	public String toString() {
		return "DashEventsBean [name=" + name + ", city=" + city + ", description=" + description + ", eventId="
				+ eventId + "]";
	}

	
}
