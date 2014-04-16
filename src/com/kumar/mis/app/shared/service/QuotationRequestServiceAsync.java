package com.kumar.mis.app.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kumar.mis.app.shared.domain.QuotationRequest;
import com.kumar.mis.app.shared.form.QuotationRequestForm;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface QuotationRequestServiceAsync {

	void save(QuotationRequestForm request, AsyncCallback<Long> callback);

	void deleteQuotationRequestById(Long id, AsyncCallback<Void> callback);

	void listAll(PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<QuotationRequest>> callback);

	void list(AsyncCallback<List<QuotationRequest>> callback);

}
