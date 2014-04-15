package com.kumar.mis.app.shared.domain;

import java.util.Date;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;

public class QuotationRequest implements IsSerializable {

	@Id
	Long id;

	CustomerEntity customer;

	Date dateCreated;
	Date dateUpdated;
	Date requestDate;
	Date responseDate;
	String description;
	String requestMode;

	public QuotationRequest() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestMode() {
		return requestMode;
	}

	public void setRequestMode(String requestMode) {
		this.requestMode = requestMode;
	}

	@Override
	public String toString() {
		return "QuotationRequest [id=" + id + ", customer=" + customer
				+ ", dateCreated=" + dateCreated + ", dateUpdated="
				+ dateUpdated + ", requestDate=" + requestDate
				+ ", responseDate=" + responseDate + ", description="
				+ description + ", requestMode=" + requestMode + "]";
	}

}
