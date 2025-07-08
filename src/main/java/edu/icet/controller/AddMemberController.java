package edu.icet.controller;

import edu.icet.model.dto.User;
import edu.icet.util.CrudUtil;
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

    @FXML
    void btnAddOnAction(ActionEvent event) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/readhub", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user values(?,?,?,?)");

            preparedStatement.setInt(1, Integer.parseInt(txtUserid.getText()));
            preparedStatement.setString(2, txtName.getText());
            preparedStatement.setString(3, txtContact.getText());
            preparedStatement.setString(4, txtDate.getText());

            if(txtUserid.getText().isEmpty() || txtName.getText().isEmpty() || txtContact.getText().isEmpty() || txtDate.getText().isEmpty()){
                new Alert(Alert.AlertType.WARNING,"Please fill in all fields.").show();
                return;
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();
            }

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Loadtable();
        clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        Loadtable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    private void clear(){

        txtUserid.clear();
        txtName.clear();
        txtDate.clear();;
        txtContact.clear();
    }

    private void Loadtable() {

        colID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact_Information"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Membership_Date"));

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user");

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)

                ));
            }

            ObservableList<User> userObservableList = FXCollections.observableArrayList();
            userList.forEach(User -> userObservableList.add(User));

            tblUser.setItems(userObservableList);


        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Loadtable();
    }
}
