package elevatorsimulation.Model;

import elevatorsimulation.Model.Enums.BuildingVisitorEntryPoint;
import elevatorsimulation.Model.Enums.BuildingVisitorState;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by andrewlincoln on 1/31/16.
 */
public class BuildingVisitor implements Serializable {

    private int averageTimeOnFloor;
    private int timeInBuilding;
    private ElevatorBank currentElevatorBank;
    private BuildingVisitorState buildingVisitorState;






    BuildingFloor currentFloor;
    private BuildingVisitorEntryPoint buildingVisitorEntryPoint;
    ArrayList<FloorRequest> floorRequests;

    public BuildingVisitor() {
        floorRequests = new ArrayList<FloorRequest>();
        buildingVisitorEntryPoint = BuildingVisitorEntryPoint.LOBBY;
        buildingVisitorState = BuildingVisitorState.OUTSIDE_OF_BUILDING;


    }

    //SETTERS ***********************************************

    public void setBuildingVisitorState(BuildingVisitorState buildingVisitorState) {
        this.buildingVisitorState = buildingVisitorState;
        ElevatorSimulationGraph.getDefaultGraph().update(true);

    }

    public void setBuildingVisitorEntryPoint(BuildingVisitorEntryPoint buildingVisitorEntryPoint) {
        this.buildingVisitorEntryPoint = buildingVisitorEntryPoint;
    }

    public void setCurrentFloor(BuildingFloor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setTimeInBuilding(int timeInBuilding) {
        this.timeInBuilding = timeInBuilding;
    }

    public void setAverageTimeOnFloor(int averageTimeOnFloor) {
        this.averageTimeOnFloor = averageTimeOnFloor;
    }

    public void setElevatorBank(ElevatorBank elevatorBank) {
        this.currentElevatorBank = elevatorBank;
    }


    //GETTERS ***********************************************


    public BuildingFloor getCurrentFloor() {
        return currentFloor;
    }

    public BuildingVisitorState getBuildingVisitorState() {
        return buildingVisitorState;
    }

    public boolean isFloorRequestAvailable() {
        return getFloorRequests().isEmpty();
    }

    public ArrayList<FloorRequest> getFloorRequests() {
        return floorRequests;
    }

    public BuildingVisitorEntryPoint getBuildingVisitorEntryPoint() {
        return buildingVisitorEntryPoint;
    }

    public int getAverageTimeOnFloor() {
        return averageTimeOnFloor;
    }

    public ElevatorBank getCurrentElevatorBank() {
        return currentElevatorBank;
    }

    // PUBLIC INTERFACE ***********************************************

    public void enterFloor() {
        this.buildingVisitorState = BuildingVisitorState.ON_FLOOR;
    }

    public void enterElevator() {
        this.buildingVisitorState = BuildingVisitorState.IN_ELEVATOR;
    }

    public void enterBuilding() {


    }

    public void exitBuilding() {
        ElevatorSimulationGraph.getDefaultGraph().update(false);
    }


    // visitor will choose a random elevatorbank in the building.
    public void addToRequestQueue() {
        FloorRequest floorRequest = new FloorRequest(this.currentElevatorBank);


        this.floorRequests.add(floorRequest);
    }


}

