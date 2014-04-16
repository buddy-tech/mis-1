package com.kumar.mis.app.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kumar.mis.app.shared.domain.QuotationRequest;
import com.kumar.mis.app.shared.form.QuotationRequestForm;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("quotationrequestservice")
public interface QuotationRequestService extends RemoteService {

	Long save(QuotationRequestForm request);

	void deleteQuotationRequestById(Long id);

	PagingLoadResult<QuotationRequest> listAll(PagingLoadConfig loadConfig);

	List<QuotationRequest> list();

}
