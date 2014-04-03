package com.kumar.mis.app.client.component.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.kumar.mis.app.client.Misweb2014;
import com.kumar.mis.app.shared.UserStore;
import com.kumar.mis.app.shared.common.LoggerMessage;
import com.kumar.mis.app.shared.common.login.UserContext;
import com.kumar.mis.app.shared.domain.login.User;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.MessageBox;

public class MISLoginPage extends Composite {

	private static MISLoginPageUiBinder uiBinder = GWT
			.create(MISLoginPageUiBinder.class);

	@UiField
	TextBox txtEmail;
	@UiField(provided = true)
	PasswordTextBox txtPassword;
	@UiField
	Button btnSubmit;
	@UiField
	Button btnCancel;

	@UiField(provided = true)
	User user;

	private Misweb2014 misweb2014;

	interface MISLoginPageUiBinder extends UiBinder<Widget, MISLoginPage> {
	}

	public MISLoginPage(Misweb2014 misweb2014) {

		this.misweb2014 = misweb2014;
		txtPassword = new PasswordTextBox();
		user = new User("kumar@kumar.com","123456");
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler({ "btnSubmit" })
	void handleSubmit(ClickEvent event) {

		String password = txtPassword.getText();
		String email = txtEmail.getText();
		LoggerMessage.printToConsole("Email " + email);
		LoggerMessage.printToConsole("Inside submit");

		User loginUser = new User(email, password);

		UserContext uc = UserStore.authenticate(loginUser);
		
		if(uc != null){
			misweb2014.setUserContext(uc);
		/*	MessageBox box = new MessageBox(" Authentication Success" );
			box.setPredefinedButtons(PredefinedButton.YES);
			box.setIcon(MessageBox.ICONS.info());
			box.setMessage("Authentication Success");
			box.show();*/
			
			misweb2014.changeToDashboardOnAuthentication();
		}
		else{
			MessageBox box = new MessageBox(" Authentication Error" );
			box.setPredefinedButtons(PredefinedButton.YES);
			box.setIcon(MessageBox.ICONS.error());
			box.setMessage(" The user name password is not valid. Please login again.");
			box.show();
		}
		
		

	}

}
