package edu.icet.service.custom;

import edu.icet.model.dto.Book;
import edu.icet.service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface BookService extends SuperService {

    Boolean addbook(Book book) throws SQLException;
    Boolean updateCustomer(Book book);
    Book searchById(String id) throws SQLException;
    List<Book> getAll() throws SQLException;

    List<String> getBookIds() throws SQLException;

}
