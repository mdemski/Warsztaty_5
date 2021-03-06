package pl.coderslab.dao;

import pl.coderslab.beans.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private String url;
    private String user;
    private String password;

    public BookDao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private static final String CREATE_BOOK_QUERY =
            "INSERT INTO book(title, author, isbn, type, publisher) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_BOOK_QUERY =
            "SELECT * FROM book where id = ?";
    private static final String UPDATE_BOOK_QUERY =
            "UPDATE book SET title = ?, author = ?, isbn = ?, type = ?, publisher = ? where id = ?";
    private static final String DELETE_BOOK_QUERY =
            "DELETE FROM book WHERE id = ?";
    private static final String FIND_ALL_BOOKS_QUERY =
            "SELECT * FROM book";

    public Book create(Book book) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_BOOK_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getType());
            statement.setString(5, book.getPublisher());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                book.setId(resultSet.getLong(1));
            }
            return book;
        } catch (SQLException e) {
            System.out.println("Nie można utworzyć książki.");
            return null;
        }
    }

    public Book read(long bookId) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = conn.prepareStatement(READ_BOOK_QUERY);
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setType(resultSet.getString("type"));
                book.setPublisher(resultSet.getString("publisher"));
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Nie można odczytać książki o id: " + bookId);
        }
        return null;
    }

    public void update(Book book) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_BOOK_QUERY);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getType());
            statement.setString(5, book.getPublisher());
            statement.setLong(6, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można uaktualnić książki o id: " + book.getId());
        }
    }

    public void delete(long bookId) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = conn.prepareStatement(DELETE_BOOK_QUERY);
            statement.setLong(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można usunąć książki o id: " + bookId);
        }
    }

    public List<Book> allBooks() {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("połączono do bazy");
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BOOKS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                String type = resultSet.getString("type");
                String publisher = resultSet.getString("publisher");
                book.setTitle(title);
                book.setPublisher(publisher);
                book.setId(id);
                book.setAuthor(author);
                book.setIsbn(isbn);
                book.setType(type);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie odnaleziono książek.");
        }
        return null;
    }
}
