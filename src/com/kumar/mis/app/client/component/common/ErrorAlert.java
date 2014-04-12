package com.kumar.mis.app.client.component.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ErrorAlert extends Composite {

	private static ErrorAlertUiBinder uiBinder = GWT
			.create(ErrorAlertUiBinder.class);

	interface ErrorAlertUiBinder extends UiBinder<Widget, ErrorAlert> {
	}

	@UiField(provided = true)
	Label lblAlertMsg;
	
	
	public ErrorAlert(String messageText) {
		lblAlertMsg = new Label();
		lblAlertMsg.setText(messageText);
		initWidget(uiBinder.createAndBindUi(this));
	}

}
