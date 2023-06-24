package com.example.appclientserver;

import javafx.fxml.FXML;

public class MianController {
    private String s;
    @FXML
    CONServer conServer;

    MianController(String s){this.s=s;}



    public String loadText() {
        return conServer.user.getText();
    }
}
