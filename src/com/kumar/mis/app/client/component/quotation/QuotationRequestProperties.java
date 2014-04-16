package com.kumar.mis.app.client.component.quotation;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

interface QuotationRequestProperties extends
		PropertyAccess<com.kumar.mis.app.shared.domain.QuotationRequest> {
	@Path("id")
	ModelKeyProvider<com.kumar.mis.app.shared.domain.QuotationRequest> key();

	@Path("id")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, Long> id();

	@Path("dateCreated")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, Date> dateCreated();

	@Path("dateUpdated")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, Date> dateUpdated();

	@Path("requestDate")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, Date> requestDate();

	@Path("responseDate")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, Date> responseDate();

	@Path("customerId")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, String> customerId();

	@Path("description")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, String> description();

	@Path("requestMode")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, String> requestMode();
	@Path("status")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, String> status();
	@Path("name")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, String> name();
	@Path("customer.customerName")
	ValueProvider<com.kumar.mis.app.shared.domain.QuotationRequest, String> customername();

}
