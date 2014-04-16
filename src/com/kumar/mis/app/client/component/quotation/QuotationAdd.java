package com.kumar.mis.app.client.component.quotation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class QuotationAdd extends UIObject {

	private static QuotationAddUiBinder uiBinder = GWT
			.create(QuotationAddUiBinder.class);

	interface QuotationAddUiBinder extends UiBinder<Element, QuotationAdd> {
	}

	@UiField
	SpanElement nameSpan;

	public QuotationAdd(String firstName) {
		setElement(uiBinder.createAndBindUi(this));
		nameSpan.setInnerText(firstName);
	}

}
