package ubrugt.UbrugteKlasser;

import javafx.application.Application;

public abstract class LineChartSample extends Application {
    public LineChartSample() {
    }

   /* public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        LineChart<Number, Number> lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Stock Monitoring, 2010");
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");

        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        Scene scene = new Scene(lineChart, 800.0D, 600.0D);
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
    }*/

    public void main(String[] args) {


        launch();
    }
}