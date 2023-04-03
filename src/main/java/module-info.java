module com.example.cookieclicker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.example.cookieclicker to javafx.fxml;
    exports com.example.cookieclicker;
}