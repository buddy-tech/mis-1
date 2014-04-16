package com.kumar.mis.app.client.component.quotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.kumar.mis.app.client.component.common.SuccessAlert;
import com.kumar.mis.app.client.component.parts.AddParts;
import com.kumar.mis.app.client.component.parts.PartsEntityProperties;
import com.kumar.mis.app.shared.domain.Parts;
import com.kumar.mis.app.shared.domain.QuotationRequest;
import com.kumar.mis.app.shared.service.QuotationRequestService;
import com.kumar.mis.app.shared.service.QuotationRequestServiceAsync;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.grid.RowExpander;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class QuotationRequestGrid extends Composite {

	private static final QuotationRequestProperties gridProperties = GWT
			.create(QuotationRequestProperties.class);
	private static final QuotationRequestServiceAsync quotationRequestService = GWT
			.create(QuotationRequestService.class);

	public QuotationRequestGrid() {

		RowExpander<QuotationRequest> expander = new RowExpander<QuotationRequest>(
				new AbstractCell<QuotationRequest>() {

					@Override
					public void render(
							com.google.gwt.cell.client.Cell.Context context,
							QuotationRequest value, SafeHtmlBuilder sb) {
						sb.appendHtmlConstant("<p style='margin: 5px 5px 10px'><b>Description:</b> "
								+ value.getDescription());
						sb.appendHtmlConstant("<p style='margin: 5px 5px 10px'><b>Customer Details:</b> ");
						sb.appendHtmlConstant("<p style='margin: 5px 5px 10px'><b>Email:</b> "+ value.getCustomer().getCustomerEmail());
						sb.appendHtmlConstant("<p style='margin: 5px 5px 10px'><b>Contact Number:</b> "+ value.getCustomer().getContactNumber());

					}

				});

		final ListStore<QuotationRequest> listStore = new ListStore<QuotationRequest>(
				gridProperties.key());

		ColumnConfig<QuotationRequest, Long> idColumn = new ColumnConfig<QuotationRequest, Long>(
				gridProperties.id(), 100, "Id");
		ColumnConfig<QuotationRequest, Date> requestDate = new ColumnConfig<QuotationRequest, Date>(
				gridProperties.requestDate(), 100, "Request Date");
		requestDate
				.setCell(new DateCell(DateTimeFormat.getFormat("dd/MM/yyyy")));
		ColumnConfig<QuotationRequest, Date> responseDateColumn = new ColumnConfig<QuotationRequest, Date>(
				gridProperties.responseDate(), 150, "Response Date");
		responseDateColumn.setCell(new DateCell(DateTimeFormat
				.getFormat("dd/MM/yyyy ")));
		ColumnConfig<QuotationRequest, Date> createdDateColumn = new ColumnConfig<QuotationRequest, Date>(
				gridProperties.dateCreated(), 150, "Created Date");
		createdDateColumn.setCell(new DateCell(DateTimeFormat
				.getFormat("dd/MM/yyyy HH:mm:ss")));
		ColumnConfig<QuotationRequest, Date> updatedDateColumn = new ColumnConfig<QuotationRequest, Date>(
				gridProperties.dateUpdated(), 150, "Updated Date");
		updatedDateColumn.setCell(new DateCell(DateTimeFormat
				.getFormat("dd/MM/yyyy HH:mm:ss")));
		ColumnConfig<QuotationRequest, String> statusColumn = new ColumnConfig<QuotationRequest, String>(
				gridProperties.status(), 100, "Status");
		ColumnConfig<QuotationRequest, String> requestModeColumn = new ColumnConfig<QuotationRequest, String>(
				gridProperties.requestMode(), 200, "Request Mode");
		ColumnConfig<QuotationRequest, String> name = new ColumnConfig<QuotationRequest, String>(
				gridProperties.name(), 150, "Name");
		ColumnConfig<QuotationRequest, String> customerNameColumn = new ColumnConfig<QuotationRequest, String>(
				gridProperties.customername(), 150, "Customer Name");

		List<ColumnConfig<QuotationRequest, ?>> allColumnConfig = new ArrayList<ColumnConfig<QuotationRequest, ?>>();

		// columns.add(idColumn);
		allColumnConfig.add(expander);
		allColumnConfig.add(name);
		allColumnConfig.add(customerNameColumn);
		allColumnConfig.add(requestDate);
		allColumnConfig.add(responseDateColumn);
		allColumnConfig.add(createdDateColumn);
		allColumnConfig.add(updatedDateColumn);
		allColumnConfig.add(statusColumn);
		allColumnConfig.add(requestModeColumn);

		ColumnModel<QuotationRequest> columnModel = new ColumnModel<QuotationRequest>(
				allColumnConfig);

		DataProxy<PagingLoadConfig, PagingLoadResult<QuotationRequest>> dataProxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<QuotationRequest>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<QuotationRequest>> callback) {
				quotationRequestService.listAll(loadConfig, callback);

			}
		};

		final PagingLoader<PagingLoadConfig, PagingLoadResult<QuotationRequest>> pagingLoader = new PagingLoader<PagingLoadConfig, PagingLoadResult<QuotationRequest>>(
				dataProxy);
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, QuotationRequest, PagingLoadResult<QuotationRequest>>(
						listStore));

		GridView<QuotationRequest> gridView = new GridView<QuotationRequest>();
		gridView.setAutoExpandColumn(statusColumn);

		final Grid<QuotationRequest> quotationRequestGrid = new Grid<QuotationRequest>(
				listStore, columnModel, gridView) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						// Only start the load after this grid attaches to DOM.
						pagingLoader.load();
					}
				});
			}
		};

		quotationRequestGrid.setPixelSize(600, 800);
		quotationRequestGrid.getView().setStripeRows(true);
		final ContentPanel gridPanel = new ContentPanel();
		gridPanel.addStyleName("margin-10");
		gridPanel.setHeadingText("Customer Grid");
		gridPanel.setHeight(600);
		gridPanel.setBorders(true);
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		gridPanel.setWidget(con);
		PagingToolBar toolBar = new PagingToolBar(50);
		toolBar.bind(pagingLoader);

		// Toolbar to edit and add

		ToolBar mainToolBar = new ToolBar();

		Button btnCreateCustomer = new Button();
		btnCreateCustomer
				.setHTML("<span class='glyphicon glyphicon-plus'></span>&nbsp;&nbsp;Add Quotation Request");
		btnCreateCustomer.setStyleName("btn btn-primary btn-sm");
		mainToolBar.add(btnCreateCustomer);

		final Button btnEditCustomer = new Button();
		btnEditCustomer
				.setHTML("<span class='glyphicon glyphicon-edit'></span>&nbsp;&nbsp;Edit Quotation Request");
		btnEditCustomer.setStyleName("btn btn-success btn-sm");

		final Button btnDeleteCustomer = new Button();
		btnDeleteCustomer
				.setHTML("<span class='glyphicon glyphicon-edit'></span>&nbsp;&nbsp;Delete Qotation Request");
		btnDeleteCustomer.setStyleName("btn btn-warning btn-sm");

		btnEditCustomer.setEnabled(false);
		btnDeleteCustomer.setEnabled(false);
		mainToolBar.add(btnEditCustomer);
		mainToolBar.add(btnDeleteCustomer);
		con.add(mainToolBar, new VerticalLayoutData(1, -1));
		con.add(quotationRequestGrid, new VerticalLayoutData(1, 1));
		con.add(toolBar, new VerticalLayoutData(1, -1));
		quotationRequestGrid.setLoader(pagingLoader);
		quotationRequestGrid
				.setSelectionModel(new GridSelectionModel<QuotationRequest>());

		quotationRequestGrid.addRowClickHandler(new RowClickHandler() {
			@Override
			public void onRowClick(RowClickEvent event) {
				btnEditCustomer.setEnabled(true);
				btnDeleteCustomer.setEnabled(true);
			}
		});

		btnEditCustomer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// show edit screen

				SimplePanel partsPanel = (SimplePanel) gridPanel.getParent()
						.getParent();

				partsPanel.remove(gridPanel.getParent());

				// add add customer screen

				com.kumar.mis.app.client.component.quotation.QuotationRequest addParts = new com.kumar.mis.app.client.component.quotation.QuotationRequest();
				addParts.initPageForEdit(quotationRequestGrid
						.getSelectionModel().getSelectedItem());

				partsPanel.add(addParts);

			}
		});

		btnCreateCustomer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// Show create screen

				SimplePanel partsPanel = (SimplePanel) gridPanel.getParent()
						.getParent();

				partsPanel.remove(gridPanel.getParent());

				// add add customer screen

				partsPanel
						.add(new com.kumar.mis.app.client.component.quotation.QuotationRequest());
			}
		});

		btnDeleteCustomer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				QuotationRequest quotationRequest = quotationRequestGrid
						.getSelectionModel().getSelectedItem();

				listStore.remove(quotationRequest);

				quotationRequestService.deleteQuotationRequestById(
						quotationRequest.getId(), new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert(" Error while deleting record");
							}

							@Override
							public void onSuccess(Void result) {
								RootPanel
										.get("alerts")
										.add(new SuccessAlert(
												"Customer deleted Successfully "));

							}
						});

			}
		});

		expander.initPlugin(quotationRequestGrid);

		initWidget(gridPanel);

	}

}
