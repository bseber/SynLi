package de.synyx.synli.client.ui.view.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import de.synyx.synli.client.ioc.ClientFactory;
import de.synyx.synli.client.ui.view.IAppLayout;

public class AppLayout extends Composite implements IAppLayout {

	private static AppLayoutUiBinder uiBinder = GWT.create(AppLayoutUiBinder.class);

	interface AppLayoutUiBinder extends UiBinder<Widget, AppLayout> {
	}
	
	@UiField
	SimplePanel westPanel;
	
	@UiField
	SimplePanel contentPanel;
	
	@UiField(provided=true)
	MenuView menuView;

	public AppLayout(ClientFactory clientFactory) {
		this.menuView = clientFactory.getMenuView();
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public AcceptsOneWidget getContentPanel() {
		return contentPanel;
	}
	
	@Override
	public AcceptsOneWidget getWestPanel() {
		return westPanel;
	}
	
}
