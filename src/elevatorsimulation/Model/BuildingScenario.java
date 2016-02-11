package elevatorsimulation.Model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class BuildingScenario implements Serializable {

    private int numberOfPassengers, numberOfFloors, numberOfElevatorBanks;
    private StringBuilder scenarioName;

    private ElevatorSimulationGraph elevatorSimulatorGraph;
    private Building building;
    private ArrayList<BuildingVisitor> buildingVisitors;
    private StringBuilder scenarioEntryText;


    public BuildingScenario(int numberOfPassengers, int numberOfFloors, int numberOfElevatorBanks, String scenarioName, ElevatorSimulationGraph elevatorSimulatorGraph) {
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfFloors = numberOfFloors;
        this.numberOfElevatorBanks = numberOfElevatorBanks;

        //TODO This is a test that will be replaced later
        this.buildingVisitors = defaultVisitors(numberOfPassengers);
        this.scenarioName = new StringBuilder(scenarioName);
        this.elevatorSimulatorGraph = elevatorSimulatorGraph;

        building = new Building(numberOfFloors, numberOfElevatorBanks, buildingVisitors);
        scenarioEntryText = this.scenarioName;
        elevatorSimulatorGraph.setBuildingScenario(this);
    }

    //GETTERS


    public ElevatorSimulationGraph getElevatorSimulatorGraph() {
        return elevatorSimulatorGraph;
    }

    public StringBuilder getScenarioEntryText() {
        return scenarioEntryText;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    private ArrayList<BuildingVisitor> defaultVisitors(int numberOfPassengers) {
        ArrayList<BuildingVisitor> visitors = new ArrayList<>();
        for (int i = 0; i < numberOfPassengers; i++) {
            if (i % 2 == 0) visitors.add(new BuildingVisitor(4));
            else visitors.add(new BuildingVisitor(5));
        }
        return visitors;
    }


    public void runScenario() {

    }

}
