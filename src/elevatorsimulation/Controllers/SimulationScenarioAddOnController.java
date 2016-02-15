package elevatorsimulation.Controllers;

import elevatorsimulation.Model.BuildingScenario;
import javafx.scene.control.TextField;

/**
 * Created by Andrew on 2/14/2016.
 */
public class SimulationScenarioAddOnController extends Controller {
    public TextField averageTimeOnFloor;
    public TextField averageTimeInBuilding;
    public TextField elevatorDoorTimeDelay;
    public TextField speedOfElevatorPerFloor;
    private BuildingScenario buildingScenario;


    public void initWithParent(Controller controller, BuildingScenario buildingScenario) {
        super.initWithParent(controller);
        this.buildingScenario = buildingScenario;
        addValuesToScenario();
    }

    public void addValuesToScenario() {

    }
}
