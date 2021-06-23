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
    exports ubrugt.UbrugteKlasser.DynamiskGrafTest;
    opens ubrugt.UbrugteKlasser.DynamiskGrafTest to javafx.fxml;
    exports ubrugt.UbrugteKlasser;
    opens ubrugt.UbrugteKlasser to javafx.fxml;
}