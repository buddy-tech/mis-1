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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.kumar.mis.app.shared.common.LoggerMessage;
import com.kumar.mis.app.shared.common.data.StaticDataDb;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.kumar.mis.app.shared.service.CustomerService;
import com.kumar.mis.app.shared.service.CustomerServiceAsync;

public class QuotationRequest extends Composite {

	private static QuotationRequestUiBinder uiBinder = GWT
			.create(QuotationRequestUiBinder.class);

	private static CustomerServiceAsync customerService = GWT
			.create(CustomerService.class);

	interface QuotationRequestUiBinder extends
			UiBinder<Widget, QuotationRequest> {
	}

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

	DateBox releaseDateBox;

	DateBox requestDateBox;

	public QuotationRequest() {
		initWidget(uiBinder.createAndBindUi(this));
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

		// fetch all the customer and populate the dropdown

		customerService.list(new AsyncCallback<List<CustomerEntity>>() {

			@Override
			public void onSuccess(List<CustomerEntity> result) {

				for (CustomerEntity ce : result) {

					lstCustomerName.addItem(
							ce.getCustomerName() + ":" + ce.getCustomerEmail(),
							ce.getCustomerName());

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
		
		LoggerMessage.printToConsole(" requestDate Date "+ requestDate.toString());
		LoggerMessage.printToConsole(" response Date "+ responseDate.toString());
		LoggerMessage.printToConsole(" Customer name selected : "+ lstCustomerName.getItemText(lstCustomerName.getSelectedIndex()));
		LoggerMessage.printToConsole(" Customer name selected : "+ lstCustomerName.getItemText(lstCustomerName.getSelectedIndex()));
		LoggerMessage.printToConsole(" Requestmode name selected : "+ lstRequestMode.getItemText(lstRequestMode.getSelectedIndex()));
		
		

		
	}

}
