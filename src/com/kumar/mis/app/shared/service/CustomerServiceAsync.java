package com.kumar.mis.app.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface CustomerServiceAsync {

	void saveCustomer(CustomerEntity customerEntity,
			AsyncCallback<String> callback);

	void list(AsyncCallback<List<CustomerEntity>> callback);

	void listAll(PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<CustomerEntity>> callback);

	void deleteCustomerById(String id, AsyncCallback<Void> callback);

}
