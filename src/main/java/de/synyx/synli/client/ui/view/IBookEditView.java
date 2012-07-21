package de.synyx.synli.client.ui.view;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

import de.synyx.synli.client.presenter.IBookEditPresenter;
import de.synyx.synli.client.ui.view.impl.BookEditView;
import de.synyx.synli.shared.domain.BookProxy;

public interface IBookEditView extends IsWidget, Editor<BookProxy> {

	RequestFactoryEditorDriver<BookProxy, BookEditView> getEditorDriver();
	
	void setPresenter(IBookEditPresenter presenter);

}
