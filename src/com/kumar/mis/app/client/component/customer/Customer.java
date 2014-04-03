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

	@UiField
	ScrollPanel customerListPanel;

	CustomerGrid customerGrid;

	final SingleSelectionModel<CustomerEntity> selectionModel = new SingleSelectionModel<CustomerEntity>(
			CustomerListCell.PROVIDES_KEY);

	public Customer() {

		initWidget(uiBinder.createAndBindUi(this));
	}

	public void initPage() {
		customerPanel.add(new AddCustomer());
		// Add a cell list to the customerListPanel
		// call customer service and get all the data

		final CustomerServiceAsync customerServiceAsync = GWT
				.create(CustomerService.class);

		CellList<CustomerEntity> customerListTable = new CellList<CustomerEntity>(
				new CustomerListCell(), CustomerListCell.PROVIDES_KEY);

		
		customerListTable.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						LoggerMessage.printToConsole(selectionModel
								.getSelectedObject().toString());
						
						Info.display("Selected", " Customer Selected. Functionality not implemented"+selectionModel.getSelectedObject().toString());
					}
				});

		customerListTable.setHeight("400px");
		customerListTable.setStyleName("well well-lg");
		

		AsyncDataProvider<CustomerEntity> asyncDataProvider = new AsyncDataProvider<CustomerEntity>() {

			@Override
			protected void onRangeChanged(HasData<CustomerEntity> display) {
				final int start = display.getVisibleRange().getStart();
				int length = display.getVisibleRange().getLength();
				AsyncCallback<List<CustomerEntity>> asyncCallback = new AsyncCallback<List<CustomerEntity>>() {

					@Override
					public void onSuccess(List<CustomerEntity> result) {
						LoggerMessage.printToConsole(" returned "+result);
						updateRowData(start, result);
						LoggerMessage.printToConsole("After updating row");
					}

					@Override
					public void onFailure(Throwable caught) {
						LoggerMessage
								.printToConsole("Error while fetching data");

					}
				};

				customerServiceAsync.list(start, length, asyncCallback);

			}
		};
		
		
		asyncDataProvider.addDataDisplay(customerListTable);
		customerListTable.setPageSize(50);
		
		asyncDataProvider.updateRowCount(100, true);
		
		SimplePager pager = new SimplePager();
		pager.setDisplay(customerListTable);

		VerticalPanel vp = new VerticalPanel();
		vp.add(customerListTable);
		vp.add(pager);
		
		customerListPanel.add(vp);
		
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
		customerGrid = new CustomerGrid();
		customerPanel.add(customerGrid);
	}
}
