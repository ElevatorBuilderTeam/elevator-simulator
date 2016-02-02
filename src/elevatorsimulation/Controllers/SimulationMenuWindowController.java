/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorsimulation.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import elevatorsimulation.Callback.WindowDidLoadCallback;
import elevatorsimulation.Model.Building;
import elevatorsimulation.Model.BuildingVisitor;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andrewlincoln
 */
public class SimulationMenuWindowController implements Initializable
{
  ArrayList<BuildingVisitor> visitors = new ArrayList<BuildingVisitor>();
  Building testBuilding = new Building(10,10,visitors);
  int number = 5;
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
  public void simulationEditorOptionClicked() {
    // push the editor window on top of the frame.

    Region root;


    try {

      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("elevatorsimulation/Views/SimulationEditorWindow.fxml"));
      root = (Region) loader.load();


      Stage stage = new Stage();
      stage.setTitle("Simulation Editor");
      stage.setScene(new Scene(root));


      stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  public void simulationAnimationOptionClicked() {

  this.loadWindow("SimulationAnimationWindow", new WindowDidLoadCallback() {
    @Override
    public void runCallBack(FXMLLoader loader) {
      System.out.println("Cool");
    }
  });

  }

  public void exitMenuOptionClicked() {
    System.exit(0);
  }



  private void loadWindow(String resource, WindowDidLoadCallback callback) {

    Region root;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("elevatorsimulation/Views/" + resource + ".fxml"));

    try {
      root = (Region) loader.load();

      Stage stage = new Stage();
      stage.setTitle("Simulation Editor");
      stage.setScene(new Scene(root));

      callback.runCallBack(loader);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  
  
}
