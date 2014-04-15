package com.kumar.mis.app.shared.domain;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * This will hold the part details
 * 
 * @author Kumar Jeyaprakasam
 *
 */

public class Parts implements IsSerializable {
	@Id
	Long id;

	String partName = "";
	String partDescription = "";
	double price;

	public Parts() {

	}

	public Parts(String partName, String partDescription, double price) {
		super();
		this.partName = partName;
		this.partDescription = partDescription;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Parts [id=" + id + ", partName=" + partName
				+ ", partDescription=" + partDescription + ", price=" + price
				+ "]";
	}

}
