package de.synyx.synli.client.ioc;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import de.synyx.synli.client.ui.view.IBookEditView;
import de.synyx.synli.client.ui.view.IBookListView;
import de.synyx.synli.client.ui.view.impl.MenuView;
import de.synyx.synli.shared.service.AppRequestFactory;

public interface ClientFactory {
	
	EventBus getEventBus();
	
	AppRequestFactory getAppRequestFactory();
	
	PlaceController getPlaceController();
	
	IBookEditView getBookEditView();
	
	IBookListView getBookListView();
	
	MenuView getMenuView();

	
}
