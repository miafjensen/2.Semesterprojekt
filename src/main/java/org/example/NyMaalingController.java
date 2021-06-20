package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.example.Database.DBConn;
import org.example.Database.MeasurementDTO;
import org.example.Sensor.ConnectionEKG;
import org.example.Sensor.SensorObserver;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class NyMaalingController implements SensorObserver, Initializable {

    ScheduledExecutorService event;
    boolean control = true;
    DBConn dbConn = new DBConn();
    Connection conn = dbConn.getConnectionobject();
    MeasurementDTO mDTO = new MeasurementDTO(conn);
    LogInController logInController = new LogInController();


    //Dynamisk graf lavet ved hjælp af:
    // https://edencoding.com/javafx-charts/#firing-the-event-every-second
    // https://levelup.gitconnected.com/realtime-charts-with-javafx-ed33c46b9c8d


    @FXML
    LineChart<String, Number> lineChart = new LineChart<>(
            new CategoryAxis(),
            new NumberAxis()
    );
    XYChart.Series<String, Number> series = new XYChart.Series<>(
            FXCollections.observableArrayList(
                    new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0),
                    new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0),
                    new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0), new XYChart.Data<>(""+1,0)
            )
    );

    int numberOfPoints = 20*3;


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
        //inspireret af: https://stackoverflow.com/questions/24409550/how-to-pass-a-variable-through-javafx-application-to-the-controller
        cprLabel.setText("" + logInController.getCprTal());
    }

    /*public void updateGraph(int[] input) {
        dataseries.setName("EKG");


        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        GraphArea.getData().add(
                                new XYChart.Series<>()
                        );


                        for (int i = 0; i < input.length; i++) {

                            // GraphArea.getData().add(new XYChart.Data<>(i,input[i]));
                            dataseries.getData().add(new XYChart.Data<String, Integer>(i, input[i]));
                            //Fejlen ligger her - skal vi rette LineCHart til en

                        }
                        GraphArea.getData().removeAll();
                        GraphArea.getData().add(dataseries);

                    }
                });
            }
        });
        taskThread.start();
    }*/

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
                        pulsLabel.setText("" + puls);

                    }), 0, 1000, TimeUnit.MILLISECONDS);
            event.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {
                        // styrer vores dynamiske graf
                        Integer random = ThreadLocalRandom.current().nextInt(10);

                        lineChart.getData().add(series);

                        // put random number with current time
                        series.getData().add(new XYChart.Data<String, Number>(""+numberOfPoints++, random));
                        series.getData().remove(0);

                    }), 0, 100, TimeUnit.MILLISECONDS);


            event.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {

                        for (String[] indhold : placeholder) {
                            try {
                                mDTO.InsertIntoMeasurementsArray(Integer.parseInt(cprLabel.getText()), indhold);
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("fejl i insert");
                            }

                        }
                        placeholder.clear();

                        System.out.println("sendt til db");
                    }), 1000, 5000, TimeUnit.MILLISECONDS);
        }

    }

    @FXML
    private void stopMaaling(ActionEvent actionEvent) {
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


    /*public void updateGraph(ActionEvent actionEvent) throws IOException {

        //updateGraph(generateData());
        for (int index : generateData()) {
            // System.out.println(index);

        }
        updateGraph(generateData());
    }*/

    /*private int[] generateData() throws IOException {
        int[] data = new int[15];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 100);
        }
        return data;
    }*/

    ArrayList<String[]> placeholder = new ArrayList<String[]>();//buffer til String arrays

    @Override
    public void notify(ConnectionEKG connectionEKG) {
        placeholder.add(connectionEKG.getSplitData());
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
