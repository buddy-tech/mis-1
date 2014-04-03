package com.kumar.mis.app.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kumar.mis.app.shared.domain.CustomerEntity;

public interface CustomerServiceAsync {

	void saveCustomer(CustomerEntity customerEntity,
			AsyncCallback<String> callback);

	void list(int start, int length,
			AsyncCallback<List<CustomerEntity>> callback);

}
