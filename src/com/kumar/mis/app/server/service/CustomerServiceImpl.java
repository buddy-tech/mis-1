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
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

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
		Objectify ofy = ObjectifyService.begin();
		if (customerEntity.getId() != null) {
			System.out.println(" Update called...");
			// update
			
			CustomerEntity entityToBeUpdated = ofy.get(CustomerEntity.class, customerEntity.getId());
			entityToBeUpdated.setCustomerName(customerEntity.getCustomerName());;
			entityToBeUpdated.setCustomerEmail(customerEntity.getCustomerEmail());
			entityToBeUpdated.setContactNumber(customerEntity.getContactNumber());
			entityToBeUpdated.setAddressLine1(customerEntity.getAddressLine1());
			entityToBeUpdated.setAddressLine2(customerEntity.getAddressLine2());
			entityToBeUpdated.setCity(customerEntity.getCity());
			entityToBeUpdated.setCountry(customerEntity.getCountry());
			entityToBeUpdated.setState(customerEntity.getState());
			
			ofy.put(entityToBeUpdated);

			return customerEntity.getId();
		}

		customerEntity.setId(UUID.randomUUID().toString());

		ofy.put(customerEntity);
		// save logic
		return customerEntity.getId();
	}

	@Override
	public List<CustomerEntity> list() {
		Objectify ofy = ObjectifyService.begin();
		Query<CustomerEntity> q = ofy.query(CustomerEntity.class);
		ArrayList<CustomerEntity> arrayList = new ArrayList<CustomerEntity>();
		for (CustomerEntity entity : q) {
			arrayList.add(entity);
		}
		return arrayList;
	}

	@Override
	public PagingLoadResult<CustomerEntity> listAll(PagingLoadConfig loadConfig) {
		int length = loadConfig.getLimit();
		int offset = loadConfig.getOffset();
		int start = offset;

		System.out.print(" Start " + start);

		Objectify ofy = ObjectifyService.begin();
		Query<CustomerEntity> q = ofy.query(CustomerEntity.class);
		ArrayList<CustomerEntity> arrayList = new ArrayList<CustomerEntity>();
		for (CustomerEntity entity : q) {

			arrayList.add(entity);
		}

		ArrayList<CustomerEntity> returnList = new ArrayList<CustomerEntity>();

		int end = start + length;
		end = end >= arrayList.size() ? arrayList.size() : end;
		for (int i = start; i < end; i++) {
			returnList.add(arrayList.get(i));
		}

		return new PagingLoadResultBean<CustomerEntity>(returnList,
				returnList.size(), loadConfig.getOffset());
	}

	@Override
	public void deleteCustomerById(String id) {

		Objectify ofy = ObjectifyService.begin();

		ofy.delete(CustomerEntity.class, id);

	}

}
