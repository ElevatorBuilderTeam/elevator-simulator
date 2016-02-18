package elevatorsimulation.Model;

import elevatorsimulation.Model.AI.VisitorAI;
import elevatorsimulation.Model.Enums.BuildingVisitorEntryPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by andrewlincoln on 1/31/16.
 */
public class BuildingVisitor implements Serializable {
    private int averageTimeOnFloor;
    private int timeInBuilding;
    private VisitorAI visitorAI;
    private ElevatorBank currentElevatorBank;

    BuildingFloor currentFloor;
    private BuildingVisitorEntryPoint buildingVisitorEntryPoint;
    ArrayList<FloorRequest> floorRequests;

    public BuildingVisitor() {
        floorRequests = new ArrayList<FloorRequest>();
        buildingVisitorEntryPoint = BuildingVisitorEntryPoint.LOBBY;
        visitorAI = new VisitorAI(this);
    }

    //SETTERS ***********************************************

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

    public VisitorAI getVisitorAI() {
        return visitorAI;
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

    public void enterBuilding(int delay, int deviation) {
        visitorAI.runVisitorEnteringSequence(delay, deviation);
    }

    public void exitBuilding() {

    }


    // visitor will choose a random elevatorbank in the building.
    public void addToRequestQueue() {
        FloorRequest floorRequest = new FloorRequest(this.currentElevatorBank);

        floorRequest.setCurrentFloor(this.currentFloor);
        this.floorRequests.add(floorRequest);
    }

}

