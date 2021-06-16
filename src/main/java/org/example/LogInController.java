package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.Database.measurementObjects;

import java.io.IOException;

public class LogInController {

    public TextField cprUser;
    public String cpr;
    public int cprTal;
    StartsideController startC = new StartsideController();
    measurementObjects mObjects = new measurementObjects();
    @FXML
    Label cprLabel;

    @FXML
    private void switchToStartside(ActionEvent ae) throws IOException {
        if (cprUser.getText().matches("\\d{6}")) {
            cpr = cprUser.getText();
            cprTal = Integer.parseInt(cpr);
            App.setRoot("startside");
        }else{
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
