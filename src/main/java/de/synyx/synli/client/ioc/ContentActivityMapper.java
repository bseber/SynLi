package de.synyx.synli.client.ioc;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import de.synyx.synli.client.place.BookEditPlace;
import de.synyx.synli.client.place.BookListPlace;
import de.synyx.synli.client.presenter.impl.BookEditPresenter;
import de.synyx.synli.client.presenter.impl.BookListPresenter;

public class ContentActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;
	
	public ContentActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		
		if (place instanceof BookListPlace) {
			return new BookListPresenter(clientFactory);
		}
		
		if (place instanceof BookEditPlace) {
			return new BookEditPresenter((BookEditPlace)place, clientFactory);
		}
		
		return null;
	}

}
