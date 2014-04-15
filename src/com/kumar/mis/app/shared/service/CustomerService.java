package com.kumar.mis.app.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("custservice")
public interface CustomerService extends RemoteService {
	
	public String saveCustomer(CustomerEntity customerEntity) throws Exception;
	
	List<CustomerEntity> list();
	
	PagingLoadResult<CustomerEntity> listAll(PagingLoadConfig loadConfig);
	
	void deleteCustomerById(String id);

}
