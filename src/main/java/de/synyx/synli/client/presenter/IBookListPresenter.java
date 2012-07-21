package de.synyx.synli.client.presenter;

import com.google.gwt.activity.shared.Activity;

import de.synyx.synli.shared.domain.BookProxy;

public interface IBookListPresenter extends Activity {

	void onBookMouseOver(BookProxy value);

	void onBookSelectionChange(BookProxy selectedBook);

	void onSearchBook(String bookTitleInfix);

}
