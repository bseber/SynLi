package de.synyx.synli.shared.service;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import de.synyx.synli.server.domain.Book;
import de.synyx.synli.shared.domain.BookProxy;

@Service(value = Book.class)
public interface BookServiceRequest extends RequestContext {
	
	Request<Long> save(BookProxy book);
	
	Request<BookProxy> findBook(Long bookId);
	
	Request<Integer> countBooks();
	
	Request<List<BookProxy>> listBooks(int offset, int limit);

}
