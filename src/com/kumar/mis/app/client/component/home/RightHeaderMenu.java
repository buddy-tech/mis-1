package com.kumar.mis.app.client.component.home;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.kumar.mis.app.shared.common.LoggerMessage;
import com.kumar.mis.app.shared.common.login.UserContext;
import com.kumar.mis.app.shared.domain.QuotationRequest;
import com.kumar.mis.app.shared.service.QuotationRequestService;
import com.kumar.mis.app.shared.service.QuotationRequestServiceAsync;
import com.sencha.gxt.core.client.XTemplates;

public class RightHeaderMenu extends Composite {

	private static RightHeaderMenuUiBinder uiBinder = GWT
			.create(RightHeaderMenuUiBinder.class);

	private static QuotationRequestServiceAsync quotationRequestService = GWT
			.create(QuotationRequestService.class);

	interface RightHeaderMenuUiBinder extends UiBinder<Widget, RightHeaderMenu> {
	}

	public interface AlertRenderer extends XTemplates {
		@XTemplate("<li><a href='#'><div><i class='fa fa-comment fa-fw'></i>{data.name}</div></a></li><li class='divider'></li>")
		public SafeHtml render(QuotationRequest data);

	}

	AlertRenderer renderer = GWT.create(AlertRenderer.class);

	private UserContext userContext;

	@UiField
	Anchor ancUserLogout;
	@UiField
	Anchor ancUserProfile;
	@UiField
	UListElement alertList;

	public RightHeaderMenu() {
		initWidget(uiBinder.createAndBindUi(this));

		alertList.removeAllChildren();

		quotationRequestService
				.list(new AsyncCallback<List<QuotationRequest>>() {

					@Override
					public void onSuccess(List<QuotationRequest> result) {
						// TODO Auto-generated method stub

						/*
						 * for (QuotationRequest qr : result) {
						 * 
						 * 
						 * final LIElement text = new
						 * LIElement(renderer.render(data)render(qr));
						 * alertList.appendChild(text.getElement());
						 * 
						 * }
						 */
						for (QuotationRequest qr : result) {

							LIElement element = Document.get()
									.createLIElement();
							AnchorElement anchor = Document.get()
									.createAnchorElement();
							SpanElement spanElement = Document.get()
									.createSpanElement();
							spanElement
									.addClassName("pull-right text-muted small");

							spanElement.setInnerHTML(DateTimeFormat.getFormat(
									"dd/MM/yyyy").format(qr.getRequestDate()));
							ParagraphElement paragraphElement = Document.get()
									.createPElement();
							paragraphElement
									.setClassName("fa fa-bell fa-fw");

							SpanElement spanElement1 = Document.get()
									.createSpanElement();
							spanElement1.setInnerHTML(qr.getName());

							element.appendChild(anchor);
							anchor.appendChild(paragraphElement);
							anchor.appendChild(spanElement);
							anchor.appendChild(spanElement1);

							alertList.appendChild(element);
							LIElement dividerElement = Document.get()
									.createLIElement();
							dividerElement.setClassName("divider");
							alertList.appendChild(dividerElement);

							LoggerMessage.printToConsole(" alert list "
									+ element);

						}

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(" Error fetching quotation request");

					}
				});
	}

	public RightHeaderMenu(UserContext userContext) {
		this();
		this.userContext = userContext;
	}

	@UiHandler({ "ancUserProfile" })
	void handleAncUserProfile(ClickEvent event) {
		Window.alert(" Not yet implemented");
	}

	@UiHandler({ "ancUserLogout" })
	void handleAncUserLogout(ClickEvent event) {
		Window.alert(" Not yet implemented");
	}

}
