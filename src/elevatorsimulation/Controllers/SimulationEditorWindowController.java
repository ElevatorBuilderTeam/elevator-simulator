/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorsimulation.Controllers;

import elevatorsimulation.Exceptions.ElevatorSimulationGraphNotFoundException;
import elevatorsimulation.Model.BuildingScenario;
import elevatorsimulation.Model.BuildingScenarioManager;
import elevatorsimulation.Model.ElevatorSimulationGraph;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author andrewlincoln
 */
public class SimulationEditorWindowController extends Controller implements Initializable
{


  public TextField scenarioName;
  public TextField numOfPassengers;
  public TextField numOfElevatorBanks;
  public TextField numOfFloors;

  private BuildingScenario buildingScenario;
  private BuildingScenarioManager buildingScenarioManager;




  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO

  }


  public void initWithParent(Controller controller) {
    this.parentController = controller;
    System.out.println(parentController);

  }


  public void addScenarioClicked(ActionEvent actionEvent) {

    buildingScenarioManager = BuildingScenarioManager.getDefaultManager();


    // parse from the input fields
    if (!valuesFromTextInputAreValid()) {
      return;
    }

    try {

      int numOfFloors = new Integer(this.numOfFloors.getText()).intValue();
      int numOfPassengers = new Integer(this.numOfPassengers.getText()).intValue();
      int numOfElevatorBanks = new Integer(this.numOfElevatorBanks.getText()).intValue();
      String scenarioName = this.scenarioName.getText();

      ElevatorSimulationGraph elevatorSimulationGraph = ((SimulationMenuWindowController) parentController).getElevatorSimulationGraph();

      if (elevatorSimulationGraph == null) {
        try {
          throw new ElevatorSimulationGraphNotFoundException();
        } catch (ElevatorSimulationGraphNotFoundException e) {
          e.printStackTrace();
        }
      }

      buildingScenarioManager.saveScenario(scenarioName, new BuildingScenario(numOfPassengers, numOfFloors, numOfElevatorBanks, scenarioName, elevatorSimulationGraph));
    } catch (NumberFormatException e) {
      System.out.println("Number format exception");
      // TODO: 2/7/2016 when doesn't enter an integer that causes this exception, show a label to help them input the correct data.
    }

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

}






