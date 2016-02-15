package elevatorsimulation.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by andrewlincoln on 1/31/16.
 */
public class Building implements Serializable {

    private ArrayList<BuildingVisitor> buildingVisitors;
    private HashMap<Integer,BuildingFloor> buildingFloors;
    private ArrayList<ElevatorBank> elevatorBanks;

    public Building(int numberOfFloors,int numberOfElevatorBanks, ArrayList<BuildingVisitor> buildingVisitors) {
        populateBuildingFloors(numberOfFloors);
        populateElevatorBank(numberOfElevatorBanks);
        setEntryPointForBuildingVisitors(buildingVisitors);

    }


    private void setEntryPointForBuildingVisitors(ArrayList<BuildingVisitor> buildingVisitors) {

        for (BuildingVisitor visitor : buildingVisitors) {
            if (visitor.getBuildingVisitorEntryPoint() == BuildingVisitorEntryPoint.GARAGE) {
                visitor.setCurrentFloor(this.buildingFloors.get(0));
            } else {
                visitor.setCurrentFloor(this.buildingFloors.get(1));
            }
        }
    }


    private void populateElevatorBank(int numberOfElevatorBanks) {
        elevatorBanks = new ArrayList<ElevatorBank>();
        for(int i = 0; i < numberOfElevatorBanks; i++) {
            elevatorBanks.add(i, new ElevatorBank(this));
        }
    }

    private void populateBuildingFloors(int numberOfBuildingFloors) {
        this.buildingFloors = new HashMap<Integer, BuildingFloor>();
        for(int i = 0; i < numberOfBuildingFloors; i++) {
            buildingFloors.put(i, new BuildingFloor(i,0));
        }
    }

    public HashMap<Integer, BuildingFloor> getBuildingFloors() {
        return buildingFloors;
    }

    public ArrayList<BuildingVisitor> getBuildingVisitors() {
        return buildingVisitors;
    }
}
