package com.kumar.mis.app.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.kumar.mis.app.shared.domain.Parts;
import com.kumar.mis.app.shared.domain.QuotationRequest;

public class ObjectifyInitServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ObjectifyService.register(CustomerEntity.class);
		ObjectifyService.register(Parts.class);
		ObjectifyService.register(QuotationRequest.class);
		System.out.println(" Objects registred..............");
		super.init();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);

		System.out.println(" called. ");
	}

}
