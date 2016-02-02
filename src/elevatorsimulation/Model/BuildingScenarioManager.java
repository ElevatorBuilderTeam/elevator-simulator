package elevatorsimulation.Model;

import javafx.stage.FileChooser;

import javax.swing.plaf.FileChooserUI;
import java.util.HashMap;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class BuildingScenarioManager {

    private HashMap<StringBuilder, BuildingScenario> buildingScenarios;
    private FileChooser fileChooser;

    public BuildingScenarioManager(FileChooser fileChooser) {
        buildingScenarios = new HashMap<StringBuilder, BuildingScenario>();

    }

    public void loadScenario() {

    }

    public void saveScenario(String name, BuildingScenario buildingScenario) {
        buildingScenarios.put(new StringBuilder(name), buildingScenario );

    }



}
