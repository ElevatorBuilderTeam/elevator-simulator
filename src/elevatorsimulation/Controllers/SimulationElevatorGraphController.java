package elevatorsimulation.Controllers;

import elevatorsimulation.Model.ElevatorSimulationGraph;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Andrew on 2/8/2016.
 */
public class SimulationElevatorGraphController extends Controller implements Initializable {

    public LineChart elevatorLineChart;
    public NumberAxis floorYaxis;
    public NumberAxis timeXaxis;

    public int number;
    public Label visitorInBuildingCount;

    private ElevatorSimulationGraph elevatorSimulationGraph;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // BuildingScenarioManager.getDefaultManager().loadScenario();
    }

    @Override
    public void initWithParent(Controller controller) {
        super.initWithParent(controller);
        this.elevatorSimulationGraph = ElevatorSimulationGraph.getDefaultGraph();
        this.elevatorSimulationGraph.setUpChart(elevatorLineChart, floorYaxis, timeXaxis, "");

    }

}
