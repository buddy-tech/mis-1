package com.kumar.mis.app.client.component.dashboard.child;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.kumar.mis.app.shared.common.LoggerMessage;

public class Trends extends Composite {

	private static TrendsUiBinder uiBinder = GWT.create(TrendsUiBinder.class);

	interface TrendsUiBinder extends UiBinder<Widget, Trends> {
	}

	public Trends() {
		LoggerMessage.printToConsole("Inside Trends");
		initWidget(uiBinder.createAndBindUi(this));
	}

}
