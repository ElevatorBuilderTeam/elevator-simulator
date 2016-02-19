package elevatorsimulation.Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class ElevatorBank implements Serializable {
    private HashMap<Integer, Elevator> elevators;
    private StringBuilder bankID;

    private Building building;

    public ElevatorBank(Building building) {
        this.building = building;

        // 4 elevators per bank
        this.populateElevators(4);

    }

    //PRIVATE IMPLEMENTATIONS ***********************************************
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


}
