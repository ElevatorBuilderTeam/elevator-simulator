package elevatorsimulation.Main;

/*
 * Copyright 2016 Jelevator, Bristol Community College. All Rights Reserved
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
    stage.setResizable(false);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
  
}
