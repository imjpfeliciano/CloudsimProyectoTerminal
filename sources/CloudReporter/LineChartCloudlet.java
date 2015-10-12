package CloudReporter;

/**
 * Created by Jake The Dog on 20/09/15.
 */

import java.awt.Color;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class LineChartCloudlet extends Application {

    final static String fcfs = "FCFS";
    final static String minmin = "Min-Min";
    final static String maxmin = "Max-Min";
    final static  String[] algorithms = {fcfs,maxmin,minmin};
    @Override public void start(Stage stage) {

        Double[][] data = {
                {12.0,16.0,16.0,24.0,8.0,24.0,28.0,32.0,8.0,32.0,16.0,4.0,24.0,20.0,12.0,20.0,16.0,8.0,4.0,28.0,4.0,31.999999999999993,15.999999999999993,23.999999999999993,35.99999999999999,31.999999999999993,7.999999999999993,4.0,8.0,35.99999999999999,8.0,20.0,8.0,27.999999999999993,12.0,32.0,36.0,36.0,24.0,36.0,8.0,36.0,8.0,20.0,36.0,32.0,8.0,8.0,28.0,28.0,},
                {28.0,32.0,32.0,32.0,36.0,36.0,36.0,36.0,24.0,24.0,28.0,27.999999999999993,31.999999999999993,27.999999999999993,31.999999999999993,31.999999999999993,11.999999999999993,19.999999999999993,8.0,8.0,20.0,8.0,27.999999999999993,24.0,8.0,28.0,8.0,28.0,4.0,4.0,28.0,12.0,4.0,4.0,20.0,4.0,20.0,28.0,16.0,16.0,28.0,8.0,8.0,4.0,4.0,24.0,20.0,16.0,16.0,4.0,},
                {3.9999999999999996,3.9999999999999996,3.9999999999999996,8.0,4.0,8.0,4.0,8.0,8.0,8.0,4.0,4.0,16.0,12.000000000000002,8.000000000000002,12.000000000000002,12.000000000000002,16.0,12.000000000000002,16.0,8.0,12.0,12.0,16.0,20.0,12.0,20.0,24.0,28.0,20.0,28.0,27.999999999999993,31.999999999999993,31.999999999999993,23.999999999999993,31.999999999999993,27.999999999999993,27.999999999999993,28.0,31.999999999999993,32.0,32.0,28.0,36.0,32.0,36.0,32.0,36.0,36.0,36.0,}


        };
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Número de cloudlets");
        yAxis.setLabel("Tiempo de ejecucion (ms)");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Tiempo de ejecución 50 Cloudlets MIN-MIN");
        //defining a series
        lineChart.setStyle(".default-color2.chart-series-line { -fx-stroke: #11C1F2; }");

        XYChart.Series[] series = {null,null,null};

        for(int i=0;i<series.length;i++){
            series[i] = new XYChart.Series();

            series[i].setName(algorithms[i]);
            for(int j=0;j<data[0].length;j++) {
                //if (j == 0) {
                //    series[i].getData().add(new XYChart.Data(j, data[i][j]));
                //}
                series[i].getData().add(new XYChart.Data(j, data[i][j]));
            }

        }


        lineChart.getData().add(series[2]);



        Scene scene  = new Scene(lineChart,800,600);
        //lineChart.getData().add(series[0], series[1], series[2]);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}