package com.lavalloisir.beans;

public class Leisure {
	
	private long id;
    private String title;
    private String description;
    private String email;
    private String phone;
    private String displayPhone;
    private String website;
    private Address address;
    private Category category;

	public Leisure() {
		super();
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
		
		String displayPhone = "";
		for (int i=1; i<=5; i++) {
			displayPhone += phone.substring((i-1) * 2, 2*i) + ".";
		}
		this.setDisplayPhone(displayPhone.substring(0, displayPhone.length()-1));
	}
	
	public String getDisplayPhone() {
		return displayPhone;
	}
	
	private void setDisplayPhone(String displayPhone) {
		this.displayPhone = displayPhone;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
