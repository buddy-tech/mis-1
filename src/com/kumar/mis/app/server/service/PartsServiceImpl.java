package com.kumar.mis.app.server.service;

import java.util.ArrayList;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.kumar.mis.app.shared.domain.Parts;
import com.kumar.mis.app.shared.service.PartsService;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

@SuppressWarnings("serial")
public class PartsServiceImpl extends RemoteServiceServlet implements
		PartsService {

	@Override
	public void init() throws ServletException {
		ObjectifyService.register(Parts.class);
		super.init();
	}

	@Override
	public Long SaveParts(Parts parts) {

		Objectify ofy = ObjectifyService.begin();

		System.out.println("Part to be saved : " + parts.toString());

		if (parts.getId() != null) {
			System.out.println(" update called....");
			
			// Fetch by id and then save it
			Parts partToBeUpdated = ofy.get(Parts.class, parts.getId());
			partToBeUpdated.setPartName(parts.getPartName());
			partToBeUpdated.setPrice(parts.getPrice());
			partToBeUpdated.setPartDescription(parts.getPartDescription());
			
		
			ofy.put(partToBeUpdated);

			return parts.getId();
		}

		Key<Parts> partSaved = ofy.put(parts);

		System.out.println(partSaved.getId());

		return partSaved.getId();
	}

	@Override
	public void deletePartById(Long id) {
		
		System.out.println(" Deleting id : "+id);
		Objectify ofy = ObjectifyService.begin();

		ofy.delete(Parts.class, id);

	}

	@Override
	public PagingLoadResult<Parts> listAll(PagingLoadConfig loadConfig) {
		int length = loadConfig.getLimit();
		int offset = loadConfig.getOffset();
		int start = offset;

		System.out.print(" Start " + start);

		Objectify ofy = ObjectifyService.begin();
		Query<Parts> q = ofy.query(Parts.class);
		ArrayList<Parts> arrayList = new ArrayList<Parts>();
		for (Parts entity : q) {

			arrayList.add(entity);
		}

		ArrayList<Parts> returnList = new ArrayList<Parts>();

		int end = start + length;
		end = end >= arrayList.size() ? arrayList.size() : end;
		for (int i = start; i < end; i++) {
			returnList.add(arrayList.get(i));
		}

		return new PagingLoadResultBean<Parts>(returnList, returnList.size(),
				loadConfig.getOffset());
	}

}
