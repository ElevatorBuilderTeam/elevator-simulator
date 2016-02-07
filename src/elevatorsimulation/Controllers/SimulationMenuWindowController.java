/*
  COPYRIGHT 2016 Andrew Lincoln, Andrew Briggs, Connor Dvorsky. All Rights Reserved.
 */
package elevatorsimulation.Controllers;

import elevatorsimulation.Model.Building;
import elevatorsimulation.Model.BuildingVisitor;
import elevatorsimulation.Model.ElevatorSimulationGraph;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author andrewlincoln
 */
public class SimulationMenuWindowController extends Controller implements Initializable {

  public MenuItem simulationEditorOption;
  public LineChart elevatorChart;
  public CategoryAxis timeAxis;
  public NumberAxis floorAxis;
  private ElevatorSimulationGraph elevatorSimulationGraph;


  ArrayList<BuildingVisitor> visitors = new ArrayList<BuildingVisitor>();
  Building testBuilding = new Building(10,10,visitors);

  int number = 5;
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO

    elevatorSimulationGraph = new ElevatorSimulationGraph(elevatorChart, floorAxis, timeAxis);

  }  
  
  public void simulationEditorOptionClicked() {
    // push the editor window on top of the frame.
    this.loadWindow("SimulationEditorWindow", (FXMLLoader loader) -> {
      loader.<SimulationEditorWindowController>getController().initWithParent(this);

    });
  }

  public void simulationAnimationOptionClicked() {

    this.loadWindow("SimulationAnimationWindow", (FXMLLoader loader) -> {


    });
  }

  public void exitMenuOptionClicked() {
    System.exit(0);
  }

  public ElevatorSimulationGraph getElevatorSimulationGraph() {
    return elevatorSimulationGraph;
  }

}
