package com.lavalloisir.beans;

import java.sql.Timestamp;

public class User {

    private long id;
    private String email;
    private String password;
    private String name;
    private String givenName;
    private String phone;
    private String picture;
    private Timestamp registration;
    private Timestamp lastConnection;
    
    public User() {
		super();
    	this.id = 0;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName.substring(0, 1).toUpperCase() + givenName.substring(1);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getRegistration() {
		return registration;
	}

	public void setRegistration(Timestamp registration) {
		this.registration = registration;
	}

	public Timestamp getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Timestamp lastConnection) {
		this.lastConnection= lastConnection;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
