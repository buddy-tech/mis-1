package com.kumar.mis.app.client.component.customer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ProvidesKey;
import com.kumar.mis.app.shared.domain.CustomerEntity;

public class CustomerListCell extends AbstractCell<CustomerEntity> {
	
	
	
	public static final ProvidesKey<CustomerEntity> PROVIDES_KEY = new ProvidesKey<CustomerEntity>() {
		
		@Override
		public Object getKey(CustomerEntity item) {
			return item == null ? null : item.getId();
		}
	};

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			CustomerEntity value, SafeHtmlBuilder sb) {
		sb.appendHtmlConstant("<span class='label label-primary'>");
		if(value.getCustomerName().equals("")){
			value.setCustomerName("Name not Set");
		}
		sb.appendHtmlConstant(value.getCustomerName());
		sb.appendHtmlConstant("</span>");
	}

}
