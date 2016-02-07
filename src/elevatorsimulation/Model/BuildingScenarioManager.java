package elevatorsimulation.Model;

import javafx.stage.FileChooser;

import java.util.HashMap;

/**
 * Created by andrewlincoln on 1/30/16.
 */

// This is a singleton That will asycnhronously query the data storage load scenario
// This class is to handle all the saving and loading of a scenario.
public class BuildingScenarioManager {

    private HashMap<StringBuilder, BuildingScenario> buildingScenarios;
    private FileChooser fileChooser;
    private static BuildingScenarioManager scenarioManager = null;


    protected BuildingScenarioManager() {
        buildingScenarios = new HashMap<StringBuilder, BuildingScenario>();
        // TODO: 2/7/2016 load scenarios from disk
    }

    public static BuildingScenarioManager getDefaultManager() {
        if (scenarioManager == null) {
            scenarioManager = new BuildingScenarioManager();
        }
        return scenarioManager;
    }

    public void loadScenario() {

    }


    public void saveScenario(String name, BuildingScenario buildingScenario) {
        buildingScenarios.put(new StringBuilder(name), buildingScenario );

    }



}
