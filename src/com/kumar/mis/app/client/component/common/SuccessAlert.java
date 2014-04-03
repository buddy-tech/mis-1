package com.kumar.mis.app.client.component.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SuccessAlert extends Composite {

	private static SuccessAlertUiBinder uiBinder = GWT
			.create(SuccessAlertUiBinder.class);

	interface SuccessAlertUiBinder extends UiBinder<Widget, SuccessAlert> {
	}

	@UiField(provided = true)
	Label lblAlertMsg;

	public SuccessAlert(String messageText) {
		lblAlertMsg = new Label();
		lblAlertMsg.setText(messageText);
		initWidget(uiBinder.createAndBindUi(this));
	}

}
