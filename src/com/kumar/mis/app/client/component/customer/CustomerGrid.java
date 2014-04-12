package com.kumar.mis.app.client.component.customer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CustomerGrid extends Composite {

	private static CustomerGridUiBinder uiBinder = GWT
			.create(CustomerGridUiBinder.class);

	interface CustomerGridUiBinder extends UiBinder<Widget, CustomerGrid> {
	}

	public CustomerGrid() {

		initWidget(uiBinder.createAndBindUi(this));
	}

}
