package de.synyx.synli.client.ui.view.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;

import de.synyx.synli.client.ioc.ClientFactory;
import de.synyx.synli.client.presenter.IBookListPresenter;
import de.synyx.synli.client.ui.view.IBookListView;
import de.synyx.synli.shared.domain.BookProxy;

public class BookListView extends Composite implements IBookListView {

	private static BookListViewUiBinder uiBinder = GWT.create(BookListViewUiBinder.class);

	interface BookListViewUiBinder extends UiBinder<Widget, BookListView> {
	}
	
	@UiField(provided = true)
	CellTable<BookProxy> bookTable;
	
	@UiField(provided = true)
	SimplePager bookTablePager;
	
	private ClientFactory clientFactory;
	private IBookListPresenter presenter;

	public BookListView() {
		bookTable = createBookTable();
		bookTablePager = createBookTablePager(bookTable);
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public void setClientFactory(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void setPresenter(IBookListPresenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public HasData<BookProxy> getBookTable() {
		return bookTable;
	}
	
	private CellTable<BookProxy> createBookTable() {
		CellTable<BookProxy> table = new CellTable<BookProxy>(10, new BookProxy.KeyProvider());
		
		table.addColumn(new TextColumn<BookProxy>() {
			@Override
			public String getValue(BookProxy object) {
				return object.getTitle();
			}
		}, "Titel");
		
		table.addColumn(new TextColumn<BookProxy>() {
			@Override
			public String getValue(BookProxy object) {
				return object.getPublisher();
			}
		}, "Verlag");
		
		table.addColumn(new TextColumn<BookProxy>() {
			@Override
			public String getValue(BookProxy object) {
				return object.getAuthor();
			}
		}, "Autor");
		
		return table;
	}

	private SimplePager createBookTablePager(CellTable<BookProxy> bookTable) {
		SimplePager pager = new SimplePager();
		pager.setDisplay(bookTable);
		// rangeLimited(true) would force DEFAULT_PAGE_SIZE visible items...
		pager.setRangeLimited(false);
		pager.setPageSize(DEFAULT_PAGE_SIZE);
		return pager;
	}

}
