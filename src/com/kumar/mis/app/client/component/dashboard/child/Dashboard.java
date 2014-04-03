package com.kumar.mis.app.client.component.dashboard.child;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kumar.mis.app.shared.common.LoggerMessage;

public class Dashboard extends Composite {

	private static DashboardUiBinder uiBinder = GWT
			.create(DashboardUiBinder.class);

	@UiField
	SimplePanel dashboardTrends;
	@UiField
	SimplePanel quotations;
	@UiField
	SimplePanel customers;
	@UiField
	SimplePanel messages;
	
	Trends trends = null;

	interface DashboardUiBinder extends UiBinder<Widget, Dashboard> {
	}

	public Dashboard() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void initPage() {
		LoggerMessage.printToConsole("Inside the initpage of Dashboard");
		dashboardTrends.add(new Trends());
	}
		

}
