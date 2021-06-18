package org.example;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.base.CharMatcher;
import com.google.common.graph.Graph;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import org.example.Database.DBConn;
import org.example.Database.MeasurementDTO;
import org.example.Database.measurementObjects;
import org.example.Sensor.ConnectionEKG;
import org.example.Sensor.PortDataFilter;
import org.example.Sensor.SensorObserver;


public class NyMaalingController implements SensorObserver, Initializable {


    private static String user;
    private static String password; //ret personligt kodeord i DBConn klassen line 29
    ScheduledExecutorService event;
    boolean control = true;
    DBConn dbConn = new DBConn();
    Connection conn = dbConn.getConnectionobject(user, password);
    MeasurementDTO mDTO = new MeasurementDTO(conn);
    measurementObjects mObjects = new measurementObjects();
    LogInController logInController = new LogInController();
    Scanner sc = new Scanner(System.in);

    @FXML
    //AVoid making uncasted object Initialises
    LineChart<String, Integer> GraphArea;
    XYChart.Series<String, Integer> dataseries = new XYChart.Series();
//vi kan også bruge <String,Integer> hvis vi skriver ""+index

    @FXML
    Label pulsLabel;
    @FXML
    Label cprLabel;

    public NyMaalingController() {

        ConnectionEKG connectionEKG = new ConnectionEKG();

        connectionEKG.registerObserver(this);
        new Thread(connectionEKG).start();
    }


    // henter og viser cpr fra LogIn på cprLabel
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cprLabel.setText("" + logInController.getCprTal());

        ConnectionEKG connectionEKG = new ConnectionEKG();

        connectionEKG.registerObserver(this);
        new Thread(connectionEKG);
        //inspireret af: https://stackoverflow.com/questions/24409550/how-to-pass-a-variable-through-javafx-application-to-the-controller
    }

    public void updateGraph(int[] input) {
        dataseries.setName("EKG");

        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < input.length; i++) {

                            // GraphArea.getData().add(new XYChart.Data<>(i,input[i]));

                            dataseries.getData().add(new XYChart.Data<String, Integer>("" + i, input[i]));

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
                       // int puls = (int) Math.round(110 - (Math.random() * 60));

                       // pulsLabel.setText("" + puls);

                    }), 0, 1000, TimeUnit.MILLISECONDS);
            event.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {

                        try {
                            for (int index : generateData()) {
                                // System.out.println(index);

                            }
                            updateGraph(generateData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }), 0, 15000, TimeUnit.MILLISECONDS);

            event.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {
                        int puls = (int) Math.round(110 - (Math.random() * 60));

                        pulsLabel.setText("" + puls);
                        for(String[] indhold:placeholder){
                            mDTO.InsertIntoMeasurementsArray(Integer.parseInt(cprLabel.getText()),indhold);
                        }
                        placeholder.clear();
                    }), 1000, 8000, TimeUnit.MILLISECONDS);
        }

    }

    @FXML
    private void stop(ActionEvent actionEvent) {
        event.shutdown();
        control = true;
    }

    @FXML
    private void switchToLogIn() throws IOException {
        if (control == false) {
            event.shutdown();
            control = true;
        }
        App.setRoot("logIn");
    }

  /*
    @Override
    public void notify(PortDataFilter portDataFilter) {
        System.out.println(portDataFilter.dataString());
        *//* kald til klasse og constructor i PortDataFilter
         *//*

    }*/



    public void updateGraph(ActionEvent actionEvent) throws IOException {

        //updateGraph(generateData());
        for (int index : generateData()) {
            // System.out.println(index);

        }
        updateGraph(generateData());
    }

   private int[] generateData() throws IOException {
        int[] data = new int[15];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 100);
        }
        return data;
    }

    ArrayList<String[]> placeholder = new ArrayList<String []>();//buffer til String arrays
    @Override
    public void notify(ConnectionEKG connectionEKG) {
        placeholder.add(connectionEKG.getSplittedData());





        //bruger materiale fra ConnectionEKG

    }


    /*private int[] generateData() throws IOException {
        int[] data = new int[35];
        for (int i = 0; i < data.length; i++) {


            File file = new File("src/main/java/org/example/EKGSim.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st = br.readLine();
            double doub = 0;
            if (st != null){
                //st = CharMatcher.inRange('0', '9').retainFrom(st);
                doub = (Double.parseDouble(st));
            data[i] = (int) Math.round(doub * 1000000);
            System.out.println(data[i]);
            st = br.readLine();
            }
            br.close();

        }


        return data;

    }*/
}
