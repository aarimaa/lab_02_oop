module com.example.exampleoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jlayer;


    opens com.example.exampleoop2 to javafx.fxml;
    exports com.example.exampleoop2;
    exports com.example.exampleoop2.utils;
    opens com.example.exampleoop2.utils to javafx.fxml;
    exports com.example.exampleoop2.zombieDialogs;
    opens com.example.exampleoop2.zombieDialogs to javafx.fxml;
}