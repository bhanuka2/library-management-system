package edu.icet.controller;

import edu.icet.model.dto.User;
import edu.icet.service.ServiceFactory;
import edu.icet.service.SuperService;
import edu.icet.service.custom.UserService;
import edu.icet.util.CrudUtil;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddMemberController implements Initializable {

    @FXML
    private TableView tblUser;

    @FXML
    private TableColumn colContact;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUserid;

    List<User> userList = new ArrayList<>();

    UserService userService= ServiceFactory.getInstance().getServiceType(ServiceType.User);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if (txtUserid.getText().isEmpty() || txtName.getText().isEmpty() ||
                    txtContact.getText().isEmpty() || txtDate.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return;
            }

            if (!txtDate.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
                new Alert(Alert.AlertType.ERROR, "Date must be in YYYY-MM-DD format").show();
                return;
            }

            Integer userid = Integer.valueOf(txtUserid.getText());
            String name = txtName.getText();
            String contact = txtContact.getText();
            Date date = Date.valueOf(txtDate.getText());

            User user = new User(userid, name, contact, date);
            Boolean result = userService.addUser(user);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();
                clear();
                Loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add user").show();
            }
        } catch (SQLException e) {
        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String id = txtUserid.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter User ID to delete").show();
            return;
        }

        boolean result = userService.deleteUser(id);
        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "User Deleted Successfully").show();
            clear();
            Loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete user").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtUserid.getText();
        User user = userService.searchById(id);
        if (user != null) {
            txtName.setText(user.getName());
            txtContact.setText(user.getContact_Information());
            txtDate.setText(user.getMembership_Date().toString());
        } else {
            new Alert(Alert.AlertType.ERROR, "User not found").show();
        }
    }

    private void clear(){

        txtUserid.clear();
        txtName.clear();
        txtDate.clear();;
        txtContact.clear();
    }

    private void Loadtable() throws SQLException {
        List<User> all = userService.getAll();
        ObservableList<User> userObservableList = FXCollections.observableArrayList(all);
        tblUser.setItems(userObservableList);
        colID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_Information"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("membership_Date"));
        tblUser.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
