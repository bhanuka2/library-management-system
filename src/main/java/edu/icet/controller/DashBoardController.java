package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardController {

    @FXML
    private AnchorPane root;


    @FXML
    void btnAddMemberOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/AddMember.fxml");

        assert resource != null;

        Parent load = FXMLLoader.load(resource);
        this.root.getChildren().clear();
        this.root.getChildren().add(load);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {

        URL resource = this.getClass().getResource("/view/AddBook.fxml");

        assert resource != null;

        Parent load = FXMLLoader.load(resource);
        this.root.getChildren().clear();
        this.root.getChildren().add(load);

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws IOException {

        URL resource = this.getClass().getResource("/view/SearchBook.fxml");

        assert resource != null;

        Parent load = FXMLLoader.load(resource);
        this.root.getChildren().clear();
        this.root.getChildren().add(load);

    }


    @FXML
    void btnIssueOnAction(ActionEvent event) throws IOException {

        URL resource = this.getClass().getResource("/view/IssueBook.fxml");

        assert resource != null;

        Parent load = FXMLLoader.load(resource);
        this.root.getChildren().clear();
        this.root.getChildren().add(load);

    }


    @FXML
    void btnReturnOnAction(ActionEvent event) throws IOException {

        URL resource = this.getClass().getResource("/view/ReturnBook.fxml");

        assert resource != null;

        Parent load = FXMLLoader.load(resource);
        this.root.getChildren().clear();
        this.root.getChildren().add(load);

    }

    @FXML
    void btnExitOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

}