package de.synyx.synli.shared.domain;

import com.google.gwt.view.client.ProvidesKey;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import de.synyx.synli.server.domain.Book;
import de.synyx.synli.shared.domain.Rating;

@ProxyFor(value = Book.class)
public interface BookProxy extends EntityProxy {

	public static class KeyProvider implements ProvidesKey<BookProxy> {

		@Override
		public Object getKey(BookProxy item) {
			return item.getId();
		}
		
	}
	
	Long getId();
	
	Integer getVersion();
	
	String getIsbn();
	void setIsbn(String isbn);
	
	String getTitle();
	void setTitle(String title);
	
	String getAmazonLink();
	void setAmazonLink(String amazonLink);
	
	String getAuthor();
	void setAuthor(String author);
	
	String getPublisher();
	void setPublisher(String publisher);
	
	Rating getRating();
	void setRating(Rating rating);

}
