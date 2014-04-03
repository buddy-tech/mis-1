package com.kumar.mis.app.client.component.customer;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import com.kumar.mis.app.shared.domain.CustomerEntity;

public class CustomerGrid extends Composite {

	private static CustomerGridUiBinder uiBinder = GWT
			.create(CustomerGridUiBinder.class);

	interface CustomerGridUiBinder extends UiBinder<Widget, CustomerGrid> {
	}


	public CustomerGrid() {

		

		initWidget(uiBinder.createAndBindUi(this));
	}

	private List<Column<CustomerEntity, String>> buildColums() {

		List<Column<CustomerEntity, String>> columns = new ArrayList<Column<CustomerEntity, String>>();

		// customer name

		columns.add(new Column<CustomerEntity, String>(new TextCell()) {

			@Override
			public String getValue(CustomerEntity ce) {
				// TODO Auto-generated method stub
				return ce.getCustomerName();
			}
		});

		columns.add(new Column<CustomerEntity, String>(new TextCell()) {

			@Override
			public String getValue(CustomerEntity ce) {
				// TODO Auto-generated method stub
				return ce.getCustomerEmail();
			}
		});

		columns.add(new Column<CustomerEntity, String>(new TextCell()) {

			@Override
			public String getValue(CustomerEntity ce) {
				// TODO Auto-generated method stub
				return ce.getContactNumber();
			}
		});
		
		
		
		return columns;

	}
	
	


	private List<Header<String>> buildHeaders() {
		List<Header<String>> headers = new ArrayList<Header<String>>();

		headers.add(new Header<String>(new TextCell()) {
			@Override
			public String getValue() {
				return "Customer Name";
			}
		});
		headers.add(new Header<String>(new TextCell()) {
			@Override
			public String getValue() {
				return "Customer Email";
			}
		});
		headers.add(new Header<String>(new TextCell()) {
			@Override
			public String getValue() {
				return "Customer Number";
			}
		});

		return headers;
	}
}
