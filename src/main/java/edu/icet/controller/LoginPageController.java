package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginPageController {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {

//        String username = txtUsername.getText();
//        String password = txtPassword.getText();
//        if (username.isEmpty() || password.isEmpty()) {
//            new Alert(Alert.AlertType.ERROR, "Input Fields Cannot be Null").show();
//        }
//        else if (username.equals("admin") && password.equals("admin")) {
//            Stage stage = new Stage();
//            URL resource = this.getClass().getResource("/view/DashBoard.fxml");
//            assert resource != null;
//            stage.setScene(new Scene(FXMLLoader.load(resource)));
//            stage.show();
//            ((Stage) txtUsername.getScene().getWindow()).close();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
//        }

        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"))));
        stage.show();

    }

}
