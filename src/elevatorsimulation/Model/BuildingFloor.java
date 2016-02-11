package elevatorsimulation.Model;

import java.io.Serializable;

/**
 * Created by andrewlincoln on 1/30/16.
 */


public class BuildingFloor implements Serializable {

    private int floorLevel, accessCode;
    private FloorState floorState;
    private ElevatorBank elevatorBank;

    private StringBuilder floorName;



    public BuildingFloor(int floorLevel, int accessCode) {
        this.floorLevel = floorLevel;
        this.accessCode = accessCode;
        this.floorName = updateFloorName(floorLevel);

    }


    public StringBuilder getFloorName() {
        return floorName;
    }
    public int getFloorLevel() {
        return floorLevel;
    }

    public void allowAccess(int accessCode) {
        if (this.accessCode == accessCode) {
            //allow access
        }
    }

    private StringBuilder updateFloorName(int floorLevel) {

        switch (floorLevel) {
            case 0:
                return new StringBuilder("Garage");

            case 1:
                return new StringBuilder("Lobby");

            default:
                return new StringBuilder("Floor " + floorLevel);

        }
    }

}
