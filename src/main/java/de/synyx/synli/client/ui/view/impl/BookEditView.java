package de.synyx.synli.client.ui.view.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

import de.synyx.synli.client.presenter.IBookEditPresenter;
import de.synyx.synli.client.ui.resources.AppResources;
import de.synyx.synli.client.ui.view.IBookEditView;
import de.synyx.synli.client.ui.widgets.RatingWidget;
import de.synyx.synli.shared.domain.BookProxy;

public class BookEditView extends Composite implements IBookEditView {

	private static BookEditViewUiBinder uiBinder = GWT.create(BookEditViewUiBinder.class);

	interface Driver extends RequestFactoryEditorDriver<BookProxy, BookEditView> {
	}
	
	interface BookEditViewUiBinder extends UiBinder<Widget, BookEditView> {
	}
	
	@UiField
	TextBox title;
	
	@UiField
	TextBox isbn;
	
	@UiField
	TextBox publisher;
	
	@UiField
	TextBox author;
	
	@UiField
	TextBox amazonLink;
	
	@UiField(provided = true)
	RatingWidget rating;
	
	private Driver editorDriver;
	private IBookEditPresenter presenter;

	public BookEditView(AppResources resources) {
		rating = new RatingWidget(resources.star(), resources.emptyStar());
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(IBookEditPresenter presenter) {
		this.presenter = presenter;
	}
	
	public Driver getEditorDriver() {
		if (editorDriver == null) {
			editorDriver = GWT.create(Driver.class);
		}
		editorDriver.initialize(this);
		return editorDriver;
	}
	
	@UiHandler("btnSave")
	void onSaveClicked(ClickEvent event) {
		presenter.onSaveClicked();
	}
	
}