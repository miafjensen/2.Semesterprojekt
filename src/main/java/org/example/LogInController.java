package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInController {

    public static int CprTal;
    public TextField cprUser;
    public String cpr;


    @FXML
    private void switchToStartside(ActionEvent ae) throws IOException {
        // sørger for at det kun er tal på 6 cifre:  http://tutorials.jenkov.com/java-regex/index.html
        if (cprUser.getText().matches("\\d{6}")) {
            cpr = cprUser.getText();
            CprTal = Integer.parseInt(cpr);
            App.setRoot("startside");
        } else {
            //https://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i CPR");
            alert.setHeaderText(null);
            alert.setContentText("Indtast 6 cifre i CPR" +
                    "\nEksempel: 120101");
            alert.showAndWait();
        }
        //System.out.println(cpr + "  " + CprTal);
    }

    // laver globalt object der kan tilgås fra de andre controllers
    public int getCprTal() {
        return CprTal;
    }

}
