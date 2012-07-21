package de.synyx.synli.client.presenter.impl;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;

import de.synyx.synli.client.ioc.ClientFactory;
import de.synyx.synli.client.place.BookEditPlace;
import de.synyx.synli.client.place.BookListPlace;
import de.synyx.synli.client.presenter.IMenuPresenter;
import de.synyx.synli.client.ui.view.IMenuView;
import de.synyx.synli.client.ui.view.impl.AppLayout;

public class MenuPresenterImpl implements IMenuPresenter, PlaceChangeEvent.Handler {

	private final PlaceController placeController;
	private final IMenuView view;

	public MenuPresenterImpl(final AppLayout layout, ClientFactory clientFactory) {
		placeController = clientFactory.getPlaceController();
		clientFactory.getEventBus().addHandler(PlaceChangeEvent.TYPE, this);

		this.view = clientFactory.getMenuView();
		this.view.setPresenter(this);
	}

	@Override
	public void onListBooksClicked() {
		placeController.goTo(new BookListPlace());
	}

	@Override
	public void onCreateBookClicked() {
		placeController.goTo(new BookEditPlace());
	}

	@Override
	public void onPlaceChange(PlaceChangeEvent event) {
		Place newPlace = event.getNewPlace();
		
		if (newPlace instanceof BookListPlace) {
			view.setListBooksActive();
			
		} else if (newPlace instanceof BookEditPlace) {
			view.setCreateBookActive();
			
		}
	}
	
}
