package com.kumar.mis.app.client.component.quotation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.kumar.mis.app.client.component.customer.grid.CustomerGridGxt;
import com.kumar.mis.app.shared.common.LoggerMessage;

public class QuotationPanel extends Composite {

	private static QuotationPanelUiBinder uiBinder = GWT
			.create(QuotationPanelUiBinder.class);

	interface QuotationPanelUiBinder extends UiBinder<Widget, QuotationPanel> {
	}

	@UiField
	Anchor ancQuotationRequest;
	@UiField
	Anchor ancQuotationRespond;
	@UiField
	Button btnQuotationSearch;
	@UiField
	SimplePanel quotationPanel;

	public QuotationPanel() {
		

		initWidget(uiBinder.createAndBindUi(this));

	}

	public void initPage() {
		LoggerMessage.printToConsole(" Quotation Panel main panel initialized");

	}

	@UiHandler({ "ancQuotationRequest" })
	void handleQuotationRequest(ClickEvent event) {
		LoggerMessage.printToConsole(" new quotation request handler ");

		Widget childWidget = quotationPanel.getWidget();
		if (childWidget != null) {
			quotationPanel.remove(childWidget);
		}

		quotationPanel.add(new QuotationRequest());

	}

	@UiHandler({ "ancQuotationRespond" })
	void handleQuotationRespond(ClickEvent event) {
		LoggerMessage.printToConsole(" New quotation response");
		Widget childWidget = quotationPanel.getWidget();
		if (childWidget != null) {
			quotationPanel.remove(childWidget);
		}
	}

	@UiHandler({ "btnQuotationSearch" })
	void handleQuotationSearch(ClickEvent event) {
		LoggerMessage.printToConsole(" Quotation Search");
		Widget childWidget = quotationPanel.getWidget();
		if (childWidget != null) {
			quotationPanel.remove(childWidget);
		}
	}

}
