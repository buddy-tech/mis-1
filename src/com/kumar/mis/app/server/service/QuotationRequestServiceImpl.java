package com.kumar.mis.app.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.kumar.mis.app.shared.domain.QuotationRequest;
import com.kumar.mis.app.shared.form.QuotationRequestForm;
import com.kumar.mis.app.shared.service.QuotationRequestService;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class QuotationRequestServiceImpl extends RemoteServiceServlet implements
		QuotationRequestService {

	@Override
	public void init() throws ServletException {
		
		

		
		super.init();
	}

	@Override
	public Long save(QuotationRequestForm request) {
		Objectify ofy = ObjectifyService.begin();

		QuotationRequest quotationRequestToBeSaved = new QuotationRequest();
		quotationRequestToBeSaved.setId(request.getId());
		quotationRequestToBeSaved.setDescription(request.getDescription());
		quotationRequestToBeSaved.setRequestDate(request.getRequestDate());
		quotationRequestToBeSaved.setResponseDate(request.getResponseDate());
		if (quotationRequestToBeSaved.getId() == null)
			quotationRequestToBeSaved.setDateCreated(new Date());
		quotationRequestToBeSaved.setDateUpdated(new Date());
		quotationRequestToBeSaved.setCustomerId(request.getCustomerId());
		quotationRequestToBeSaved.setName(request.getName());

		if (request.getId() != null) {

			quotationRequestToBeSaved = ofy.get(QuotationRequest.class,
					request.getId());
			quotationRequestToBeSaved.setId(request.getId());
			quotationRequestToBeSaved.setDescription(request.getDescription());
			quotationRequestToBeSaved.setRequestDate(request.getRequestDate());
			quotationRequestToBeSaved
					.setResponseDate(request.getResponseDate());
			quotationRequestToBeSaved.setDateUpdated(new Date());
			quotationRequestToBeSaved.setCustomerId(request.getCustomerId());
			quotationRequestToBeSaved.setName(request.getName());
			ofy.put(quotationRequestToBeSaved);

			return quotationRequestToBeSaved.getId();
		}

		System.out.println(quotationRequestToBeSaved);
		ofy.put(quotationRequestToBeSaved);
		// save logic
		return quotationRequestToBeSaved.getId();
	}

	@Override
	public void deleteQuotationRequestById(Long id) {
		Objectify ofy = ObjectifyService.begin();
		ofy.delete(QuotationRequest.class, id);

	}

	@Override
	public PagingLoadResult<QuotationRequest> listAll(
			PagingLoadConfig loadConfig) {
		int length = loadConfig.getLimit();
		int offset = loadConfig.getOffset();
		int start = offset;

		System.out.print(" Start " + start);

		Objectify ofy = ObjectifyService.begin();
		Query<QuotationRequest> q = ofy.query(QuotationRequest.class);
		ArrayList<QuotationRequest> arrayList = new ArrayList<QuotationRequest>();
		for (QuotationRequest entity : q) {

			arrayList.add(entity);
		}

		ArrayList<QuotationRequest> returnList = new ArrayList<QuotationRequest>();

		int end = start + length;
		end = end >= arrayList.size() ? arrayList.size() : end;
		for (int i = start; i < end; i++) {

			QuotationRequest qr = arrayList.get(i);

			CustomerEntity ce = ofy.get(CustomerEntity.class,
					qr.getCustomerId());
			qr.setCustomer(ce);

			returnList.add(qr);
		}

		System.out.println(returnList);

		return new PagingLoadResultBean<QuotationRequest>(returnList,
				returnList.size(), loadConfig.getOffset());
	}

	@Override
	public List<QuotationRequest> list() {
		Objectify ofy = ObjectifyService.begin();
		Query<QuotationRequest> q = ofy.query(QuotationRequest.class);
		ArrayList<QuotationRequest> arrayList = new ArrayList<QuotationRequest>();
		for (QuotationRequest entity : q) {
			arrayList.add(entity);
		}
		return arrayList;
	}

}
