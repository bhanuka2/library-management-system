package edu.icet.service;

import edu.icet.model.Book;
import edu.icet.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookController implements BookService{

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
    public List<Book> getAll() {
        return List.of();
    }

    @Override
    public Boolean AddBook(Book book) {
        return null;
    }
}


