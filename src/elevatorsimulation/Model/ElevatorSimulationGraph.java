package elevatorsimulation.Model;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import java.io.Serializable;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class ElevatorSimulationGraph implements Serializable {

    // Not sure if we need these.
    private LineChart<Integer, Integer> chart;
    private NumberAxis floorAxis;
    private CategoryAxis timeAxis;
    private BuildingScenario buildingScenario;
    private int maxY, xBounds;


    public ElevatorSimulationGraph() {

    }

    //GETTERS


    public int getMaxY() {
        return maxY;
    }

    public void setBuildingScenario(BuildingScenario buildingScenario) {
        this.buildingScenario = buildingScenario;

        this.maxY = buildingScenario.getNumberOfFloors();

    }
}
