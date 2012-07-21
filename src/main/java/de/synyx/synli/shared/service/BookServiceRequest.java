package de.synyx.synli.shared.service;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import de.synyx.synli.server.domain.Book;
import de.synyx.synli.shared.domain.BookProxy;

@Service(value = Book.class)
public interface BookServiceRequest extends RequestContext {
	
	Request<Integer> countBooks();
	
	Request<Integer> countBooksByTitleInfix(String bookTitleInfix);
	
	Request<BookProxy> findBook(Long bookId);
	
	Request<List<BookProxy>> listBooks(int offset, int limit);
	
	Request<List<BookProxy>> listBooksByTitleInfix(String titleInfix, int offset, int limit);
	
	Request<Long> save(BookProxy book);

}
