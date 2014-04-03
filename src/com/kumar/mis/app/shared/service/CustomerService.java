package com.kumar.mis.app.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kumar.mis.app.shared.domain.CustomerEntity;

@RemoteServiceRelativePath("custservice")
public interface CustomerService extends RemoteService {
	
	public String saveCustomer(CustomerEntity customerEntity) throws Exception;
	
	List<CustomerEntity> list(int start, int length);

}
