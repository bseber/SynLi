package de.synyx.synli.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;

import de.synyx.synli.client.ioc.AppPlaceHistoryMapper;
import de.synyx.synli.client.ioc.ClientFactory;
import de.synyx.synli.client.ioc.ClientFactoryImpl;
import de.synyx.synli.client.ioc.ContentActivityMapper;
import de.synyx.synli.client.ioc.WestActivityMapper;
import de.synyx.synli.client.place.BookListPlace;
import de.synyx.synli.client.presenter.IMenuPresenter;
import de.synyx.synli.client.presenter.impl.MenuPresenterImpl;
import de.synyx.synli.client.ui.view.impl.AppLayout;

public class SynLib implements EntryPoint {
	
	private final ClientFactory clientFactory = GWT.create(ClientFactoryImpl.class);
	private final BookListPlace defaultPlace = new BookListPlace();
	
	public void onModuleLoad() {
		
		EventBus eventBus = clientFactory.getEventBus();
		
		AppLayout layout = new AppLayout(clientFactory);
		
		ContentActivityMapper contentActivityMapper = new ContentActivityMapper(clientFactory);
		ActivityManager contentActivityManager = new ActivityManager(contentActivityMapper, eventBus);
		contentActivityManager.setDisplay(layout.getContentPanel());
		
		WestActivityMapper westActivityMapper = new WestActivityMapper(clientFactory);
		ActivityManager westActivityManager = new ActivityManager(westActivityMapper, eventBus);
		westActivityManager.setDisplay(layout.getWestPanel());
		
		AppPlaceHistoryMapper placeHistoryMapper = GWT.create(AppPlaceHistoryMapper.class);
		
		PlaceController placeController = clientFactory.getPlaceController();
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(placeHistoryMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);
		
		RootLayoutPanel.get().add(layout);
		IMenuPresenter menuPresenter = new MenuPresenterImpl(layout, clientFactory);

		historyHandler.handleCurrentHistory();
	}
	
}
