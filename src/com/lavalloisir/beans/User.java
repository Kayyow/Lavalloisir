package com.lavalloisir.beans;

import java.sql.Timestamp;

public class User {

    private final long id;
    private String email;
    private String password;
    private String name;
    private String givenName;
    private String phone;
    private String picture;
    private Timestamp registrationDate;
    private Timestamp lastConnectionDate;
    
    public User(long id) {
		super();
		this.id = id;
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
		this.name = name;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
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

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Timestamp getLastConnectionDate() {
		return lastConnectionDate;
	}

	public void setLastConnectionDate(Timestamp lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}

	public long getId() {
		return id;
	}
}
