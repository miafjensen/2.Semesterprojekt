package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.graph.Graph;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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
    public TextField CPRfield;
    boolean control = true;
    String cpr;
    @FXML
            //AVoid making uncasted object Initialises
    LineChart<String,Integer> GraphArea;
    XYChart.Series<String,Integer> dataseries = new XYChart.Series();
//vi kan også bruge <String,Integer> hvis vi skriver ""+index



    DBConn dbConn = new DBConn();
    Connection conn = dbConn.getConnectionobject(user, password);
    MeasurementDTO mDTO = new MeasurementDTO(conn);
    measurementObjects mObjects = new measurementObjects();



    @FXML
    Label pulsLabel;
    @FXML
    Label cprLabel;





    /*public int[] getSensorData(){

    }

     */




    public void updateGraph(int[] input){
        dataseries.setName("hestenet");

        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {


                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for(int i =0;i<input.length; i++){

                                // GraphArea.getData().add(new XYChart.Data<>(i,input[i]));

                                dataseries.getData().add(new XYChart.Data<String,Integer>( ""+i,input[i]));

                                //Fejlen ligger her - skal vi rette LineCHart til en

                               // System.out.println("hejsaaaa :)");
                            }
                            GraphArea.getData().removeAll();
                            GraphArea.getData().add(dataseries);
                        }
                    });

            }
        });

        taskThread.start();


       //






    }

    @FXML
    /*
    private void onEnter(ActionEvent ae){
        System.out.println("test");

        if (cpr.matches("[0-9]")){
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

        }
        System.out.println(cpr);


    }
*/



    private void switchToStartside() throws IOException {
        if (control == false) {
            event.shutdown();
            control = true;
        }
        try {
            App.setRoot("startside");
        }catch (Exception ex){
            LineChartApp.setRoot("startside");
        }
    }

    @FXML
    private synchronized void startMaaling() throws InterruptedException {
        event = Executors.newSingleThreadScheduledExecutor();
        if (control) {
            control = false;
            event.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {
                        int puls = (int) Math.round(110 - (Math.random() * 60));

                       //int cprTal = Integer.parseInt(cpr);

                        pulsLabel.setText(""+puls);

                    }), 0, 1000, TimeUnit.MILLISECONDS);
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


    public void updateGraph(ActionEvent actionEvent) {

        //updateGraph(generateData());
        for (int index : generateData()){
           // System.out.println(index);

        }
        updateGraph(generateData());
    }

    private int[] generateData(){
            int[] data = new int[25];
         for(int i =0;i<data.length; i++){
             data[i]= (int) (Math.random() * 10000);
        }


            return data;

    }
}
