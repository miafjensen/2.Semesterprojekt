package ubrugt.UbrugteKlasser.DynamiskGrafTest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class LineChartController {

//    lavet ved hjælp af:
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

    Timeline updateChart;

    EventHandler<ActionEvent> chartUpdater = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Integer random = ThreadLocalRandom.current().nextInt(10);

            lineChart.getData().add(series);

            // put random number with current time
            series.getData().add(new XYChart.Data<String, Number>(""+numberOfPoints++, random));
            series.getData().remove(0);

        }
    };

    public LineChartController() {
        updateChart = new Timeline(new KeyFrame(Duration.millis(100), chartUpdater));
        updateChart.setCycleCount(Timeline.INDEFINITE);
        updateChart.play();
    }



}
