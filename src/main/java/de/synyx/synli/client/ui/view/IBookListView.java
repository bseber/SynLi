package de.synyx.synli.client.ui.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;

import de.synyx.synli.client.presenter.IBookListPresenter;
import de.synyx.synli.shared.domain.BookProxy;

public interface IBookListView extends IsWidget {
	
	static final int DEFAULT_PAGE_SIZE = 10;
	
	void setPresenter(IBookListPresenter presenter);

	HasData<BookProxy> getBookTable();

}
