package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.beans.Book;
import pl.coderslab.beans.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Qualifier("memoryBookService")
    @Autowired
    private BookService bookService;
    @Qualifier("dbBookService")
    @Autowired
    private BookService bookServiceDb;

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return bookService.getList();
    }

    @RequestMapping("/{bookId}")
    public Book showBook(@PathVariable long bookId) {
        return bookService.getBook(bookId);
    }

    @PostMapping("/")
    public void addBook(@RequestBody Book book) {
        bookServiceDb.addBook(book);
        bookService.addBook(book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable long bookId) {
        bookServiceDb.deleteBook(bookId);
        bookService.deleteBook(bookId);
    }

    @PutMapping("/")
    public void editBook(@RequestBody Book book) {
        bookServiceDb.editBook(book);
        bookService.editBook(book);
    }
}