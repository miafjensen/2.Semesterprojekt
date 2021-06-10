module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jssc;
    requires com.fazecast.jSerialComm;

    opens org.example to javafx.fxml;
    exports org.example;
}