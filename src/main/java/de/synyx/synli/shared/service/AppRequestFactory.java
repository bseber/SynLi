package de.synyx.synli.shared.service;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface AppRequestFactory extends RequestFactory {
	
	BookServiceRequest getBookServiceRequest();

}
