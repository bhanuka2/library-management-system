package edu.icet.service.custom.impl;

import edu.icet.model.dto.Book;
import edu.icet.model.entity.BookEntity;
import edu.icet.repository.DAOFactory;
import edu.icet.repository.custom.BookRepository;
import edu.icet.service.custom.BookService;
import edu.icet.util.CrudUtil;
import edu.icet.util.RepositoryType;
import org.modelmapper.ModelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    BookRepository bookRepository = DAOFactory.getInstance().getRepositoryType(RepositoryType.Book);

    @Override
    public Boolean addbook(Book book) throws SQLException {
        BookEntity entity = new ModelMapper().map(book, BookEntity.class);
        return bookRepository.add(entity);
    }

    @Override
    public Boolean updateCustomer(Book book) {
        return null;
    }

    @Override
    public Book searchById(String isbn) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM book WHERE ISBN=?", isbn);
        if (resultSet.next()){
            Book book =new Book();
            book.setISBN(resultSet.getString("ISBN"));
            book.setTitle(resultSet.getString("Title"));
            book.setAuthor(resultSet.getString("Author"));
            book.setCategory(resultSet.getString("Category"));
            book.setQuantity(resultSet.getInt("Quantity"));
            return book;
        }
        return null;
    }



    @Override
    public List<Book> getAll() throws SQLException {
        List<BookEntity> entities = bookRepository.getAll();
        return entities.stream()
                .map(entity -> new ModelMapper().map(entity, Book.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBookIds() throws SQLException {
        return List.of();
    }

    @Override
    public List<Book> deleteBooks(String id) throws SQLException {
        CrudUtil.execute("delete from book where ISBN=?", id);
        List<Book> bookList = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute("select * from book")) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setISBN(resultSet.getString("ISBN"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setCategory(resultSet.getString("category"));
                book.setQuantity(resultSet.getInt("quantity"));

                bookList.add(book);
            }
        }
        return bookList;
    }
}
