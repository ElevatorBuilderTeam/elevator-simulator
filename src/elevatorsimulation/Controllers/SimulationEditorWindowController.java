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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
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
    public AnchorPane anchorPane;
    public Label scenarioFileName;
    public CheckBox appendScenriosCheckBox;

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

            int numOfFloors = Integer.parseInt(this.numOfFloors.getText());
            int numOfPassengers = Integer.parseInt(this.numOfPassengers.getText());
            int numOfElevatorBanks = Integer.parseInt(this.numOfElevatorBanks.getText());
            String scenarioName = this.scenarioName.getText();

            ElevatorSimulationGraph elevatorSimulationGraph = new ElevatorSimulationGraph();

            buildingScenarioManager.addScenario(scenarioName, new BuildingScenario(numOfPassengers, numOfFloors, numOfElevatorBanks, scenarioName, elevatorSimulationGraph));

            // update the scenario list
            scenarioListView.setItems(buildingScenarioManager.getScenarioEntries());

            clearData();

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

            loadWindow("SimulationElevatorGraphWindow", (loader, stage) -> {
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

    public void saveScenariosClicked(ActionEvent actionEvent) {

        if (buildingScenarioManager.getScenarioEntries().isEmpty()) {
            return;

        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save your ELE File");

        fileChooser.setInitialFileName("mysavedscenario.ele");
        File newFile = fileChooser.showSaveDialog(null);
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("ELE files", "*.ele"));

        //check if the user used the .ele extension
        // if not, remove any '.' characters and add the .ele

        //write to the file
        buildingScenarioManager.saveScenarioToFile(newFile);

    }


    public void loadScenarioClicked(ActionEvent e) throws IOException {
        // TODO: 2/9/15 figure out path to where scenarios will be saved on HDD.

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("ELE files", "*.ele"));


        // load the file if it's there already.

        // File file = null;
        // PrintWriter writer = new PrintWriter(new PrintWriter("testFile.ele", "UTF-8"));


        if (selectedFile != null && isCorrectFileExtension(selectedFile)) {

            buildingScenarioManager.loadScenariosFromFile(selectedFile, appendScenriosCheckBox.isSelected(), () -> {

                scenarioListView.setItems(buildingScenarioManager.getScenarioEntries());

            });

            scenarioFileName.setText(selectedFile.getName());
        }


    }


    private boolean isCorrectFileExtension(File selectedFile) {
        // file must end with extension .ele

        int postionOfDot = selectedFile.getName().length() - 4;
        System.out.println(selectedFile.getName().substring(postionOfDot));
        if (selectedFile.getName().substring(postionOfDot).equalsIgnoreCase(".ele")) {

            return true;
        }

        scenarioFileName.setText("Cannot Read File ");
        return false;

    }


    public void clearData() {
        this.numOfElevatorBanks.setText("");
        this.numOfFloors.setText("");
        this.numOfFloors.setText("");
    }

}






