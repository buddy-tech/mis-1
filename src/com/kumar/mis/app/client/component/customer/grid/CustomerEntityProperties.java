package com.kumar.mis.app.client.component.customer.grid;

import com.google.gwt.editor.client.Editor.Path;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CustomerEntityProperties extends
		PropertyAccess<CustomerEntity> {
	@Path("id")
	ModelKeyProvider<CustomerEntity> key();
    @Path("customerName")
	ValueProvider<CustomerEntity, String> name();
    @Path("contactNumber")
	ValueProvider<CustomerEntity, String> contactNumber();
    @Path("customerEmail")
	ValueProvider<CustomerEntity, String> email();
    @Path("addressLine1")
	ValueProvider<CustomerEntity, String> address1();
    @Path("addressLine2")
	ValueProvider<CustomerEntity, String> address2();
    @Path("city")
	ValueProvider<CustomerEntity, String> city();
    @Path("state")
	ValueProvider<CustomerEntity, String> state();
    @Path("country")
	ValueProvider<CustomerEntity, String> country();

}
