package com.kumar.mis.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;
import com.kumar.mis.app.client.component.common.SuccessAlert;
import com.kumar.mis.app.client.component.customer.CustomerForm;
import com.kumar.mis.app.client.component.dashboard.DashboardMain;
import com.kumar.mis.app.client.component.login.MISLoginPage;
import com.kumar.mis.app.shared.UserStore;
import com.kumar.mis.app.shared.common.LoggerMessage;
import com.kumar.mis.app.shared.common.login.UserContext;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Misweb2014 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	private UserStore store = new UserStore();
	private MISLoginPage loginPage = null;
	private UserContext userContext = null;

	private DashboardMain dashboardMain = null;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		LoggerMessage.isConsolePrintingEnabled = true;
		loginPage = new MISLoginPage(this);

//		RootPanel.get("mainContainer").add(loginPage);
		RootPanel.get("mainContainer").add(new CustomerForm());
	}

	public UserContext getUserContext() {
		return userContext;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

	public void changeToDashboardOnAuthentication() {
		// Clear the screen jumbotron and the login and move to the dashboard

		// Get jumbotron and login element
		loginPage.getElement().removeFromParent();
		// DOM.removeChild(RootPanel.get("rootContainer").getElement(),
		// RootPanel.get("jumbotron").getElement());
		// Update the Header
		Element headerText = DOM.getElementById("txtHeaderText");
		Anchor headerachor = Anchor.wrap(headerText);
		LoggerMessage.printToConsole(headerachor.getText());
		headerachor.setText("Welcome "
				+ userContext.getUser().getEmailAddress());
		RootPanel.get("jumbotron").getElement().removeFromParent();
		dashboardMain = new DashboardMain(this);

		// RootPanel.get("pageContainer").add(new
		// SuccessAlert("Authentication successful"));

		RootPanel.get("alerts").add(
				new SuccessAlert("Authentication successful"));

		RootPanel.get("mainContainer").add(dashboardMain);

		dashboardMain.initPage();

	}

}
