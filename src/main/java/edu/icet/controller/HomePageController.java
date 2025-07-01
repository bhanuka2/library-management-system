package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.awt.SystemColor.window;

public class HomePageController {

    @FXML
    void btnAddBookOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddBook.fxml"))));
        stage.show();
    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddMember.fxml"))));
        stage.show();
    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/IssueBook.fxml"))));
        stage.show();
    }

    @ FXML
    void btnReturnBookOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReturnBook.fxml"))));
        stage.show();
    }

    @FXML
    void btnSearchBookOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SearchBook.fxml"))));
        stage.show();
    }


}
