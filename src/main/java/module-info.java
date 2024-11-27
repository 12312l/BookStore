module com.javanc.fruitmarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.javanc to javafx.fxml;
    opens com.javanc.controller to javafx.fxml;
    opens com.javanc.DTO to javafx.base;
    exports com.javanc;
    exports com.javanc.controller;
}