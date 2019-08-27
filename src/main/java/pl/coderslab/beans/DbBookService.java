package pl.coderslab.beans;

import org.springframework.stereotype.Service;
import pl.coderslab.dao.BookDao;

import java.util.List;

@Service
public class DbBookService implements BookService {
    private BookDao bookDao;

    public DbBookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getList() {
        return bookDao.allBooks();
    }

    @Override
    public Book getBook(long bookId) {
        return bookDao.read(bookId);
    }

    @Override
    public void addBook(Book book) {
        bookDao.create(book);
    }

    @Override
    public void deleteBook(long bookId) {
        bookDao.delete(bookId);
    }

    @Override
    public void editBook(Book book) {
        bookDao.update(book);
    }
}
