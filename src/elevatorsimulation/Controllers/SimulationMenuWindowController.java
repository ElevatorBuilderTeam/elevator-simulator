/*
  COPYRIGHT 2016 Andrew Lincoln, Andrew Briggs, Connor Dvorsky. All Rights Reserved.
 */
package elevatorsimulation.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author andrewlincoln
 */
public class SimulationMenuWindowController extends Controller implements Initializable {

  public MenuItem simulationEditorOption;
  public AnchorPane anchorPane;
  private boolean editorWindowOpened = false;

  /**
   * Initializes the controller class.
   */

  @Override
  public void initialize(URL url, ResourceBundle rb) {


  }
  
  public void simulationEditorOptionClicked() {
    // push the editor window on top of the frame.
    if (editorWindowOpened) {
      return;
    }
    this.loadWindow("SimulationEditorWindow", (loader, stage) -> {
      loader.<SimulationEditorWindowController>getController().initWithParent(this);
      editorWindowOpened = true;
      stage.setOnCloseRequest((event) -> {
        editorWindowOpened = false;
      });
    });
  }

  public void simulationAnimationOptionClicked() {

    this.loadWindow("SimulationAnimationWindow", (loader, stage) -> {

    });
  }


  public void exitMenuOptionClicked() {
    System.exit(0);
  }


}
