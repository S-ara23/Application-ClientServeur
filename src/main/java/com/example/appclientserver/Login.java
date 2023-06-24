package com.example.appclientserver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;

import java.io.IOException;
import java.util.ResourceBundle;

public class Login extends ChatClient implements Initializable {
    static final String url="jdbc:mysql://localhost/utilisateur";
    static final String Query="SELECT * FROM Personne";
    private boolean flag=false;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;





    @FXML
    protected void onButtonClickSB(ActionEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        try{
            Connection con= DriverManager.getConnection(url,"root","s@r@232001");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(Query);
            while(rs.next()){

                if (login.getText().equals(rs.getString("username")) && password.getText().equals(rs.getString("Password"))) {
                    Stage stageSB = (Stage) login.getScene().getWindow();
                    FXMLLoader fxSB = new FXMLLoader(HelloApplication.class.getResource("ChatClient.fxml"));
                    Scene sceneSB = new Scene(fxSB.load());
                    stageSB.setScene(sceneSB);

                    User_label.setText(login.getText());

                    flag=true;

                }
            }
            if(flag==false) {
                Alert alertSB = new Alert(Alert.AlertType.ERROR);
                alertSB.setTitle("Error");
                alertSB.setContentText("username or password are not valited!");
                alertSB.show();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    protected  void ONHyperLink(ActionEvent event)throws IOException {
        HelloApplication app = new HelloApplication();

            Stage stageSB = (Stage) login.getScene().getWindow();
            FXMLLoader fxSB = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));
            Scene sceneSB = new Scene(fxSB.load());
            stageSB.setScene(sceneSB);

    }


}
