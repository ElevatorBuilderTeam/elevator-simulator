package elevatorsimulation.Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author andrewlincoln
 */
public class ElevatorSimulation extends Application
{
  
  @Override
  public void start(Stage stage) throws Exception {
    Parent root;
    root = FXMLLoader.load(getClass().getClassLoader().getResource("elevatorsimulation/Views/SimulationMenuWindow.fxml"));
    
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
