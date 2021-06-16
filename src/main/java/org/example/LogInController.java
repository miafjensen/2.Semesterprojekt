package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInController {

    public TextField cprUser;
    public String cpr;
    StartsideController startC = new StartsideController();
    @FXML
    Label cprLabel;

    @FXML
    private void switchToStartside() throws IOException {
        if (cprUser.getText().matches("\\d{6}")) {
            cpr = cprUser.getText();
            //startC.setCpr(cpr);
            LineChartApp.setRoot("startside");
        }else{
            //https://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i CPR");
            alert.setHeaderText(null);
            alert.setContentText("Indtast 6 cifre i CPR" +
                    "Eksempel: 120101");
            alert.showAndWait();
        }
        //startC.setCpr(cpr); //https://stackoverflow.com/questions/32147304/change-label-text-in-a-new-scene-which-was-entered-in-different-scene-javafx


        System.out.println(cpr);
    }

}
/*if (cpr.matches("[0-9]")){
        if (cpr.length()== 6){
        int CPR = Integer.parseInt(CPRfield.getText());
        mObjects.setCPR(CPR);
        cpr = CPRfield.getText() ;}}
        else {
        //https://code.makery.ch/blog/javafx-dialogs-official/
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl i CPR");
        alert.setHeaderText(null);
        alert.setContentText("Indtast 6 cifre i CPR" +
        "Eksempel: 120101");
        alert.showAndWait();

        }*/