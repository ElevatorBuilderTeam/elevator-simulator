package elevatorsimulation.Model;

import elevatorsimulation.Callback.CompletionHandler;
import elevatorsimulation.Exceptions.ScenarioAlreadyExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrewlincoln on 1/30/16.
 */

// This is a singleton That will asychronously query the data storage load scenario
// This class is to handle all the saving and loading of a scenario.
public class BuildingScenarioManager implements Serializable {

    private static BuildingScenarioManager scenarioManager = null;
    private HashMap<String, BuildingScenario> buildingScenarios;

    private ObservableList<String> scenarioEntries = FXCollections.observableArrayList();


    protected BuildingScenarioManager() {
        buildingScenarios = new HashMap<>();

    }


    //GETTERS ***********************************************

    public static BuildingScenarioManager getDefaultManager() {
        if (scenarioManager == null) {
            scenarioManager = new BuildingScenarioManager();
        }
        return scenarioManager;
    }

    public ObservableList<String> getScenarioEntries() {
        return scenarioEntries;
    }

    //PUBLIC INTERFACE ***********************************************

    public BuildingScenario loadScenario(String scenarioName) {
        for (Map.Entry<String, BuildingScenario> entries : buildingScenarios.entrySet()) {
            if (scenarioName.equalsIgnoreCase(entries.getKey())) {
                return entries.getValue();
            }
        }
        return null;
    }

    public void loadScenariosFromFile(File selectedFile, boolean shouldClearList, CompletionHandler completionHandler) {

        HashMap<String, BuildingScenario> tempScenarios = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            this.scenarioEntries.removeAll();
            this.buildingScenarios.clear();

            tempScenarios = (HashMap<String, BuildingScenario>) objectInputStream.readObject();
            this.buildingScenarios.putAll(tempScenarios);


            for (Map.Entry<String, BuildingScenario> entries : this.buildingScenarios.entrySet()) {
                this.scenarioEntries.add(entries.getKey());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        completionHandler.completed();
    }


    public void addScenario(String scenarioName, BuildingScenario buildingScenario) {

        if (!scenarioExists(scenarioName)) {
            buildingScenarios.put(scenarioName, buildingScenario);
            scenarioEntries.add(buildingScenario.getScenarioEntryText().toString());
            return;
        }

        try {
            throw new ScenarioAlreadyExistsException();
        } catch (ScenarioAlreadyExistsException e) {
            System.out.println("Scenario Already Exists");
        }

    }

    public void saveScenarioToFile(File newFile) {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(this.buildingScenarios);
            outputStream.close();
            fileOutputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void removeScenario(String scenarioName, CompletionHandler handler) {

        buildingScenarios.remove(scenarioName);
        handler.completed();

    }

    public void runScenario() {

    }

    public boolean scenarioExists(String scenarioName) {
        for (Map.Entry<String, BuildingScenario> entries : buildingScenarios.entrySet()) {
            if (scenarioName.equalsIgnoreCase(entries.getKey())) {
                return true;
            }
        }
        return false;
    }

}
