package org.jsp.ecommaraceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private int id;
@Column(nullable = true) 
private int housenumber;
@Column(nullable = false)
private String landmark,buildingname,city,state,country;
@Column(nullable = false)
private long pincode;

@ManyToOne
@JsonIgnore
@JoinColumn
private User user;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getLandmark() {
	return landmark;
}

public void setLandmark(String landmark) {
	this.landmark = landmark;
}

public String getBuildingname() {
	return buildingname;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public void setBuildingname(String buildingname) {
	this.buildingname = buildingname;
}

public int getHousenumber() {
	return housenumber;
}

public void setHousenumber(int housenumber) {
	this.housenumber = housenumber;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public long getPincode() {
	return pincode;
}

public void setPincode(long pincode) {
	this.pincode = pincode;
}

}
