package com.kumar.mis.app.shared.domain;

import java.util.UUID;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CustomerEntity implements IsSerializable {

	private String customerName ="";
	private String customerEmail="";
	private String contactNumber="";
	private String addressLine1="";
	private String addressLine2="";
	private String city="";
	private String country="";
	private String state="";
	@Id
	private String id;

	public CustomerEntity() {

	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CustomerEntity [customerName=" + customerName
				+ ", customerEmail=" + customerEmail + ", contactNumber="
				+ contactNumber + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", country=" + country + ", state=" + state + ", id=" + id
				+ "]";
	}

}
