package edu.icet.service;

import edu.icet.model.Book;

import java.util.List;

public interface BookService {

    List <Book> getAll();
    Boolean AddBook (Book book);
}
