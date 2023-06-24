package com.example.appclientserver;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class CONServer  implements Initializable {

    @FXML
    private TextField host;
    @FXML
    private TextField port;
    @FXML
    private ListView view;
    @FXML
    private TextField   Idmsg;

    @FXML
    public Label user;
    public static Label user_label;

    PrintWriter pw;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_label=user;

    }

    @FXML
    protected  void onLink(ActionEvent event)throws IOException {
        HelloApplication app = new HelloApplication();
        Stage stageSB = (Stage) user_label.getScene().getWindow();
        FXMLLoader fxSB = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));

        Scene sceneSB = new Scene(fxSB.load());
        stageSB.setScene(sceneSB);

    }


    @FXML
    protected void onconnect()throws Exception{
        String Host=host.getText();
        int Port=Integer.parseInt(port.getText());
        Socket  s=new Socket(Host,Port);
        InputStream is=s.getInputStream();//lire octet
        InputStreamReader isr=new InputStreamReader(is);//caractere
        BufferedReader br=new BufferedReader(isr);
        OutputStream os=s.getOutputStream();//ecrire octet
         pw=new PrintWriter(os,true);


        new Thread(()->{
            while(true){
                try{
                    String reponse =br.readLine();
                    Platform.runLater(()->{
                        view.getItems().add(reponse+"\n");

                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

@FXML
    public  void submit(){
        String message=Idmsg.getText();
        pw.println(message);
}

}
