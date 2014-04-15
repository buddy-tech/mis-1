package com.kumar.mis.app.shared.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kumar.mis.app.shared.domain.Parts;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("partservice")
public interface PartsService extends RemoteService {

	Long SaveParts(Parts parts);

	void deletePartById(Long id);

	PagingLoadResult<Parts> listAll(PagingLoadConfig loadConfig);

}
