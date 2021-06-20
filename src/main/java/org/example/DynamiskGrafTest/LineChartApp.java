package org.example.DynamiskGrafTest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.example.Sensor.ConnectionEKG;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JavaFX App
 */
public class LineChartApp extends Application {

    ConnectionEKG connectionEKG = new ConnectionEKG();

    //Dynamisk graf fra https://levelup.gitconnected.com/realtime-charts-with-javafx-ed33c46b9c8d
    public static void main(String[] args) {
        launch(args);
    }

    XYChart.Series<String, Number> series = new XYChart.Series<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFX Realtime Chart Demo");//defining the axes
        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time/s");
        xAxis.setAnimated(true); // axis animations are removed
        yAxis.setLabel("Value");
        yAxis.setAnimated(true); // axis animations are removed

        //creating the line chart with two axis created above
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Realtime JavaFX Charts");
        lineChart.setAnimated(true); // disable animations

        ArrayList<String[]> yAkse = new ArrayList<>();



        //defining a series to display data

        series.setName("Data Series");

        // add series to chart
        lineChart.getData().add(series);


        // setup scene
        Scene scene = new Scene(lineChart, 800, 600);
        primaryStage.setScene(scene);

        // show the stage
        primaryStage.show();
        // this is used to display time in HH:mm:ss format
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // setup a scheduled executor to periodically put data into the chart
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // put dummy data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10
            Integer random = ThreadLocalRandom.current().nextInt(10);

            // Update the chart
            Platform.runLater(() -> {
                // get current time
                Date now = new Date();
                // put random number with current time
                series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random));
            });
        }, 0, 1, TimeUnit.SECONDS);
/*
        // setup a scheduled executor to periodically put data into the chart
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // put dummy data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10
            Integer random = ThreadLocalRandom.current().nextInt(10);
            AtomicInteger i = new AtomicInteger();
            yAkse.add(connectionEKG.getSplitData());

            // Update the chart
            Platform.runLater(() -> {
                // get current time
                i.getAndDecrement();
                //int data = Integer.parseInt(yAkse[i]);
                // put random number with current time
                series.getData().add(new XYChart.Data<>(i, random));


                //series.getData().remove(0);

            });
        }, 0, 500, TimeUnit.MILLISECONDS);*/

    }

    int numberOfPoints = 120;


}

/*
//defining a series to display data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data Series");

        // add series to chart
        GraphArea.getData().add(series);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        event = Executors.newSingleThreadScheduledExecutor();

            event.scheduleAtFixedRate(() -> {
                int random = ThreadLocalRandom.current().nextInt(10);
                Platform.runLater(() -> {
                    Date now = new Date();
                    series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random));
                });
            }, 0, 1000, TimeUnit.MILLISECONDS);
 */