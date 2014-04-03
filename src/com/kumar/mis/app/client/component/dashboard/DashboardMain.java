package com.kumar.mis.app.client.component.dashboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kumar.mis.app.client.Misweb2014;
import com.kumar.mis.app.client.component.customer.Customer;
import com.kumar.mis.app.client.component.dashboard.child.Dashboard;
import com.kumar.mis.app.shared.common.LoggerMessage;

public class DashboardMain extends Composite {

	private static DashboardMainUiBinder uiBinder = GWT
			.create(DashboardMainUiBinder.class);

	interface DashboardMainUiBinder extends UiBinder<Widget, DashboardMain> {
	}

	private Misweb2014 misweb2014;

	@UiField
	Anchor ancDashboard;
	@UiField
	Anchor ancCustomer;
	@UiField
	Anchor ancQuotation;
	@UiField
	Anchor ancReports;
	@UiField
	SimplePanel dashboardPanel;

	Dashboard dashboardContent = null;

	Customer customer;

	public DashboardMain() {

		initWidget(uiBinder.createAndBindUi(this));
	}

	public DashboardMain(Misweb2014 misweb2014) {

		this.misweb2014 = misweb2014;

		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler({ "ancDashboard", "ancCustomer", "ancQuotation", "ancReports" })
	void handleDashboardMenuSelections(ClickEvent clickEvent) {

		LoggerMessage.printToConsole(" Called the handler"
				+ clickEvent.getSource());

		Widget widget = dashboardPanel.getWidget();
		if (widget != null)
			dashboardPanel.remove(widget);

		if (clickEvent.getSource() == ancDashboard) {
			LoggerMessage.printToConsole(" Dashboard Button Clicked....");
			dashboardContent = new Dashboard();
			dashboardPanel.add(dashboardContent);
			dashboardContent.initPage();
		} else if (clickEvent.getSource() == ancCustomer) {
			customer = new Customer();
			dashboardPanel.add(customer);
			customer.initPage();
		} else {
			LoggerMessage.printToConsole(" not same");
		}
	}

	public void initPage() {

	}

}
