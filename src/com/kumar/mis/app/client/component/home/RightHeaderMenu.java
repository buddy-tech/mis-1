package com.kumar.mis.app.client.component.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.kumar.mis.app.shared.common.login.UserContext;

public class RightHeaderMenu extends Composite {

	private static RightHeaderMenuUiBinder uiBinder = GWT
			.create(RightHeaderMenuUiBinder.class);

	interface RightHeaderMenuUiBinder extends UiBinder<Widget, RightHeaderMenu> {
	}

	private UserContext userContext;

	@UiField
	Anchor ancUserLogout;
	@UiField
	Anchor ancUserProfile;

	public RightHeaderMenu() {
		initWidget(uiBinder.createAndBindUi(this));
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
