package de.synyx.synli.client.ui.view.impl;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.synyx.synli.client.presenter.IBookListPresenter;
import de.synyx.synli.client.ui.resources.AppResources;
import de.synyx.synli.client.ui.view.IBookListView;
import de.synyx.synli.client.ui.widgets.RatingWidget;
import de.synyx.synli.shared.domain.BookProxy;
import de.synyx.synli.shared.domain.Rating;

public class BookListView extends Composite implements IBookListView {

	private static BookListViewUiBinder uiBinder = GWT.create(BookListViewUiBinder.class);

	interface BookListViewUiBinder extends UiBinder<Widget, BookListView> {
	}
	
	@UiField
	TextBox tbBookTitleSearch;
	
	@UiField(provided = true)
	CellTable<BookProxy> bookTable;
	
	@UiField(provided = true)
	SimplePager bookTablePager;
	
	private IBookListPresenter presenter;

	private AppResources appResources;

	public BookListView(AppResources appResources) {
		this.appResources = appResources;
		bookTable = createBookTable();
		bookTablePager = createBookTablePager(bookTable);
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public void setPresenter(IBookListPresenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public HasData<BookProxy> getBookTable() {
		return bookTable;
	}
	
	@UiHandler("tbBookTitleSearch")
	void onSearchBookTitleTyping(KeyUpEvent event) {
		String input = tbBookTitleSearch.getValue();
		presenter.onSearchBook(input);
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

		table.addColumn(new Column<BookProxy, Rating>(new RatingCell()) {
			@Override
			public Rating getValue(BookProxy object) {
				return object.getRating();
			}
		}, "Bewertung");
		
		table.addCellPreviewHandler(new CellPreviewEvent.Handler<BookProxy>() {
			@Override
			public void onCellPreview(CellPreviewEvent<BookProxy> event) {
				if ("mouseover".equals(event.getNativeEvent().getType())) {
					presenter.onBookMouseOver(event.getValue());
				}
			}
		});
		
		final SingleSelectionModel<BookProxy> selectionModel = new SingleSelectionModel<BookProxy>(new BookProxy.KeyProvider());
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				presenter.onBookSelectionChange(selectionModel.getSelectedObject());
			}
		});
		table.setSelectionModel(selectionModel);
		
		return table;
	}

	private SimplePager createBookTablePager(CellTable<BookProxy> bookTable) {
		SimplePager pager = new SimplePager();
		pager.setDisplay(bookTable);
		// rangeLimited(true) would force DEFAULT_PAGE_SIZE visible items...
		// with set to 'false' there are only two elements visible on the last
		// page, for example
		pager.setRangeLimited(false);
		pager.setPageSize(DEFAULT_PAGE_SIZE);
		return pager;
	}
	
	private class RatingCell extends AbstractCell<Rating> {

		private final RatingWidget widget = new RatingWidget(appResources.star(), appResources.emptyStar(), false);
		
		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, Rating value, SafeHtmlBuilder sb) {
			widget.setValue(value);
			SafeHtml safeHtml = SafeHtmlUtils.fromTrustedString(widget.getElement().getString());
			sb.append(safeHtml);
		}
		
	}

}
