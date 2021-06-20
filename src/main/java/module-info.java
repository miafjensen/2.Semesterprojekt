module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jssc;
    requires com.fazecast.jSerialComm;
    requires com.google.common;
    requires java.sql;
    requires java.desktop;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.DynamiskGrafTest;
    opens org.example.DynamiskGrafTest to javafx.fxml;
    exports org.example.UbrugteKlasser;
    opens org.example.UbrugteKlasser to javafx.fxml;
}