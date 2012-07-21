package de.synyx.synli.client.presenter.impl;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.web.bindery.requestfactory.shared.Receiver;

import de.synyx.synli.client.ioc.ClientFactory;
import de.synyx.synli.client.place.BookEditPlace;
import de.synyx.synli.client.presenter.IBookListPresenter;
import de.synyx.synli.client.ui.view.IBookListView;
import de.synyx.synli.shared.domain.BookProxy;
import de.synyx.synli.shared.service.AppRequestFactory;
import de.synyx.synli.shared.service.BookServiceRequest;

public class BookListPresenter extends AbstractActivity implements IBookListPresenter {

	private final IBookListView view;
	private final AppRequestFactory requestFactory;
	private final PlaceController placeController;
	private final AsyncDataProvider<BookProxy> dataProvider;
	
	public BookListPresenter(ClientFactory clientFactory) {
		view = clientFactory.getBookListView();
		requestFactory = clientFactory.getAppRequestFactory();
		placeController = clientFactory.getPlaceController();
		dataProvider = createDataProvider();
	}
	
	@Override
	public void onBookMouseOver(BookProxy value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBookSelectionChange(BookProxy selectedBook) {
		if (selectedBook != null) {
			placeController.goTo(new BookEditPlace(selectedBook.getId()));
		}
	}
	
	@Override
	public void onSearchBook(String bookTitleInfix) {
		updateBookTable(0, bookTitleInfix);
	}
	
	@Override
	public void onStop() {
		view.setPresenter(null);
		dataProvider.removeDataDisplay(view.getBookTable());
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		
		// adding the dataDisplay fires a RangeChangeEvent
		dataProvider.addDataDisplay(view.getBookTable());
		
		panel.setWidget(view);
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
	
	private void updateBookTable(int offset) {
		updateBookTable(offset, "");
	}
	
	private void updateBookTable(final int offset, String bookTitleInfix) {
		
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

		if (bookTitleInfix.equals("")) {
			request.countBooks().to(countReceiver);
			request.listBooks(offset, IBookListView.DEFAULT_PAGE_SIZE).to(listReceiver);
		} else {
			request.countBooksByTitleInfix(bookTitleInfix).to(countReceiver);
			request.listBooksByTitleInfix(bookTitleInfix, offset, IBookListView.DEFAULT_PAGE_SIZE).to(listReceiver);
		}
		
		request.fire();
	}

}
