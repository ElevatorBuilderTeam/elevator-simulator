package elevatorsimulation.Controllers;

import elevatorsimulation.Model.ElevatorSimulationGraph;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Andrew on 2/8/2016.
 */
public class SimulationElevatorGraphController extends Controller implements Initializable {


    public LineChart elevatorLineChart;
    public CategoryAxis timeXaxis;
    public NumberAxis floorYaxis;
    public int number;

    private ElevatorSimulationGraph elevatorSimulationGraph;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // BuildingScenarioManager.getDefaultManager().loadScenario();
    }


    public void initWithParent(Controller controller, ElevatorSimulationGraph elevatorSimulationGraph) {
        super.initWithParent(controller);

        this.elevatorSimulationGraph = elevatorSimulationGraph;

        setUpGraph(elevatorSimulationGraph);

    }

    private void setUpGraph(ElevatorSimulationGraph elevatorSimulationGraph) {
        // TODO: 2/8/2016 Setup graph here
        this.floorYaxis.setUpperBound(elevatorSimulationGraph.getMaxY());

    }

}
