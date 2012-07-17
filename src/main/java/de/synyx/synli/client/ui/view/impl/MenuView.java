package de.synyx.synli.client.ui.view.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

import de.synyx.synli.client.presenter.IMenuPresenter;
import de.synyx.synli.client.ui.view.IMenuView;

public class MenuView extends Composite implements IMenuView {

	private static MenuViewUiBinder uiBinder = GWT.create(MenuViewUiBinder.class);

	interface MenuViewUiBinder extends UiBinder<Widget, MenuView> {
	}

	@UiField
	MenuItem btnList;
	
	@UiField
	MenuItem btnCreate;

	private IMenuPresenter presenter;
	
	public MenuView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		btnList.setCommand(new Command() {
			@Override
			public void execute() {
				presenter.onListBooksClicked();
			}
		});
		
		btnCreate.setCommand(new Command() {
			@Override
			public void execute() {
				presenter.onCreateBookClicked();
			}
		});
	}

	@Override
	public void setPresenter(IMenuPresenter presenter) {
		this.presenter = presenter;
	}

}
