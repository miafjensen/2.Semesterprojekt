package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
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
    int numberOfPoints = 20 * 3; //fortæller hvor mange punkter der er "XYChart.series" fra start, så grafen kan fortsætte derfra

    @FXML
    Label pulsLabel;
    @FXML
    Label cprLabel;

    @FXML
    LineChart<String, Number> lineChart = new LineChart<>(
            new CategoryAxis(),
            new NumberAxis()
    );

    XYChart.Series<String, Number> series = new XYChart.Series<>(
            FXCollections.observableArrayList(          //den serie af punkter vi starter med
                    // koordinater til serien, der med til at sørge for hvor mange punkter der er fra start, som påvirker hvor mange punkter der kan ses ad gangen
                    new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0),
                    new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0),
                    new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0), new XYChart.Data<>("" + 1, 0)
                    //Dynamisk graf lavet ved hjælp af:
                    // https://edencoding.com/javafx-charts/#firing-the-event-every-second
                    // https://levelup.gitconnected.com/realtime-charts-with-javafx-ed33c46b9c8d
            )
    );

    public NyMaalingController() {
        ConnectionEKG connectionEKG = new ConnectionEKG();
        connectionEKG.registerObserver(this);
        new Thread(connectionEKG).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inspireret af: https://stackoverflow.com/questions/24409550/how-to-pass-a-variable-through-javafx-application-to-the-controller
        cprLabel.setText("" + logInController.getCprTal());  // henter og viser cpr fra LogIn på cprLabel
    }

    @FXML
    private void switchToStartside() throws IOException {
        if (control == false) {     //tjekker om startMaaling kører, hvis den gør lukkes den inden der skiftes side
            event.shutdown();
            control = true;
        }
        App.setRoot("startside");
    }

    @FXML
    private synchronized void startMaaling() throws InterruptedException {
        event = Executors.newSingleThreadScheduledExecutor();
        if (control) {          // sørger for at den kun kan startes en gang
            control = false;
            event.scheduleAtFixedRate(() ->         //styrer den simulerede puls
                    Platform.runLater(() -> {
                        int puls = (int) Math.round(110 - (Math.random() * 60)); //generer heltal mellem 60 og 110
                        pulsLabel.setText("" + puls);

                    }), 0, 3000, TimeUnit.MILLISECONDS);

            event.scheduleAtFixedRate(() ->         // styrer vores dynamiske graf
                    Platform.runLater(() -> {

                        Integer random = ThreadLocalRandom.current().nextInt(1000);     // genererer random int op til 1000

                        lineChart.getData().add(series); // tilføjer punkt til grafen

                        series.getData().add(new XYChart.Data<String, Number>("" + numberOfPoints++, random));  // laver koordinaterne til punktet

                        series.getData().remove(0); //Sletter det tidligste punkt på grafen
                    }), 0, 100, TimeUnit.MILLISECONDS);

            event.scheduleAtFixedRate(() ->          // styrer overførslen til Databasen
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
        event.shutdown(); //lukker for de events der er startet af startMaaling
        control = true;
    }

    @FXML
    private void switchToLogIn() throws IOException {
        if (control == false) {  //tjekker om startMaaling kører, hvis den gør lukkes den inden der skiftes side
            event.shutdown();
            control = true;
        }
        App.setRoot("logIn");
    }

    ArrayList<String[]> placeholder = new ArrayList<String[]>();//buffer til String arrays til data fra sensor

    @Override
    public void notify(ConnectionEKG connectionEKG) {  //kaldes fra SensorObserver og giver besked når der er data at hente
        placeholder.add(connectionEKG.getSplitData());      //bruger materiale fra ConnectionEKG
    }
}

// nogle af de konstruktører der er blevet arbejdet med men enten ikke er blevet færdige, eller fundet bedre løsninger:

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