package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.Database.DBConn;
import org.example.Database.MeasurementDTO;
import org.example.Database.measurementObjects;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeDataController implements Initializable {


    @FXML
    Label cprLabel;
    @FXML
    TableView tableView;
    @FXML
    TextField cprField;

    String cpr;
    int cprSearched;
    LogInController logInController = new LogInController();
    private static String user;
    private static String password;
    DBConn dbConn = new DBConn();
    Connection conn = dbConn.getConnectionobject(user, password);
    MeasurementDTO mDTO = new MeasurementDTO(conn);
    measurementObjects mObjects = new measurementObjects();


    @FXML
    private void switchToStartside() throws IOException {
        App.setRoot("startside");
    }


    @FXML
    private void switchToLogIn() throws IOException {
        App.setRoot("logIn");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cprLabel.setText("" + logInController.getCprTal());

    }


    @FXML
    private void searchByCpr(ActionEvent ae) throws IOException {
        if (cprField.getText().matches("\\d{6}")) {
            cpr = cprField.getText();
            cprSearched = Integer.parseInt(cpr);
            ArrayList<measurementObjects> result = mDTO.FindAllMeasurementResults(cprSearched);

        } else {

            //https://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i CPR");
            alert.setHeaderText(null);
            alert.setContentText("Indtast 6 cifre i CPR" +
                    "\nEksempel: 120101");
            alert.showAndWait();
        }


        System.out.println(cpr + "  " + cprSearched);
    }
}
