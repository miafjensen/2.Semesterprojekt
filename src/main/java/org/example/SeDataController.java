package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SeDataController {


    @FXML
    Label cprLabel;

    @FXML
    TextField cprField;

    String cpr;
    int cprTal;

    @FXML
    private void switchToStartside() throws IOException {
        App.setRoot("startside");
    }

    @FXML
    private void switchToNyMaaling() throws IOException {
        App.setRoot("nyMaaling");
    }

    @FXML
    private void switchToLogIn() throws IOException {
        App.setRoot("logIn");
    }

    public void setCpr(String cpr) {
        cprLabel.setText(cpr);
    }


    @FXML
    private void searchByCpr(ActionEvent ae) throws IOException {
        if (cprField.getText().matches("\\d{6}")) {
            cpr = cprField.getText();
            cprTal = Integer.parseInt(cpr);
        } else {
            //https://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i CPR");
            alert.setHeaderText(null);
            alert.setContentText("Indtast 6 cifre i CPR" +
                    "\nEksempel: 120101");
            alert.showAndWait();
        }
        //startC.setCpr("" + cprTal); //https://stackoverflow.com/questions/32147304/change-label-text-in-a-new-scene-which-was-entered-in-different-scene-javafx


        System.out.println(cpr + "  " + cprTal);
    }
}
