package elevatorsimulation.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by andrewlincoln on 1/31/16.
 */
public class BuildingVisitor implements Serializable {
    int averageTimeOnFloor;
    ArrayList<FloorRequest> floorRequests;


    public BuildingVisitor(int averageTimeOnFloor) {
        floorRequests = new ArrayList<FloorRequest>();
        this.averageTimeOnFloor = averageTimeOnFloor;
    }

    public void enterBuilding() {

    }

    public void exitBuilding() {

    }

    public void addFloorRequest(FloorRequest floorRequest) {
        this.floorRequests.add(floorRequest);
    }

}
