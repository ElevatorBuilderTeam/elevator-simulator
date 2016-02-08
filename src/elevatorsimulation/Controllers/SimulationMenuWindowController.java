/*
  COPYRIGHT 2016 Andrew Lincoln, Andrew Briggs, Connor Dvorsky. All Rights Reserved.
 */
package elevatorsimulation.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author andrewlincoln
 */
public class SimulationMenuWindowController extends Controller implements Initializable {

  public MenuItem simulationEditorOption;

  int number = 5;
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

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


  public void runSimulationClicked(ActionEvent actionEvent) {

    System.out.println("Clicked");

  }

  public void exitMenuOptionClicked() {
    System.exit(0);
  }


}
