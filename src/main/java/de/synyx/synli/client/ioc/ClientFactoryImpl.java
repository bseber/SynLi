package de.synyx.synli.client.ioc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import de.synyx.synli.client.ui.view.IBookEditView;
import de.synyx.synli.client.ui.view.IBookListView;
import de.synyx.synli.client.ui.view.impl.BookEditView;
import de.synyx.synli.client.ui.view.impl.BookListView;
import de.synyx.synli.client.ui.view.impl.MenuView;
import de.synyx.synli.shared.service.AppRequestFactory;

public class ClientFactoryImpl implements ClientFactory {

	private static final ClientFactory INSTANCE = new ClientFactoryImpl();
	
	private EventBus eventBus;
	
	private AppRequestFactory requestFactory;

	private PlaceController placeController;
	
	private MenuView menuView = new MenuView();
	
	private IBookEditView bookView;
	
	private IBookListView bookListView;
	
	private ClientFactoryImpl() {
		eventBus = new SimpleEventBus();
		requestFactory = GWT.create(AppRequestFactory.class);
		requestFactory.initialize(eventBus);
		placeController = new PlaceController(eventBus);
	}
	
	public static ClientFactory instance() {
		return INSTANCE;
	}
	
	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
	
	@Override
	public AppRequestFactory getAppRequestFactory() {
		return requestFactory;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public IBookEditView getBookEditView() {
		if (bookView == null) {
			bookView = new BookEditView();
			bookView.setClientFactory(this);
		}
		return bookView;
	}

	@Override
	public IBookListView getBookListView() {
		if (bookListView == null) {
			bookListView = new BookListView();
			bookListView.setClientFactory(this);
		}
		return bookListView;
	}

	@Override
	public MenuView getMenuView() {
		return menuView;
	}
	
}
