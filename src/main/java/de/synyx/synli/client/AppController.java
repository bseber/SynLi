package de.synyx.synli.client;

import com.google.gwt.place.shared.PlaceController;

import de.synyx.synli.client.ioc.ClientFactory;
import de.synyx.synli.client.place.BookEditPlace;
import de.synyx.synli.client.place.BookListPlace;
import de.synyx.synli.client.presenter.IMenuPresenter;
import de.synyx.synli.client.ui.view.IMenuView;
import de.synyx.synli.client.ui.view.impl.AppLayout;

public class AppController implements IMenuPresenter {

	private final PlaceController placeController;
	
	public AppController(final AppLayout layout, ClientFactory clientFactory) {
		placeController = clientFactory.getPlaceController();
		
		IMenuView menuView = clientFactory.getMenuView();
		menuView.setPresenter(this);
	}
	
	@Override
	public void onListBooksClicked() {
		placeController.goTo(new BookListPlace());
	}

	@Override
	public void onCreateBookClicked() {
		placeController.goTo(new BookEditPlace());
	}
	
}
