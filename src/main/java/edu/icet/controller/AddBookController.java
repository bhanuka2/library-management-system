package edu.icet.controller;

import edu.icet.model.dto.Book;
import edu.icet.service.custom.impl.BookServiceImpl;
import edu.icet.service.custom.BookService;
import edu.icet.util.CrudUtil;
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
        LoadTable();
        clearFields();


    }


    private void LoadTable() throws SQLException {
       // BookService service = new BookServiceImpl();
       // List<Book> all = service.getAll();

        ObservableList<Book> bookObservableList = FXCollections.observableArrayList();
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM book");

            while (resultSet.next()) {
                bookObservableList.add(new Book(

                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)

            ));
        }

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
        int selectedID = tblBooks.getSelectionModel().getSelectedIndex();
        tblBooks.getItems().remove(selectedID);

    }
}

