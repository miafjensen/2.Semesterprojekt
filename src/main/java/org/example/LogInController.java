package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInController {

    public static int CprTal;
    public TextField cprUser;
    public String cpr;
    //public int CprTal;
    //StartsideController startC = new StartsideController();
    //measurementObjects mObjects = new measurementObjects();
    @FXML
    Label cprLabel;

    @FXML
    private void switchToStartside(ActionEvent ae) throws IOException {
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

        System.out.println(cpr + "  " + CprTal);
    }


    public int getCprTal() {
          return CprTal;
    }

    /*public static void setCprTal(int CprTal) {
        LogInController.CprTal = CprTal;
    }*/

}
