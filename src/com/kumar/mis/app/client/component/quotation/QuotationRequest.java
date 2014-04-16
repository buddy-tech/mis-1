package com.kumar.mis.app.client.component.quotation;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.kumar.mis.app.client.component.common.SuccessAlert;
import com.kumar.mis.app.client.component.parts.PartsGridGxt;
import com.kumar.mis.app.shared.common.LoggerMessage;
import com.kumar.mis.app.shared.common.data.StaticDataDb;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.kumar.mis.app.shared.form.QuotationRequestForm;
import com.kumar.mis.app.shared.service.CustomerService;
import com.kumar.mis.app.shared.service.CustomerServiceAsync;
import com.kumar.mis.app.shared.service.QuotationRequestService;
import com.kumar.mis.app.shared.service.QuotationRequestServiceAsync;

public class QuotationRequest extends Composite {

	private static QuotationRequestUiBinder uiBinder = GWT
			.create(QuotationRequestUiBinder.class);

	private static CustomerServiceAsync customerService = GWT
			.create(CustomerService.class);
	private static QuotationRequestServiceAsync quotationRequestService = GWT
			.create(QuotationRequestService.class);

	interface QuotationRequestUiBinder extends
			UiBinder<Widget, QuotationRequest> {
	}

	QuotationRequestForm quotatioinRequestForm = new QuotationRequestForm();

	@UiField
	VerticalPanel releaseDatePanel;
	@UiField
	VerticalPanel requestDatePanel;
	@UiField
	ListBox lstRequestMode;
	@UiField
	ListBox lstCustomerName;

	@UiField
	Button btnAddRequest;

	@UiField
	TextArea txtDescription;
	@UiField
	Label lblRequestStatus;
	@UiField
	Hidden hddRequestId;
	@UiField
	TextBox txtName;

	DateBox releaseDateBox;

	DateBox requestDateBox;

	Widget thisObject;

	public QuotationRequest() {
		initWidget(uiBinder.createAndBindUi(this));
		hddRequestId.setValue(null);
		thisObject = this;
		releaseDatePanel.setWidth("100%");
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
		releaseDateBox = new DateBox();
		releaseDateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		releaseDateBox.setStyleName("form-control");
		releaseDatePanel.add(releaseDateBox);

		requestDatePanel.setWidth("100%");
		requestDateBox = new DateBox();
		requestDateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		requestDateBox.setStyleName("form-control");
		requestDatePanel.add(requestDateBox);
		lblRequestStatus.setText(StaticDataDb.getRequestStatus().get(
				"inprogress"));

		// fetch all the customer and populate the dropdown

		customerService.list(new AsyncCallback<List<CustomerEntity>>() {

			@Override
			public void onSuccess(List<CustomerEntity> result) {

				for (CustomerEntity ce : result) {

					lstCustomerName.addItem(
							ce.getCustomerName() + ":" + ce.getCustomerEmail(),
							ce.getId());

				}

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching the customer records");

			}
		});

		Iterator<Entry<String, String>> iter = StaticDataDb.getRequestMode()
				.entrySet().iterator();

		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			lstRequestMode.addItem(entry.getValue(), entry.getKey());
		}

	}

	@UiHandler({ "btnAddRequest" })
	void addRequestHandler(ClickEvent event) {

		LoggerMessage.printToConsole(" Add request handler called ...");

		Date requestDate = requestDateBox.getValue();
		Date responseDate = releaseDateBox.getValue();

		LoggerMessage.printToConsole(" requestDate Date "
				+ requestDate.toString());
		LoggerMessage.printToConsole(" response Date "
				+ responseDate.toString());
		LoggerMessage.printToConsole(" Customer name selected : "
				+ lstCustomerName.getItemText(lstCustomerName
						.getSelectedIndex()));
		LoggerMessage
				.printToConsole(" Requestmode name selected : "
						+ lstRequestMode.getItemText(lstRequestMode
								.getSelectedIndex()));
		LoggerMessage.printToConsole(" Name : " + txtName.getText());

		quotatioinRequestForm.setCustomerId(lstCustomerName
				.getValue(lstCustomerName.getSelectedIndex()));

		quotatioinRequestForm.setDateCreated(new Date());
		quotatioinRequestForm.setRequestDate(requestDate);
		quotatioinRequestForm.setResponseDate(responseDate);
		quotatioinRequestForm.setRequestMode(lstRequestMode
				.getValue(lstRequestMode.getSelectedIndex()));
		quotatioinRequestForm.setDescription(txtDescription.getText());
		quotatioinRequestForm.setName(txtName.getText());

		if (hddRequestId.getValue() != null
				&& hddRequestId.getValue().trim().length() > 0) {

			LoggerMessage.printToConsole(" Hidden id is "
					+ hddRequestId.getValue());

			quotatioinRequestForm
					.setId(Long.parseLong(hddRequestId.getValue()));

		}

		quotationRequestService.save(quotatioinRequestForm,
				new AsyncCallback<Long>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error Saving the quotation request");

					}

					@Override
					public void onSuccess(Long result) {

						SimplePanel panel = (SimplePanel) thisObject
								.getParent();

						thisObject.removeFromParent();

						panel.add(new QuotationRequestGrid());

						RootPanel.get("alerts").add(
								new SuccessAlert(
										"Quotation request saved with id : "
												+ result));

					}
				});

	}

	public void initPageForEdit(
			final com.kumar.mis.app.shared.domain.QuotationRequest selectedItem) {
		LoggerMessage.printToConsole(" Update Called ");
		// Set the customer id first

		customerService.list(new AsyncCallback<List<CustomerEntity>>() {

			@Override
			public void onSuccess(List<CustomerEntity> result) {

				for (CustomerEntity ce : result) {

					lstCustomerName.addItem(
							ce.getCustomerName() + ":" + ce.getCustomerEmail(),
							ce.getId());

				}

				int count = lstCustomerName.getItemCount();

				for (int i = 0; i < count; i++) {
					String itemText = lstCustomerName.getValue(i);

					if (selectedItem.getCustomerId().equalsIgnoreCase(itemText)) {
						lstCustomerName.setSelectedIndex(i);
						break;
					}
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching the customer records");

			}
		});

		txtDescription.setText(selectedItem.getDescription());
		requestDateBox.setValue(selectedItem.getRequestDate());
		releaseDateBox.setValue(selectedItem.getResponseDate());
		lblRequestStatus.setText(selectedItem.getStatus());
		txtName.setText(selectedItem.getName());

		int count = lstRequestMode.getItemCount();

		for (int i = 0; i < count; i++) {
			String itemText = lstRequestMode.getValue(i);

			if (selectedItem.getRequestMode().equalsIgnoreCase(itemText)) {
				lstRequestMode.setSelectedIndex(i);
				break;
			}
		}

		hddRequestId.setValue(selectedItem.getId().toString());

	}

}
