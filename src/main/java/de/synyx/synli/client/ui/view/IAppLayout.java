package de.synyx.synli.client.ui.view;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface IAppLayout extends IsWidget {
	
	AcceptsOneWidget getContentPanel();
	
	AcceptsOneWidget getWestPanel();
	
}
