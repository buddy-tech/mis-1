package com.kumar.mis.app.client.component.customer;

import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.base.Preconditions;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.kumar.mis.app.client.component.common.ErrorAlert;
import com.kumar.mis.app.client.component.common.SuccessAlert;
import com.kumar.mis.app.client.component.customer.grid.CustomerGridGxt;
import com.kumar.mis.app.shared.common.LoggerMessage;
import com.kumar.mis.app.shared.common.data.StaticDataDb;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.kumar.mis.app.shared.service.CustomerService;
import com.kumar.mis.app.shared.service.CustomerServiceAsync;
import com.sencha.gxt.widget.core.client.info.Info;

public class AddCustomer extends Composite {

	private static AddCustomerUiBinder uiBinder = GWT
			.create(AddCustomerUiBinder.class);

	@UiField
	TextBox txtCustomerName;
	@UiField
	TextBox txtContactNumber;
	@UiField
	TextBox txtEmailAddress;
	@UiField
	TextBox txtAddressLine1;
	@UiField
	TextBox txtAddressLine2;

	@UiField
	Button btnAddCustomer;
	@UiField(provided = true)
	ListBox stateListBox;
	@UiField(provided = true)
	ListBox countryListBox;
	@UiField(provided = true)
	ListBox cityListBox;

	@UiField(provided = true)
	CustomerEntity customer;

	interface AddCustomerUiBinder extends UiBinder<Widget, AddCustomer> {
	}

	public AddCustomer() {

		stateListBox = new ListBox();

		Iterator<Entry<String, String>> iter = StaticDataDb.getCities()
				.entrySet().iterator();
		cityListBox = new ListBox();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			cityListBox.addItem(entry.getKey(), entry.getValue());
		}

		iter = StaticDataDb.getStates().entrySet().iterator();

		stateListBox = new ListBox();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			stateListBox.addItem(entry.getKey(), entry.getValue());
		}

		iter = StaticDataDb.getCountries().entrySet().iterator();
		countryListBox = new ListBox();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			countryListBox.addItem(entry.getKey(), entry.getValue());
		}

		customer = new CustomerEntity();
		initWidget(uiBinder.createAndBindUi(this));

	}

	// Called by edit customer entity
	public AddCustomer(CustomerEntity customerEntity) {

		super();
		/**/

	}

	public void initPage() {
		LoggerMessage.printToConsole("Inside add customer....");
	}

	@UiHandler({ "btnAddCustomer" })
	void handleAddCustomer(ClickEvent event) {

		CustomerServiceAsync customerServiceAsync = GWT
				.create(CustomerService.class);
		final AddCustomer thisObject = this;
		customer.setCustomerName(txtCustomerName.getText().trim());
		customer.setCustomerEmail(txtEmailAddress.getText().trim());
		customer.setContactNumber(txtContactNumber.getText().trim());
		customer.setAddressLine1(txtAddressLine1.getText().trim());
		customer.setAddressLine2(txtAddressLine2.getText().trim());

		customer.setState(stateListBox.getItemText(stateListBox
				.getSelectedIndex()));
		customer.setCountry(countryListBox.getItemText(countryListBox
				.getSelectedIndex()));
		customer.setCity(cityListBox.getItemText(cityListBox.getSelectedIndex()));

		// set the validation logic

		if (customer.getCustomerName().length() == 0) {
			RootPanel.get("alerts").add(
					new ErrorAlert("Customer name cannot be blank"));
			return;
		} else if (customer.getCustomerEmail().length() == 0) {
			RootPanel.get("alerts").add(
					new ErrorAlert("Customer email cannot be blank"));
			return;
		} else if (customer.getContactNumber().length() == 0) {
			RootPanel.get("alerts").add(
					new ErrorAlert("Customer contact cannot be blank"));
			return;
		}

		/**
		 * Save customer.
		 */

		customerServiceAsync.saveCustomer(customer,
				new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						LoggerMessage
								.printToConsole(" Success calling customer service");

						SimplePanel customer = (SimplePanel) thisObject
								.getParent();

						customer.remove(thisObject);
						customer.add(new CustomerGridGxt());

						RootPanel.get("alerts").add(
								new SuccessAlert(
										"Customer Saved Successfully Id :"
												+ result));

						Info.display("Save Successful",
								"Customer Saved Successfully");

					}

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						LoggerMessage
								.printToConsole("Failure calling customer service");

					}
				});

	}

	public void initPageForEdit(CustomerEntity selectedItem) {
		this.customer = selectedItem;
		txtCustomerName.setText(selectedItem.getCustomerName());
		txtContactNumber.setText(selectedItem.getContactNumber());
		txtEmailAddress.setText(selectedItem.getCustomerEmail());
		txtAddressLine1.setText(selectedItem.getAddressLine1());
		txtAddressLine2.setText(selectedItem.getAddressLine2());

		int count = cityListBox.getItemCount();

		for (int i = 0; i < count; i++) {
			LoggerMessage.printToConsole("Count is " + count);
			String itemText = cityListBox.getItemText(i);
			LoggerMessage.printToConsole("Count is " + itemText);
			if (selectedItem.getCity().equalsIgnoreCase(itemText)) {
				cityListBox.setSelectedIndex(i);
				break;
			}
		}

		count = stateListBox.getItemCount();

		for (int i = 0; i < count; i++) {
			String itemText = stateListBox.getItemText(i);
			if (selectedItem.getState().equalsIgnoreCase(itemText)) {
				stateListBox.setSelectedIndex(i);
				break;
			}
		}

		count = countryListBox.getItemCount();

		for (int i = 0; i < count; i++) {
			String itemText = countryListBox.getItemText(i);
			if (selectedItem.getCountry().equalsIgnoreCase(itemText)) {
				countryListBox.setSelectedIndex(i);
				break;
			}
		}

		btnAddCustomer.setText("Update Customer");

	}

}
