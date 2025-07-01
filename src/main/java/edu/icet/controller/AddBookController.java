package edu.icet.controller;

import edu.icet.model.Book;
import edu.icet.service.BookController;
import edu.icet.service.BookService;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

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

    private void clearFields() {
        txtISBN.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtCategory.clear();
        txtQuantity.clear();
    }

    List<Book>books=new ArrayList<>();
    public void btnAddOnAction(javafx.event.ActionEvent actionEvent) throws SQLException {


        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/readhub","root","1234");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?)");

        preparedStatement.setString(1,txtISBN.getText());
        preparedStatement.setString(2,txtTitle.getText());
        preparedStatement.setString(3,txtAuthor.getText());
        preparedStatement.setString(4,txtCategory.getText());
        preparedStatement.setInt(5,Integer.parseInt(txtQuantity.getText()));


        if(txtISBN.getText().isEmpty() || txtTitle.getText().isEmpty() || txtAuthor.getText().isEmpty() || txtCategory.getText().isEmpty() || txtQuantity.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please fill in all fields.").show();
            return;
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();
        }

        int i = preparedStatement.executeUpdate();
        clearFields();


    }


    private void LoadTable(){

        BookService service = new BookController();
        List<Book> all = service.getAll();

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
            }


        ObservableList<Book> bookObservableList = FXCollections.observableArrayList();
        books.forEach(Book -> bookObservableList.add(Book));

        tblBooks.setItems(bookObservableList);


        //            e.printStackTrace();
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Database Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Failed to load customers.");
//            alert.show();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        }
    LocalDateTime DDTT = LocalDateTime.now();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //lblDDTT.setText(String.valueOf(DDTT));

        LoadTable();
    }



    public void btnDeleteOnAction(javafx.event.ActionEvent actionEvent) {
        int selectedID = tblBooks.getSelectionModel().getSelectedIndex();
        tblBooks.getItems().remove(selectedID);

    }
}

