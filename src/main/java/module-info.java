module com.example.appclientserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.appclientserver to javafx.fxml;
    exports com.example.appclientserver;
}