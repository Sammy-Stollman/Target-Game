module com.example.cookieclicker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.desktop;


    opens com.example.cookieclicker to javafx.fxml;
    exports com.example.cookieclicker;
}