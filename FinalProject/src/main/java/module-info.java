module org.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;


    opens org.example.finalproject to javafx.fxml;
    exports org.example.finalproject;
}