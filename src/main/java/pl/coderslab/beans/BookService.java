package pl.coderslab.beans;

import java.util.List;

public interface BookService {

    List<Book> getList();

    Book getBook(long bookId);

    void addBook(Book book);

    void deleteBook(long bookId);

    void editBook(Book book);
}
