package edu.icet.service.custom.impl;

import edu.icet.model.dto.Book;
import edu.icet.service.custom.BookService;
import edu.icet.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    public List<Book> getall() throws SQLException {

        List<Book> books = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM book");
        while (resultSet.next())
            books.add(new Book(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)

            ));
            return books;
        }

    @Override
    public Boolean addbook(Book book) throws SQLException {
        return null;
    }

    @Override
    public Boolean updateCustomer(Book book) {
        return null;
    }

    @Override
    public Book searchById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return List.of();
    }

    @Override
    public List<String> getBookIds() throws SQLException {
        return List.of();
    }

}


