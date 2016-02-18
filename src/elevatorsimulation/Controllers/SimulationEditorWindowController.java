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
import java.util.InputMismatchException;
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

    public ListView<String> scenarioListView;
    public AnchorPane anchorPane;
    public Label scenarioFileName;
    public CheckBox appendScenriosCheckBox;
    public Label warningTextLabel;

    private boolean scenarioAddOnWindowOpened = false;


    private BuildingScenarioManager buildingScenarioManager = BuildingScenarioManager.getDefaultManager();


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

            int numOfFloors = Integer.parseInt(removeWhiteSpaceFromString(this.numOfFloors.getText()));
            int numOfPassengers = Integer.parseInt(removeWhiteSpaceFromString(this.numOfPassengers.getText()));
            int numOfElevatorBanks = Integer.parseInt(removeWhiteSpaceFromString(this.numOfElevatorBanks.getText()));
            String scenarioName = removeWhiteSpaceFromString(this.scenarioName.getText());

            buildingScenarioManager.addScenario(scenarioName, new BuildingScenario(numOfPassengers, numOfFloors, numOfElevatorBanks, scenarioName, ElevatorSimulationGraph.getDefaultGraph()));

            // update the scenario list
            scenarioListView.setItems(buildingScenarioManager.getScenarioEntries());

            hideWarning();

        } catch (NumberFormatException e) {
            showWarning();
        }
        clearData();
    }

    public void removeScenarioClicked(ActionEvent actionEvent) {

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

            //TODO: THIS METHOD CALL RUNS ALL THE AI SYSTEMS
            tempScenario.runScenario();

            loadWindow("SimulationElevatorGraphWindow", (loader, stage) -> {
                loader.<SimulationElevatorGraphController>getController().initWithParent(this);

                stage.setOnCloseRequest(event -> {
                    ElevatorSimulationGraph.getDefaultGraph().stopTimeline();
                });
            });

        }
    }

    public void additionalScenarioOptionsClicked() {

        if (this.scenarioAddOnWindowOpened) {
            return;
        }

        if (!scenarioListView.getSelectionModel().isEmpty()) {

            String scenarioToBeLoaded = findNameForScenario(scenarioListView, false);

            BuildingScenario tempScenario = buildingScenarioManager.loadScenario(scenarioToBeLoaded);


            loadWindow("ScenarioAddOnWindow", (loader, stage) -> {
                this.scenarioAddOnWindowOpened = true;
                loader.<SimulationScenarioAddOnController>getController().initWithParent(this, tempScenario);

                stage.setOnCloseRequest(event -> this.scenarioAddOnWindowOpened = false);
            });
        }
    }

    private String findNameForScenario(ListView<String> scenarioListView, boolean shouldRemoveItem) {

        String scenario;
        int selectedItemIndex = scenarioListView.getSelectionModel().getSelectedIndex();
        scenario = scenarioListView.getItems().get(selectedItemIndex);

        if (shouldRemoveItem) {
            buildingScenarioManager.removeScenario(scenario, () -> scenarioListView.setItems(buildingScenarioManager.getScenarioEntries()));
            buildingScenarioManager.getScenarioEntries().remove(selectedItemIndex);


            // this.scenarioListView.getItems().remove(selectedItemIndex);
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

    private void showWarning() {
        warningTextLabel.setText("Entries must be integer values");
    }

    private void hideWarning() {
        warningTextLabel.setText("");
    }

    private void clearData() {
        this.numOfElevatorBanks.setText("");
        this.numOfFloors.setText("");
        this.numOfPassengers.setText("");
        this.scenarioName.setText("");
    }

    private String removeWhiteSpaceFromString(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        if (stringBuilder.charAt(0) != ' ') {
            return stringBuilder.toString();
        }
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == ' ') {


                stringBuilder.deleteCharAt(i);
                return removeWhiteSpaceFromString(stringBuilder.toString());
            }
        }

        throw new InputMismatchException();

    }


    private void setMaxSizeOfTextFields() {

    }


}






