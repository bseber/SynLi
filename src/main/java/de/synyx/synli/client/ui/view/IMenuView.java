package de.synyx.synli.client.ui.view;

import com.google.gwt.user.client.ui.IsWidget;

import de.synyx.synli.client.presenter.IMenuPresenter;

public interface IMenuView extends IsWidget {

	void setPresenter(IMenuPresenter presenter);

}
