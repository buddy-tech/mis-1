package com.kumar.mis.app.shared.form;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class QuotationRequestForm implements IsSerializable {

	String name;

	Date dateCreated;
	Date dateUpdated;
	Date requestDate;
	Date responseDate;
	String description;
	String requestMode;
	String customerId;
	Long id;

	String status = "inprogress";

	public QuotationRequestForm() {

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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "QuotationRequestForm [dateCreated=" + dateCreated
				+ ", dateUpdated=" + dateUpdated + ", requestDate="
				+ requestDate + ", responseDate=" + responseDate
				+ ", description=" + description + ", requestMode="
				+ requestMode + ", customerId=" + customerId + ", id=" + id
				+ "]";
	}

}
