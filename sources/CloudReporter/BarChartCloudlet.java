package CloudReporter;/**
 * Created by Jake The Dog on 19/09/15.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart;
import javafx.*;


public class BarChartCloudlet extends Application {
        final static String fcfs = "FCFS";
    final static String minmin = "Min-Min";
    final static String maxmin = "Max-Min";
    final static  String[] algorithms = {fcfs,minmin,maxmin};
    final  static double[][] processingCost ={

        {91.8,89.1,155.4},
        {136.2,92.0,154.65},
        {138.1,92.1,166.1},
        {139.35,137.75,172.4},
        {296.4,151.98,180.54}

    };

    final  static double[][] timeExecution = {
        {19.6,21.36,20.2},
        {20.98,22.8,22.22},
        {21.88,20.106666666666666,23.72},
        {20.06,19.92,24.41},
        {19.776,21.024,25.072}

    };

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bar chart");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final javafx.scene.chart.BarChart<String,Number> bc =
                new javafx.scene.chart.BarChart<String, Number>(xAxis,yAxis);
        bc.setTitle("Promedio tiempo de ejecución");
        xAxis.setLabel("Algoritmos de calendarización");
        yAxis.setLabel("Tiempo ejecución (s)");

        XYChart.Series[] series = {null,null,null}; //{new XYChart.Series(),new XYChart.Series(),new XYChart.Series(),new XYChart.Series(),new XYChart.Series()};

        for(int i = 0;i< series.length;i++){
            int name = (i+1)*100;
            series[i] = new XYChart.Series();
            series[i].setName(algorithms[i]);
            series[i].getData().add(new XYChart.Data(fcfs, timeExecution[i][0]));
            series[i].getData().add(new XYChart.Data(minmin,timeExecution[i][1]));
            series[i].getData().add(new XYChart.Data(maxmin,timeExecution[i][2]));
        }

        /*
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("100");
        series1.getData().add(new XYChart.Data(fcfs, 91.8));
        series1.getData().add(new XYChart.Data(minmin, 89.1));
        series1.getData().add(new XYChart.Data(maxmin, 155.4));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("200");
        series2.getData().add(new XYChart.Data(fcfs, 136.2));
        series2.getData().add(new XYChart.Data(minmin, 92.0));
        series2.getData().add(new XYChart.Data(maxmin, 154.65));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("300");
        series3.getData().add(new XYChart.Data(fcfs, 138.1));
        series3.getData().add(new XYChart.Data(minmin, 92.1));
        series3.getData().add(new XYChart.Data(maxmin, 166.1));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("400");
        series4.getData().add(new XYChart.Data(fcfs, 139.35));
        series4.getData().add(new XYChart.Data(minmin,137.75));
        series4.getData().add(new XYChart.Data(maxmin, 172.4));

        XYChart.Series series5 = new XYChart.Series();
        series5.setName("500");
        series5.getData().add(new XYChart.Data(fcfs, 276.4));
        series5.getData().add(new XYChart.Data(minmin, 151.98));
        series5.getData().add(new XYChart.Data(maxmin, 180.54));
        */
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series[0], series[1], series[2],series[3],series[4]);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        //launch(args);
    }


}
