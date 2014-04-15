package com.kumar.mis.app.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kumar.mis.app.shared.domain.Parts;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface PartsServiceAsync {

	void SaveParts(Parts parts, AsyncCallback<Long> callback);

	void deletePartById(Long id, AsyncCallback<Void> callback);

	void listAll(PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<Parts>> callback);

}
