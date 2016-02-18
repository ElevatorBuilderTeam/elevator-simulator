package elevatorsimulation.Model;

import elevatorsimulation.Model.Enums.BuildingVisitorEntryPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by andrewlincoln on 1/31/16.
 */
public class Building implements Serializable {

    private ArrayList<BuildingVisitor> buildingVisitors;
    private HashMap<Integer,BuildingFloor> buildingFloors;
    private ArrayList<ElevatorBank> elevatorBanks;

    public Building(int numberOfFloors,int numberOfElevatorBanks, ArrayList<BuildingVisitor> buildingVisitors) {
        this.buildingVisitors = buildingVisitors;

        populateBuildingFloors(numberOfFloors);
        populateElevatorBank(numberOfElevatorBanks);
        setEntryPointForBuildingVisitors(buildingVisitors);
    }

    // PRIVATE IMPLEMENTATIONS  ***********************************************

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
        elevatorBanks = new ArrayList<>();
        for(int i = 0; i < numberOfElevatorBanks; i++) {
            elevatorBanks.add(i, new ElevatorBank(this));
        }
    }

    private void populateBuildingFloors(int numberOfBuildingFloors) {
        this.buildingFloors = new HashMap<>();
        for(int i = 0; i < numberOfBuildingFloors; i++) {
            buildingFloors.put(i, new BuildingFloor(i,0));
        }
    }

    // GETTERS  ***********************************************

    public HashMap<Integer, BuildingFloor> getBuildingFloors() {
        return buildingFloors;
    }

    public ElevatorBank getRandomElevatorBank() {
        return this.elevatorBanks.get(new Random().nextInt(elevatorBanks.size() - 1));
    }

    public ArrayList<BuildingVisitor> getBuildingVisitors() {
        return buildingVisitors;
    }
}
