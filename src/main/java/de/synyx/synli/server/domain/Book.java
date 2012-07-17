package de.synyx.synli.server.domain;

import java.util.ArrayList;
import java.util.List;

public class Book {

	private static List<Book> STORE = new ArrayList<Book>();
	
	private static Long LAST_ADDED_ID = 0L;

	static {
		save(new Book("Buch 1", "oreilly", "Max Mustermann"));
		save(new Book("Buch 2", "oreilly", "Max Mustermann"));
		save(new Book("Buch 3", "springer", "Max Mustermann"));
		save(new Book("Buch 4", "springer", "Max Mustermann"));
		save(new Book("Buch 5", "springer", "Max Mustermann"));
		save(new Book("Buch 6", "oreilly", "Max Mustermann"));
		save(new Book("Buch 7", "springer", "Max Mustermann"));
		save(new Book("Buch 8", "springer", "Max Mustermann"));
		save(new Book("Buch 10", "springer", "John Doe"));
		save(new Book("Buch 11", "springer", "John Doe"));
		save(new Book("Buch 12", "oreilly", "John Doe"));
		save(new Book("Buch 13", "oreilly", "John Doe"));
		save(new Book("Buch 14", "springer", "John Doe"));
	}
	
	public static Integer countBooks() {
		return STORE.size();
	}
	
	public static Book findBook(Long id) {
		for (Book b : STORE) {
			if (b.getId().equals(id)) {
				return b;
			}
		}
		return null;
	}
	
	public static Long save(Book book) {
		
		long id = book.getId() == null ? ++LAST_ADDED_ID : book.getId();
		
		book.setId(id);
		book.increaseVersion();
		
		int indexOf = STORE.indexOf(book);
		if (indexOf == -1) {
			STORE.add(book);
		} else {
			STORE.set(indexOf, book);
		}
		
		return id;
	}
	
	public static List<Book> listBooks(int offset, int limit) {
		int added = 0;
		List<Book> resultList = new ArrayList<Book>(limit);
		for (int i = 0; i < STORE.size() && added < limit; i++) {
			if (i >= offset) {
				resultList.add(STORE.get(i));
				added++;
			}
		}
		return resultList;
	}
	
	private Long id;
	private Integer version;
	private String title;
	private String isbn;
	private String amazonLink;
	private String publisher;
	private String author;

	public Book() {
	}
	
	private Book(String title, String publisher, String author) {
		this.title = title;
		this.publisher = publisher;
		this.author = author;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	private void increaseVersion() {
		if (version == null) {
			version = 1;
		} else {
			version++;
		}
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAmazonLink() {
		return amazonLink;
	}

	public void setAmazonLink(String amazonLink) {
		this.amazonLink = amazonLink;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
