package com.kumar.mis.app.client.component.customer.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.SimplePanel;
import com.kumar.mis.app.client.component.customer.AddCustomer;
import com.kumar.mis.app.shared.common.LoggerMessage;
import com.kumar.mis.app.shared.domain.CustomerEntity;
import com.kumar.mis.app.shared.service.CustomerService;
import com.kumar.mis.app.shared.service.CustomerServiceAsync;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class CustomerGridGxt extends Composite {

	private static final CustomerEntityProperties gridProperties = GWT
			.create(CustomerEntityProperties.class);

	private static final CustomerServiceAsync customerServiceAsync = GWT
			.create(CustomerService.class);

	public CustomerGridGxt() {
		// Create Column configs

		ListStore<CustomerEntity> listStore = new ListStore<CustomerEntity>(
				gridProperties.key());

		ColumnConfig<CustomerEntity, String> nameColumn = new ColumnConfig<CustomerEntity, String>(
				gridProperties.name(), 200, "Name");
		ColumnConfig<CustomerEntity, String> contactNumberColumn = new ColumnConfig<CustomerEntity, String>(
				gridProperties.contactNumber(), 150, "Contact Number");
		ColumnConfig<CustomerEntity, String> emailColumn = new ColumnConfig<CustomerEntity, String>(
				gridProperties.email(), 200, "Email Address");
		ColumnConfig<CustomerEntity, String> address1Column = new ColumnConfig<CustomerEntity, String>(
				gridProperties.address1(), 300, "Address 1");
		ColumnConfig<CustomerEntity, String> address3Column = new ColumnConfig<CustomerEntity, String>(
				gridProperties.address2(), 300, "Address 2");
		ColumnConfig<CustomerEntity, String> cityColumn = new ColumnConfig<CustomerEntity, String>(
				gridProperties.city(), 100, "City");
		ColumnConfig<CustomerEntity, String> stateColumn = new ColumnConfig<CustomerEntity, String>(
				gridProperties.state(), 100, "State");
		ColumnConfig<CustomerEntity, String> countryColumn = new ColumnConfig<CustomerEntity, String>(
				gridProperties.country(), 100, "Country");

		List<ColumnConfig<CustomerEntity, ?>> columns = new ArrayList<ColumnConfig<CustomerEntity, ?>>();

		columns.add(nameColumn);
		columns.add(contactNumberColumn);
		columns.add(emailColumn);
		columns.add(address1Column);
		columns.add(address3Column);
		columns.add(cityColumn);
		columns.add(stateColumn);
		columns.add(countryColumn);

		ColumnModel<CustomerEntity> columnModel = new ColumnModel<CustomerEntity>(
				columns);

		// Add data proxy and loaders

		DataProxy<PagingLoadConfig, PagingLoadResult<CustomerEntity>> dataProxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<CustomerEntity>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<CustomerEntity>> callback) {

				// greetingService.list(loadConfig, callback);

				customerServiceAsync.listAll(loadConfig, callback);

			}
		};

		final PagingLoader<PagingLoadConfig, PagingLoadResult<CustomerEntity>> pagingLoader = new PagingLoader<PagingLoadConfig, PagingLoadResult<CustomerEntity>>(
				dataProxy);
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, CustomerEntity, PagingLoadResult<CustomerEntity>>(
						listStore));

		GridView<CustomerEntity> gridView = new GridView<CustomerEntity>();
		// gridView.setAutoExpandColumn(nameColumn);

		final Grid<CustomerEntity> customerGrid = new Grid<CustomerEntity>(
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

		customerGrid.setPixelSize(600, 800);
		customerGrid.getView().setStripeRows(true);
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
				.setHTML("<span class='glyphicon glyphicon-plus'></span>&nbsp;&nbsp;Add Customer");
		btnCreateCustomer.setStyleName("btn btn-primary btn-sm");
		mainToolBar.add(btnCreateCustomer);

		final Button btnEditCustomer = new Button();
		btnEditCustomer
				.setHTML("<span class='glyphicon glyphicon-edit'></span>&nbsp;&nbsp;Edit Customer");
		btnEditCustomer.setStyleName("btn btn-success btn-sm");
	
		btnEditCustomer.setEnabled(false);
		mainToolBar.add(btnEditCustomer);
		con.add(mainToolBar, new VerticalLayoutData(1, -1));
		con.add(customerGrid, new VerticalLayoutData(1, 1));
		con.add(toolBar, new VerticalLayoutData(1, -1));
		customerGrid.setLoader(pagingLoader);
		customerGrid
				.setSelectionModel(new GridSelectionModel<CustomerEntity>());

		customerGrid.addRowClickHandler(new RowClickHandler() {
			@Override
			public void onRowClick(RowClickEvent event) {
				btnEditCustomer.setEnabled(true);
			}
		});

		btnEditCustomer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// show edit screen

				SimplePanel customer = (SimplePanel) gridPanel.getParent()
						.getParent();

				customer.remove(gridPanel.getParent());

				// add add customer screen
				
				AddCustomer addCustomer  = new AddCustomer();
				addCustomer.initPageForEdit(customerGrid.getSelectionModel().getSelectedItem());
				

				customer.add(addCustomer);

			}
		});

		btnCreateCustomer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// Show create screen

				SimplePanel customer = (SimplePanel) gridPanel.getParent()
						.getParent();

				customer.remove(gridPanel.getParent());

				// add add customer screen

				customer.add(new AddCustomer());
			}
		});

		initWidget(gridPanel);

	}

}
