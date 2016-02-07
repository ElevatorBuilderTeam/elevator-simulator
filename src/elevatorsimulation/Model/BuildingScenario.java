package elevatorsimulation.Model;


/**
 * Created by andrewlincoln on 1/30/16.
 */
public class BuildingScenario {

    private int numberOfPassengers, numberOfFloors, numberOfElevatorBanks;
    private StringBuilder scenarioName;

    private ElevatorSimulationGraph elevatorSimulatorGraph;

    public BuildingScenario(int numberOfPassengers, int numberOfFloors, int numberOfElevatorBanks, String scenarioName, ElevatorSimulationGraph elevatorSimulatorGraph) {
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfFloors = numberOfFloors;
        this.numberOfElevatorBanks = numberOfElevatorBanks;
        this.scenarioName = new StringBuilder(scenarioName);
        this.elevatorSimulatorGraph = elevatorSimulatorGraph;
    }
}
