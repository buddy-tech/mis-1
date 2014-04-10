package com.kumar.mis.app.client.component.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.kumar.mis.app.client.component.common.SuccessAlert;
import com.kumar.mis.app.client.component.customer.grid.CustomerGridGxt;
import com.kumar.mis.app.shared.common.LoggerMessage;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.kumar.mis.app.shared.service.CustomerService;
import com.kumar.mis.app.shared.service.CustomerServiceAsync;
import com.sencha.gxt.widget.core.client.info.Info;

public class Customer extends Composite {

	private static CustomerUiBinder uiBinder = GWT
			.create(CustomerUiBinder.class);

	interface CustomerUiBinder extends UiBinder<Widget, Customer> {
	}

	@UiField
	SimplePanel customerPanel;

	@UiField
	Anchor ancAddCustomer;

	AddCustomer addCustomer;

	@UiField
	Button btnSearchCustomer;

	CustomerGrid customerGrid;

	final SingleSelectionModel<CustomerEntity> selectionModel = new SingleSelectionModel<CustomerEntity>(
			CustomerListCell.PROVIDES_KEY);

	public Customer() {

		initWidget(uiBinder.createAndBindUi(this));
	}

	public void initPage() {
		customerPanel.add(new AddCustomer());
		
		
	}

	@UiHandler({ "ancAddCustomer" })
	void handlAddCustomerLink(ClickEvent event) {
		LoggerMessage.printToConsole(" Inside Add customer link");
		// Remove the children and add the add customer panel

		Widget childWidget = customerPanel.getWidget();

		if (childWidget != null) {
			customerPanel.remove(childWidget);
		}

		addCustomer = new AddCustomer();
		customerPanel.add(addCustomer);
	}

	@UiHandler({ "btnSearchCustomer" })
	void handleSeachCustomer(ClickEvent event) {
		LoggerMessage.printToConsole(" Inside search customer ");
		Widget childWidget = customerPanel.getWidget();
		if (childWidget != null) {
			customerPanel.remove(childWidget);
		}		
		
		customerPanel.add(new CustomerGridGxt());
	}
}
