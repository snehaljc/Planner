package com.planner.model;

import java.util.Date;
import java.util.List;

public class EventsBean {
	private String name, address, description, image;
	private Integer fee, createdBy, city;
	private List<String> invitedusers;
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	private Boolean ispublic;
	public List<String> getInvitedusers() {
		return invitedusers;
	}
	public void setInvitedusers(List<String> invitedusers) {
		this.invitedusers = invitedusers;
	}
	private Date datetime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public Boolean getIspublic() {
		return ispublic;
	}
	public void setIspublic(Boolean ispublic) {
		this.ispublic = ispublic;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public EventsBean(String name, Integer city, String address, String description, String image, Integer fee,
			Boolean ispublic, Date datetime) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.description = description;
		this.image = image;
		this.fee = fee;
		this.ispublic = ispublic;
		this.datetime = datetime;
	}
	public EventsBean() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	public String toString() {
		return "EventsBean [name=" + name + ", city=" + city + ", address=" + address + ", description=" + description
				+ ", fee=" + fee + ", ispublic=" + ispublic + ", datetime=" + datetime + "]";
	}
	
}
