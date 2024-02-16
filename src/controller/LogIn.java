package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LogIn {
    public static String username;
    public Button btnLogin;
    public TextField userNametxt;
    public AnchorPane logInPane;


    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        username = userNametxt.getText().isEmpty() ? "Unknown" : userNametxt.getText();
        Data.username = username;
        System.out.println(Data.username);
//        loadChat();
        URL resource = getClass().getResource("../view/ChatPage.fxml");
        System.out.println("........................................................................");
        Parent load = FXMLLoader.load(resource);
        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.show();

        logInPane.getScene().getWindow().hide();

    }

}