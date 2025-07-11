package edu.icet.controller;

import edu.icet.model.dto.Book;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.impl.BookServiceImpl;
import edu.icet.service.custom.BookService;
import edu.icet.util.CrudUtil;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    BookService bookService= ServiceFactory.getInstance().getServiceType(ServiceType.Book);

    @FXML
    private Label lblDDTT;

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

    @FXML
    private Label lblDate;

    private void clearFields() {
        txtISBN.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtCategory.clear();
        txtQuantity.clear();
    }

    List<Book>books=new ArrayList<>();
    public void btnAddOnAction(javafx.event.ActionEvent actionEvent) throws SQLException {

        String isbn = txtISBN.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String category = txtCategory.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        Book book = new Book(isbn, title, author, category, quantity);
        Boolean addbook = bookService.addbook(book);

        if(txtISBN.getText().isEmpty() || txtTitle.getText().isEmpty() || txtAuthor.getText().isEmpty() || txtCategory.getText().isEmpty() || txtQuantity.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please fill in all fields.").show();
            return;
        }
        if (addbook) {
            new Alert(Alert.AlertType.CONFIRMATION, "Book Added Successfully").show();
            clearFields();
            LoadTable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to add book").show();
        }
    }


    private void LoadTable() throws SQLException {

        List<Book> all = bookService.getAll();
        ObservableList<Book> bookObservableList = FXCollections.observableArrayList(all);

        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        tblBooks.setItems(bookObservableList);
    }
    LocalDateTime DDTT = LocalDateTime.now();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        java.util.Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        String format1 = format.format(date);
        lblDate.setText(format1);
        try {
            LoadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(javafx.event.ActionEvent actionEvent) {
        Book selectedBook = tblBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            try {
                bookService.deleteBooks(selectedBook.getISBN());
                new Alert(Alert.AlertType.CONFIRMATION, "Book Deleted Successfully").show();
                LoadTable();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete book").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a book to delete").show();
        }
    }
}

