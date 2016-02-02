/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorsimulation.Controllers;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import elevatorsimulation.Model.Building;
import elevatorsimulation.Model.BuildingVisitor;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author andrewlincoln
 */
public class SimulationEditorWindowController implements Initializable
{
    SimulationMenuWindowController simulationMenuWindowController;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO

    }

  public void referenceParent(SimulationMenuWindowController simulationMenuWindowController) {
      this.simulationMenuWindowController = simulationMenuWindowController;

  }

}
