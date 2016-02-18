package elevatorsimulation.Model;


import elevatorsimulation.Model.AI.VisitorAI;

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

        this.buildingVisitors = defaultVisitors(numberOfPassengers);
        building = new Building(numberOfFloors, numberOfElevatorBanks, buildingVisitors);

        //TODO This is a test that will be replaced later

        this.scenarioName = new StringBuilder(scenarioName);
        this.elevatorSimulatorGraph = elevatorSimulatorGraph;
        setRandomElevatorBankForVisitors();

        scenarioEntryText = this.scenarioName;
        elevatorSimulatorGraph.setBuildingScenario(this);
    }

    // PRIVATE IMPLEMENTATIONS ***********************************************

    private ArrayList<BuildingVisitor> defaultVisitors(int numberOfPassengers) {
        ArrayList<BuildingVisitor> visitors = new ArrayList<>();
        for (int i = 0; i < numberOfPassengers; i++) {
            BuildingVisitor buildingVisitor = new BuildingVisitor();

            visitors.add(new BuildingVisitor());
        }
        return visitors;
    }

    private void setRandomElevatorBankForVisitors() {
        for (BuildingVisitor visitor : this.buildingVisitors) {
            visitor.setElevatorBank(this.building.getRandomElevatorBank());
        }
    }

    //GETTERS ***********************************************

    public ElevatorSimulationGraph getElevatorSimulatorGraph() {
        return elevatorSimulatorGraph;
    }

    public StringBuilder getScenarioEntryText() {
        return scenarioEntryText;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    //PUBLIC INTERFACE ***********************************************

    public void runScenario() {

        for (BuildingVisitor visitor : buildingVisitors) {

            //TODO: Set a number of floor requests, default is a random amount based on the number of floors the building has

            VisitorAI visitorAI = visitor.getVisitorAI();

            visitorAI.setAmountOfFloorRequests(this.getNumberOfFloors());
            visitorAI.findRandomFloor();
            visitorAI.randomAverageTimeInBuilding(10);
            visitorAI.randomizeAverageTimeOnFloor(10, 4);
            visitorAI.randomizeEntryPoint();
            visitorAI.runVisitorEnteringSequence(10, 4);

            visitor.enterBuilding(5, 4);



        }
    }

}
