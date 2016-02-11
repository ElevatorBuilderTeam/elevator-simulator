package elevatorsimulation.Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class ElevatorBank implements Serializable {
    private HashMap<Integer, Elevator> elevators;
    private int numberOfFloors;
    private StringBuilder bankID;

    private Building building;

    public ElevatorBank(Building building) {
        this.building = building;

        this.populateElevators(building.getBuildingFloors().size());

    }


    private void populateElevators(int numberOfFloors) {

        elevators = new HashMap<Integer, Elevator>();
        for(int i = 0 ; i < numberOfFloors; i++) {
            elevators.put(i, new Elevator(this));
        }
    }

    public HashMap<Integer, Elevator> getElevators() {
        return elevators;
    }

    public Building getBuilding() {
        return building;
    }

    public void addRequest(FloorRequest floorRequest){

    }

}
