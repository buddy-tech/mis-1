package com.kumar.mis.app.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.kumar.mis.app.shared.service.CustomerService;

@SuppressWarnings("serial")
public class CustomerServiceImpl extends RemoteServiceServlet implements
		CustomerService {

	@Override
	public void init() throws ServletException {
		ObjectifyService.register(CustomerEntity.class);
		super.init();
	}

	@Override
	public String saveCustomer(CustomerEntity customerEntity) throws Exception {

		customerEntity.setId(UUID.randomUUID().toString());

		Objectify ofy = ObjectifyService.begin();

		ofy.put(customerEntity);

		System.out.println(customerEntity.getId());
		// save logic
		return customerEntity.getId();
	}

	@Override
	public List<CustomerEntity> list(int start, int length) {
		Objectify ofy = ObjectifyService.begin();
		Query<CustomerEntity> q = ofy.query(CustomerEntity.class);
		ArrayList<CustomerEntity> arrayList = new ArrayList<CustomerEntity>();
		for (CustomerEntity entity : q) {
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
			arrayList.add(entity);
		}

		ArrayList<CustomerEntity> returnList = new ArrayList<CustomerEntity>();

		int end = start + length;
		end = end >= arrayList.size() ? arrayList.size() : end;
		for (int i = start; i < end; i++) {
			System.out.println(i);
			returnList.add(arrayList.get(i));
		}

		System.out.println("Returning : " + returnList);

		return returnList;
	}

}
