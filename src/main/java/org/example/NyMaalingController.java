package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import org.example.Sensor.PortDataFilter;
import org.example.Sensor.SensorObserver;

public class NyMaalingController implements SensorObserver {

    /*
    todo: I skal sætte en getText fra jeres tekstfelt - og når brugeren så trykker enter eller tabber væk
    skal der kaldes en "setCPR" - som sørger for at jeres CPR bliver sat inde i database-klassen
    - Ting vi skal have med fra denne her : Et kald til en global setCPR metode, gerne inde i MeasurementConn klasse
    for at kunne sætte dem ind automatisk.
     */

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("startside");
    }

    @FXML
    private void startMaaling (){

    }

    @Override
    public void notify(PortDataFilter portDataFilter) {
        System.out.println(portDataFilter.dataFilter());
        /* kald til klasse og constructor i PortDataFilter
        */

    }
}
//hej