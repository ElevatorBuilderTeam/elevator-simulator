package elevatorsimulation.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by andrewlincoln on 1/31/16.
 */
public class BuildingVisitor implements Serializable {
    int averageTimeOnFloor;
    BuildingFloor currentFloor;
    private BuildingVisitorEntryPoint buildingVisitorEntryPoint;

    int timeInBuilding;
    ArrayList<FloorRequest> floorRequests;


    public BuildingVisitor() {
        floorRequests = new ArrayList<FloorRequest>();
        buildingVisitorEntryPoint = BuildingVisitorEntryPoint.LOBBY;
    }


    //SETTERS


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

    //GETTERS


    public ArrayList<FloorRequest> getFloorRequests() {
        return floorRequests;
    }

    public BuildingVisitorEntryPoint getBuildingVisitorEntryPoint() {
        return buildingVisitorEntryPoint;
    }

    public int getAverageTimeOnFloor() {
        return averageTimeOnFloor;
    }

    public void enterBuilding() {

    }

    public void exitBuilding() {

    }

    public void addFloorRequest(FloorRequest floorRequest) {

        floorRequest.setCurrentFloor(this.currentFloor);
        this.floorRequests.add(floorRequest);
    }

}
