package de.synyx.synli.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class BookEditPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<BookEditPlace> {

		@Override
		public BookEditPlace getPlace(String token) {
			Long id = null;
			try {
				id = Long.valueOf(token);
			} catch (NumberFormatException e) {
			}
			return new BookEditPlace(id);
		}

		@Override
		public String getToken(BookEditPlace place) {
			if (place.getBookId() == null) {
				return "";
			}
			return String.valueOf(place.getBookId());
		}

	}

	private Long bookId;

	/**
	 * This will create a new Book. To edit one call the constructor with a
	 * given book id
	 */
	public BookEditPlace() {
		this(null);
	}

	/**
	 * @param bookId
	 *            the id of the book which will be edited
	 */
	public BookEditPlace(Long bookId) {
		this.bookId = bookId;
	}

	public Long getBookId() {
		return bookId;
	}

}
