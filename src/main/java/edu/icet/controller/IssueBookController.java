package edu.icet.controller;

import edu.icet.model.dto.Book;
import edu.icet.model.dto.IssueRecord;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IssueBookController implements Initializable {

    @FXML
    private TableColumn colBookID;

    @FXML
    private TableColumn colBorrowDate;

    @FXML
    private TableColumn colFine;

    @FXML
    private TableColumn colRecordID;

    @FXML
    private TableColumn colReturnDate;

    @FXML
    private TableColumn colUserID;

    @FXML
    private TableView tblIssueRecord;

    @FXML
    private TextField txtBookID;

    @FXML
    private Label txtBorrowDate;

    @FXML
    private TextField txtFine;

    @FXML
    private TextField txtRecordID;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private TextField txtUserID;

    List<IssueRecord> IssueList = new ArrayList<>();
    @FXML
    void btnsaveOnAction(ActionEvent event) {


        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/readhub","root","1234");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO issue-table VALUES(?,?,?,?,?,?)");

        preparedStatement.setInt(1,Integer.parseInt(txtRecordID.getText()));
        preparedStatement.setInt(2,Integer.parseInt(txtUserID.getText()));
        preparedStatement.setString(3,txtBookID.getText());
        preparedStatement.setString(4,txtBorrowDate.getText());
        preparedStatement.setString(5, (txtReturnDate.getText()));
        preparedStatement.setString(6,txtFine.getText());


        if(txtUserID.getText().isEmpty() || txtRecordID.getText().isEmpty() || txtBookID.getText().isEmpty() || txtBorrowDate.getText().isEmpty() || txtReturnDate.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please fill in all fields.").show();
            return;
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();
        }

        int i = preparedStatement.executeUpdate();
        LoadTable();
       // clearFields();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void LoadTable() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM issue-table");

        while (resultSet.next()) {
            IssueList.add(new IssueRecord(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }

        colRecordID.setCellValueFactory(new PropertyValueFactory<>("RecordID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("BorrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));




        ObservableList<IssueRecord> issuebookObservableList = FXCollections.observableArrayList();
        IssueList.forEach(IssueRecord -> issuebookObservableList.add(IssueRecord));

        tblIssueRecord.setItems(issuebookObservableList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            LoadTable();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
