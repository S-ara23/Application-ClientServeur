package com.example.appclientserver;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatClient  extends CONServer implements Initializable {


    @FXML
    public Label labelUser;
    public static Label User_label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User_label=labelUser;


    }

    @FXML
    public void OnSubmit() throws IOException {

        HelloApplication app = new HelloApplication();
        Stage stageSB = (Stage) labelUser.getScene().getWindow();
        FXMLLoader fxSB = new FXMLLoader(HelloApplication.class.getResource("CONServer.fxml"));

        Scene sceneSB = new Scene(fxSB.load());
        stageSB.setScene(sceneSB);
        user_label.setText(labelUser.getText());


    }



    @FXML
    protected  void ONLink(ActionEvent event)throws IOException {
        HelloApplication app = new HelloApplication();
        Stage stageSB = (Stage) labelUser.getScene().getWindow();
        FXMLLoader fxSB = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));

        Scene sceneSB = new Scene(fxSB.load());
        stageSB.setScene(sceneSB);

    }


}
