package de.synyx.synli.client.presenter.impl;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.requestfactory.shared.Receiver;

import de.synyx.synli.client.ioc.ClientFactory;
import de.synyx.synli.client.presenter.IBookListPresenter;
import de.synyx.synli.client.ui.view.IBookListView;
import de.synyx.synli.shared.domain.BookProxy;
import de.synyx.synli.shared.service.AppRequestFactory;
import de.synyx.synli.shared.service.BookServiceRequest;

public class BookListPresenter extends AbstractActivity implements IBookListPresenter {

	private final IBookListView view;
	private final AppRequestFactory requestFactory;
	private final AsyncDataProvider<BookProxy> dataProvider;
	private final SingleSelectionModel<BookProxy> selectionModel;
	
	public BookListPresenter(ClientFactory clientFactory) {
		view = clientFactory.getBookListView();
		requestFactory = clientFactory.getAppRequestFactory();
		dataProvider = createDataProvider();
		selectionModel = createSelectionModel();
		view.getBookTable().setSelectionModel(selectionModel);
	}
	
	private SingleSelectionModel<BookProxy> createSelectionModel() {
		SingleSelectionModel<BookProxy> sm = new SingleSelectionModel<BookProxy>(new BookProxy.KeyProvider());
		sm.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// do something...
			}
		});
		return sm;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		dataProvider.addDataDisplay(view.getBookTable());
		panel.setWidget(view);
	}
	
	@Override
	public void onStop() {
		view.setPresenter(null);
		dataProvider.removeDataDisplay(view.getBookTable());
	}
	
	private AsyncDataProvider<BookProxy> createDataProvider() {
		AsyncDataProvider<BookProxy> dataProvider = new AsyncDataProvider<BookProxy>(new BookProxy.KeyProvider()) {
			@Override
			protected void onRangeChanged(HasData<BookProxy> display) {
				int start = display.getVisibleRange().getStart();
				updateBookTable(start);
			}
		};
		return dataProvider;
	}
	
	private void updateBookTable(final int offset) {
		
		Receiver<Integer> countReceiver = new Receiver<Integer>() {
			@Override
			public void onSuccess(Integer response) {
				dataProvider.updateRowCount(response, true);
			}
		};
		
		Receiver<List<BookProxy>> listReceiver = new Receiver<List<BookProxy>>() {

			@Override
			public void onSuccess(List<BookProxy> response) {
				dataProvider.updateRowData(offset, response);
			}
		};
		
		BookServiceRequest request = requestFactory.getBookServiceRequest();
		request.countBooks().to(countReceiver);
		request.listBooks(offset, IBookListView.DEFAULT_PAGE_SIZE).to(listReceiver);
		request.fire();
	}

}
