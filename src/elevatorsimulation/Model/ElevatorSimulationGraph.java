package elevatorsimulation.Model;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class ElevatorSimulationGraph {
    int time, floor;
    LineChart<Integer, Integer > chart;
    NumberAxis floorAxis;
    CategoryAxis timeAxis;


    public ElevatorSimulationGraph(LineChart chart, NumberAxis floorAxis, CategoryAxis timeAxis) {
        this.chart = chart;
        this.floorAxis = floorAxis;
        this.timeAxis = timeAxis;
    }


}
