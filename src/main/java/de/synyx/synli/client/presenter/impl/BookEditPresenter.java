package de.synyx.synli.client.presenter.impl;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.RequestContext;

import de.synyx.synli.client.ioc.ClientFactory;
import de.synyx.synli.client.place.BookEditPlace;
import de.synyx.synli.client.place.BookListPlace;
import de.synyx.synli.client.presenter.IBookEditPresenter;
import de.synyx.synli.client.ui.view.IBookEditView;
import de.synyx.synli.client.ui.view.impl.BookEditView;
import de.synyx.synli.shared.domain.BookProxy;
import de.synyx.synli.shared.service.AppRequestFactory;
import de.synyx.synli.shared.service.BookServiceRequest;

public class BookEditPresenter extends AbstractActivity implements IBookEditPresenter {

	private final ClientFactory clientFactory;
	private final AppRequestFactory requestFactory;
	private final IBookEditView view;
	private final BookEditPlace bookEditPlace;
	
	private RequestFactoryEditorDriver<BookProxy, BookEditView> editorDriver;
	
	public BookEditPresenter(BookEditPlace bookEditPlace, ClientFactory clientFactory) {
		this.bookEditPlace = bookEditPlace;
		this.clientFactory = clientFactory;
		this.requestFactory = clientFactory.getAppRequestFactory();
		this.view = clientFactory.getBookEditView();
		this.view.setPresenter(this);
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		Long bookId = bookEditPlace.getBookId();
		
		if (bookId == null) {
			startEditing(null);
			
		} else {
			requestFactory.getBookServiceRequest().findBook(bookEditPlace.getBookId()).fire(new Receiver<BookProxy>() {
				@Override
				public void onSuccess(BookProxy response) {
					startEditing(response);
				}
			});
		}
		
		
		panel.setWidget(view);
	}
	
	@Override
	public void onStop() {
		view.setPresenter(null);
	}

	@Override
	public void onSaveClicked() {
		save();
	}
	
	private void onSaveSuccess(Long bookId) {
		clientFactory.getPlaceController().goTo(new BookListPlace());
	}
	
	private void startEditing(BookProxy book) {
		BookServiceRequest serviceRequest = requestFactory.getBookServiceRequest();
		BookProxy bookToEdit = book == null ? serviceRequest.create(BookProxy.class) : serviceRequest.edit(book);
		
		editorDriver = view.getEditorDriver();
		
		serviceRequest.save(bookToEdit).with(editorDriver.getPaths()).to(new Receiver<Long>() {
			@Override
			public void onSuccess(Long response) {
				onSaveSuccess(response);
			}
		});
		
		editorDriver.edit(bookToEdit, serviceRequest);
		editorDriver.flush();
	}

	private void save() {
		RequestContext context = editorDriver.flush();
		if (editorDriver.hasErrors()) {
			Window.alert("error");
			return;
		}
		if (!context.isChanged()) {
			Window.alert("nothing changed");
			return;
		}
		context.fire();
	}
	
}
