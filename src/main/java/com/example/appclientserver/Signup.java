package com.example.appclientserver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Signup {
    static final String url="jdbc:mysql://localhost/utilisateur";

    @FXML
    private TextField username;
    @FXML
    private PasswordField pwd;
    @FXML
    private PasswordField ConfirmPwd;
    @FXML
    private DatePicker DateBirth;

    @FXML
    protected void onButtonSignup(ActionEvent event) throws IOException {
        HelloApplication app = new HelloApplication();

        if (!username.getText().isEmpty() && pwd.getText().equals(ConfirmPwd.getText()) ) {
            try{
                Connection con= DriverManager.getConnection(url,"root","s@r@232001");
                Statement st=con.createStatement();
                String Query="INSERT INTO `Personne`(`Username`,`Password`,`Birthday`) VALUES (?,?,?)";
                PreparedStatement pst=con.prepareStatement(Query);
                pst.setString(1,username.getText());
                pst.setString(2,pwd.getText());
                pst.setString(3, String.valueOf(DateBirth.getValue()));
                pst.executeUpdate();

            }catch(SQLException e){
                e.printStackTrace();
            }
            Stage stageSB = (Stage) username.getScene().getWindow();
            FXMLLoader fxSB = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
            Scene sceneSB = new Scene(fxSB.load());
            stageSB.setScene(sceneSB);

        } else {
            Alert alertSB = new Alert(Alert.AlertType.ERROR);
            alertSB.setTitle("Error");
            alertSB.setContentText("username or password are not valited!");
            alertSB.show();
        }

    }
            ;
}
