package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import org.example.Database.DBConn;
import org.example.Database.MeasurementDTO;
import org.example.Database.measurementObjects;
import org.example.Sensor.PortDataFilter;
import org.example.Sensor.SensorObserver;


public class NyMaalingController implements SensorObserver {

    /*
    todo: I skal sætte en getText fra jeres tekstfelt - og når brugeren så trykker enter eller tabber væk
    skal der kaldes en "setCPR" - som sørger for at jeres CPR bliver sat inde i database-klassen
    - Ting vi skal have med fra denne her : Et kald til en global setCPR metode, gerne inde i MeasurementConn klasse
    for at kunne sætte dem ind automatisk.
     */
    private static String user = "root";
    private static String password = "1234mySQL"; //indtast jeres personlige password til localhost

    ScheduledExecutorService event;
    boolean control = true;
    int cpr;


    DBConn dbConn = new DBConn();
    Connection conn = dbConn.getConnectionobject(user, password);
    MeasurementDTO mDTO = new MeasurementDTO(conn);
    measurementObjects mObjects = new measurementObjects();

    @FXML
    Label pulsLabel;
    @FXML
    Label cprLabel;


    @FXML
    private void switchToStartside() throws IOException {
        if (control == false) {
            event.shutdown();
            control = true;
        }
        App.setRoot("startside");
    }

    @FXML
    private synchronized void startMaaling() throws InterruptedException {
        event = Executors.newSingleThreadScheduledExecutor();
        if (control) {
            control = false;
            event.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {
                        int puls = (int) Math.round(110 - (Math.random() * 60));

                       //int cprTal = Integer.parseInt(cpr); // virker ikke

                        pulsLabel.setText(""+puls);


                    }), 0, 1000, TimeUnit.MILLISECONDS);
            event.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {
                        int puls = (int) Math.round(110 - (Math.random() * 60));


                        int s = puls;
                        int cpr = 767676;
                        mDTO.InsertIntoMeasurements( cpr, s);

                    }), 0, 100, TimeUnit.MILLISECONDS);
        }

    }
    @FXML
    private void stop(ActionEvent actionEvent) {
        event.shutdown();
        control = true;
    }
    @FXML
    private void switchToLogIn() throws IOException {
        App.setRoot("logIn");
    }

    @Override
    public void notify(PortDataFilter portDataFilter) {
        System.out.println(portDataFilter.dataFilter());
        /* kald til klasse og constructor i PortDataFilter
         */

    }


}
