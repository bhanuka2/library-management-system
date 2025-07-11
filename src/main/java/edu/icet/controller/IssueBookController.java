package edu.icet.controller;

import edu.icet.model.dto.IssueRecord;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.IssueRecordService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueBookController {

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
    private TextField txtBorrowDate;

    @FXML
    private TextField txtFine;

    @FXML
    private TextField txtRecordID;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private TextField txtUserID;

    List<IssueRecord> IssueList = new ArrayList<>();

IssueRecordService issueRecordService = ServiceFactory.getInstance().getServiceType(ServiceType.IssueRecord);

    @FXML
    void btnsaveOnAction(ActionEvent event) throws SQLException {
        if (txtRecordID.getText().isEmpty() || txtUserID.getText().isEmpty() ||
                txtBookID.getText().isEmpty() || txtBorrowDate.getText().isEmpty() ||
                txtReturnDate.getText().isEmpty() || txtFine.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
            return;
        }
            int recordID = Integer.parseInt(txtRecordID.getText().trim());
            int userID = Integer.parseInt(txtUserID.getText().trim());
            String bookID = txtBookID.getText().trim();
            Date borrowDate = Date.valueOf(txtBorrowDate.getText().trim());
            Date returnDate = Date.valueOf(txtReturnDate.getText().trim());
            double fine = Double.parseDouble(txtFine.getText().trim());


            IssueRecord issueRecord =new IssueRecord(recordID,userID,bookID,borrowDate,returnDate,fine);
        Boolean b = issueRecordService.AddRecord(issueRecord);


        if (borrowDate.after(returnDate)) {
                new Alert(Alert.AlertType.ERROR, "Return date must be after borrow date").show();
                return;
            }

            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Issue Record Added Successfully").show();
                loadTable();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Add Issue Record").show();
            }

    }
    private void clearFields() {
        txtRecordID.clear();
        txtUserID.clear();
        txtBookID.clear();
        txtBorrowDate.clear();
        txtReturnDate.clear();
        txtFine.clear();
    }

    public void loadTable() throws SQLException {
        List<IssueRecord> allRecords =issueRecordService.getAllRecords();
        ObservableList<IssueRecord> userObservableList = FXCollections.observableArrayList(allRecords);
        tblIssueRecord.setItems(userObservableList);

        colRecordID.setCellValueFactory(new PropertyValueFactory<>("RecordID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("BorrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
        colFine.setCellValueFactory(new PropertyValueFactory<>("Fine"));
    }
}