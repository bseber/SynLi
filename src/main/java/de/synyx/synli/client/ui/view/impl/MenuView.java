package de.synyx.synli.client.ui.view.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

import de.synyx.synli.client.presenter.IMenuPresenter;
import de.synyx.synli.client.ui.view.IMenuView;

public class MenuView extends Composite implements IMenuView {

	private static MenuViewUiBinder uiBinder = GWT.create(MenuViewUiBinder.class);

	public interface MenuViewStyle extends CssResource {
		
		/**
		 * Style to indicate the current active page in the menu bar
		 * 
		 * @return
		 */
		String activeLink();
		
	}
	
	interface MenuViewUiBinder extends UiBinder<Widget, MenuView> {
	}

	@UiField
	MenuItem btnList;
	
	@UiField
	MenuItem btnCreate;

	@UiField
	MenuViewStyle style;
	
	private IMenuPresenter presenter;
	
	public MenuView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		btnList.setScheduledCommand(new ScheduledCommand() {
			@Override
			public void execute() {
				presenter.onListBooksClicked();
			}
		});
		
		btnCreate.setScheduledCommand(new ScheduledCommand() {
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

	@Override
	public void setListBooksActive() {
		btnList.addStyleName(style.activeLink());
		btnCreate.removeStyleName(style.activeLink());
	}

	@Override
	public void setCreateBookActive() {
		btnList.removeStyleName(style.activeLink());
		btnCreate.addStyleName(style.activeLink());
	}

}
