package edu.icet.controller;

import edu.icet.model.dto.Book;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.impl.BookServiceImpl;
import edu.icet.service.custom.BookService;
import edu.icet.util.CrudUtil;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchBookController implements Initializable {

    @FXML
    private TableColumn colAuthor;

    @FXML
    private TableColumn colCategory;

    @FXML
    private TableColumn colISBN;

    @FXML
    private TableColumn colQuantity;

    @FXML
    private TableColumn colTitle;

    @FXML
    private TableView<Book> tblBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtTitle;

    BookService bookService = ServiceFactory.getInstance().getServiceType(ServiceType.Book);


    private void populateFields(Book book) {
        txtISBN.setText(book.getISBN());
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtQuantity.setText(String.valueOf(book.getQuantity()));
        txtCategory.setText(book.getCategory());
        txtISBN.setEditable(false);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String isbn = txtISBN.getText();
        Book book = bookService.searchById(isbn);

        List<Book>books =new ArrayList<>();
            populateFields(book);
            ObservableList<Book> bookList = FXCollections.observableArrayList(books);
            bookList.add(book);
            tblBooks.setItems(bookList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tblBooks.refresh();
    }
}
