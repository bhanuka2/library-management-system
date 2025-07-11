package edu.icet.repository.custom.impl;

import edu.icet.model.dto.Book;
import edu.icet.model.entity.BookEntity;
import edu.icet.repository.custom.BookRepository;
import edu.icet.service.custom.BookService;
import edu.icet.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryimpl implements BookRepository {

    @Override
    public boolean add(BookEntity entity) throws SQLException {
        return CrudUtil.execute("insert into book values (?,?,?,?,?)",
                entity.getISBN(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getCategory(),
                entity.getQuantity()
        );
    }

    @Override
    public boolean update(BookEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public BookEntity searchById(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM book WHERE ISBN=?", id);
        if (resultSet.next()){
            BookEntity book =new BookEntity();
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
    public List<BookEntity> getAll() throws SQLException {
        List<BookEntity> bookList = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute("select * from book")) {
            while (resultSet.next()) {
                BookEntity book = new BookEntity();
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