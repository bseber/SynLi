package de.synyx.synli.client.ioc;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

	private final ContentActivityMapper contentActivityMapper;
	private final WestActivityMapper westActivityMapper;
	
	public AppActivityMapper(ClientFactory clientFactory) {
		contentActivityMapper = new ContentActivityMapper(clientFactory);
		westActivityMapper = new WestActivityMapper(clientFactory);
	}

	@Override
	public Activity getActivity(Place place) {
		
		/*
		 * for example: 
		 * if (place instanceof WestPlace) {
		 * 		return westActivityMapper.getActivity(place);
		 * }
		 */
		
		return contentActivityMapper.getActivity(place);
	}

}
