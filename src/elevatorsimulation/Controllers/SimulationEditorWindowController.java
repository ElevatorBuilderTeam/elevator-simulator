/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorsimulation.Controllers;

import elevatorsimulation.Model.BuildingScenario;
import elevatorsimulation.Model.BuildingScenarioManager;
import elevatorsimulation.Model.ElevatorSimulationGraph;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author andrewlincoln
 * @author andrewbriggs
 */
public class SimulationEditorWindowController extends Controller implements Initializable {


    public TextField scenarioName;
    public TextField numOfPassengers;
    public TextField numOfElevatorBanks;
    public TextField numOfFloors;

    public ListView scenarioListView;

    private BuildingScenario buildingScenario;
    private BuildingScenarioManager buildingScenarioManager;


    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @Override
    public void initWithParent(Controller controller) {
        super.initWithParent(controller);
        checkForScenarioEntries();
    }

    public void addScenarioClicked(ActionEvent actionEvent) {

        // parse from the input fields
        if (!valuesFromTextInputAreValid()) {
            return;
        }

        try {

            int numOfFloors = new Integer(this.numOfFloors.getText()).intValue();
            int numOfPassengers = new Integer(this.numOfPassengers.getText()).intValue();
            int numOfElevatorBanks = new Integer(this.numOfElevatorBanks.getText()).intValue();
            String scenarioName = this.scenarioName.getText();

            ElevatorSimulationGraph elevatorSimulationGraph = new ElevatorSimulationGraph();

            buildingScenarioManager.addScenario(scenarioName, new BuildingScenario(numOfPassengers, numOfFloors, numOfElevatorBanks, scenarioName, elevatorSimulationGraph));

            // update the scenario list
            scenarioListView.setItems(buildingScenarioManager.getScenarioEntries());

        } catch (NumberFormatException e) {
            System.out.println("Number format exception or elevator not found");
            // TODO: 2/7/2016 when user doesn't enter an integer it causes this exception, show a label to help them input the correct data.
        }

    }


    public void removeScenarioClicked(ActionEvent actionEvent) {
        // TODO: 2/7/2016 remove scenario remove the highlighted scenario from the list

        // locate the highlighted scenario
        // remove it from 2 locations
        // the list
        // the manager
        //check to see if item is selected
        if (!scenarioListView.getSelectionModel().isEmpty()) {

            findNameForScenario(scenarioListView, true);

        }

    }

    public void runScenarioClicked(ActionEvent actionEvent) {
        // TODO: 2/7/2016 implement algorithm for run scenario


        // find the selected scenario
        // get the simulationGraph associated with it
        // send the graph to the SimulationElevatorGraphController
        if (!scenarioListView.getSelectionModel().isEmpty()) {

            String scenarioToBeLoaded = findNameForScenario(scenarioListView, false);

            BuildingScenario tempScenario = buildingScenarioManager.loadScenario(scenarioToBeLoaded);
            ElevatorSimulationGraph scenarioElevatorGraph = tempScenario.getElevatorSimulatorGraph();

            loadWindow("SimulationElevatorGraphWindow", loader -> {
                loader.<SimulationElevatorGraphController>getController().initWithParent(this, scenarioElevatorGraph);

            });

        }
    }

    private String findNameForScenario(ListView scenarioListView, boolean shouldRemoveItem) {

        String scenario = null;
        int selectedItemIndex = scenarioListView.getSelectionModel().getSelectedIndex();
        scenario = (String) scenarioListView.getItems().get(selectedItemIndex);

        if (shouldRemoveItem) {
            buildingScenarioManager.removeScenario(scenario, () -> scenarioListView.setItems(buildingScenarioManager.getScenarioEntries()));
            buildingScenarioManager.getScenarioEntries().remove(selectedItemIndex);
        }

        System.out.println("Scenario: " + scenario);
        return scenario;

    }


    private boolean valuesFromTextInputAreValid() {
        if (numOfFloors.getText() == "" ||
                numOfPassengers.getText() == "" ||
                numOfElevatorBanks.getText() == "" ||
                scenarioName.getText() == "") {
            System.out.println("Missing a value, please fill all values in");
            return false;
        }
        return true;
    }

    private void checkForScenarioEntries() {
        buildingScenarioManager = BuildingScenarioManager.getDefaultManager();

        if (buildingScenarioManager.getScenarioEntries() != null) {
            scenarioListView.setItems(buildingScenarioManager.getScenarioEntries());
            scenarioListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
    }


    public void loadScenarioClicked() {
        // TODO: 2/9/15 figure out path to where scenarios will be saved on HDD.

        String os = System.getProperty("os.name").toLowerCase();
        boolean win = os.indexOf("win") >= 0;

        try {
            if(win == true)
                Runtime.getRuntime().exec("explorer.exe /select, path");
            else{
                Runtime.getRuntime().exec("open, path");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






