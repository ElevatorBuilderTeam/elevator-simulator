package elevatorsimulation.Model;

import elevatorsimulation.Callback.CompletionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrewlincoln on 1/30/16.
 */

// This is a singleton That will asychronously query the data storage load scenario
// This class is to handle all the saving and loading of a scenario.
public class BuildingScenarioManager {

    private static BuildingScenarioManager scenarioManager = null;
    private HashMap<StringBuilder, BuildingScenario> buildingScenarios;
    private FileChooser fileChooser;
    private ObservableList<String> scenarioEntries = FXCollections.observableArrayList();


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

    //GETTERS


    public ObservableList<String> getScenarioEntries() {
        return scenarioEntries;
    }

    public BuildingScenario loadScenario(String scenarioName) {
        for (Map.Entry<StringBuilder, BuildingScenario> entries : buildingScenarios.entrySet()) {
            if (scenarioName.equalsIgnoreCase(entries.getKey().toString())) {
                return entries.getValue();
            }
        }
        return null;
    }


    public void saveScenario(String name, BuildingScenario buildingScenario) {
        buildingScenarios.put(new StringBuilder(name), buildingScenario );
        scenarioEntries.add(buildingScenario.getScenarioEntryText().toString());

    }

    public void removeScenario(String scenarioName, CompletionHandler handler) {
        for (Map.Entry<StringBuilder, BuildingScenario> entries : buildingScenarios.entrySet()) {
            if (scenarioName.equalsIgnoreCase(entries.getKey().toString())) {

                buildingScenarios.remove(entries.getKey());
                handler.completed();
            }
        }
    }

    public void runScenario() {

    }




}
