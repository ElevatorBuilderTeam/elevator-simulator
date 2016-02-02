package elevatorsimulation.Model;

/**
 * Created by andrewlincoln on 1/30/16.
 */


public class BuildingFloor {

    private int floorLevel, accessCode;
    private FloorState floorState;


    private StringBuilder floorName;
    private ElevatorBank elevatorBank;


    public BuildingFloor(int floorLevel, int accessCode) {
        this.floorLevel = floorLevel;
        this.accessCode = accessCode;

        switch ( floorLevel ) {
            case 0:
                floorName = new StringBuilder("Garage");
                break;
            case 1:
                floorName = new StringBuilder("Lobby");
                break;
            default:
                floorName = new StringBuilder("Floor " + floorLevel);
                break;
        }
    }


    public StringBuilder getFloorName() {
        return floorName;
    }
    public int getFloorLevel() {
        return floorLevel;
    }

    public void allowAccess(int accessCode) {
        if (this.accessCode == accessCode) {
            //allow acess
        }
    }

}
