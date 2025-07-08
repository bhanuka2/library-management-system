package edu.icet.controller;

import edu.icet.model.dto.Book;
import edu.icet.service.custom.impl.BookServiceImpl;
import edu.icet.service.custom.BookService;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML
    private ComboBox<?> cmbISBN;


  //  @FXML
//    Book btnSearchOnAction(ActionEvent event) throws SQLException {
//        searchById();

    //    return null;
   // }

    public Book searchById(String ISBN) throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM book WHERE ISBN=?", ISBN);
        if (resultSet.next()) {
            return new Book(
                    resultSet.getString("ISBN"),
                    resultSet.getString("Title"),
                    resultSet.getString("Author"),
                    resultSet.getString("Category"),
                    resultSet.getInt("Category")
            );
        }
        return null;

    }

    List<Book>books=new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookService service = new BookServiceImpl();
       // List<Book> all = service.getAll();

        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM book");
            while (resultSet.next()) {

                    books.add(new Book(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)

                    ));

                tblBooks.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateFields(newSelection);
                    }
                });


            }



        ObservableList<Book> bookObservableList = FXCollections.observableArrayList();
            books.forEach(Book -> bookObservableList.add(Book));

            tblBooks.setItems(bookObservableList);
        } catch (SQLException e) {

        }

        }

    private void populateFields(Book book) {
        txtISBN.setText(book.getISBN());
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtQuantity.setText(String.valueOf(book.getQuantity()));
        txtCategory.setText(book.getCategory());
        txtISBN.setEditable(false);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
