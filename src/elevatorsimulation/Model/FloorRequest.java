package elevatorsimulation.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class FloorRequest {
    HashMap<Integer, BuildingFloor> destinations;
    private ElevatorBank elevatorBank;
    private BuildingFloor currentFloor;
    private Elevator nextAvailableElevator;


    // have to make a floor Request from a certain floor
    public FloorRequest(ElevatorBank elevatorBank, BuildingFloor currentFloor) {
        this.destinations = new HashMap<Integer, BuildingFloor>();
        this.elevatorBank = elevatorBank;
        this.currentFloor = currentFloor;

    }


    public void callNextAvailableElevator(int floorDestination) {

        nextAvailableElevator = findAvailableElevator();

        nextAvailableElevator.moveTo(floorDestination);
    }

      /*
         If available, pick a standing elevator at this floor.

         Else pick an elevator moving to this floor.

         Else pick a standing elevator on another floor.

         Else pick the elevator with the lowest load on its request list.
       */


    private Elevator findAvailableElevator()  {

        for(Map.Entry<Integer, Elevator> entry : this.elevatorBank.getElevators().entrySet()) {
            if(entry.getValue().getElevatorState() == ElevatorState.STAND) {
                return findClosestStandElevator(this.elevatorBank);
            } else if (this.currentFloor == entry.getValue().getDestinationFloor()) {
                return entry.getValue();
            }

        }
        return findLowestCapacityElevator(this.elevatorBank);
    }


    private Elevator findClosestStandElevator(ElevatorBank elevatorBank) {
        //
        ArrayList<Elevator> standingElevators = new ArrayList<>();
        for (Map.Entry<Integer, Elevator> entry : this.elevatorBank.getElevators().entrySet()) {
            if (entry.getValue().getElevatorState() == ElevatorState.STAND) {
                standingElevators.add(entry.getValue());


            }

        }

        int closestFloor = 0;
        Elevator closestElevator = null;

        for (Elevator standingElevator : standingElevators) {
            // if the elevator in question is at this floor... obviously this must be the closest.
            if (standingElevator.getCurrentFloor() == this.currentFloor) {
                return standingElevator;
            }

            closestElevator = standingElevator;
            // compute the distance from this floor to the elevator in question
            int floorDistance = closestElevator.getCurrentFloor().getFloorLevel() - this.currentFloor.getFloorLevel();
            if (closestElevator.getCurrentFloor().getFloorLevel() < closestFloor) {
                closestElevator = standingElevator;
                closestFloor = floorDistance;
            }


            return closestElevator;

        }
        return null;
    }

    private Elevator findLowestCapacityElevator(ElevatorBank elevatorBank) {
        // hardcoded but will change by the building standards.
        int lowest = 50;
        Elevator lowestCapacityElevator = null;

        for(Map.Entry<Integer, Elevator> entry : this.elevatorBank.getElevators().entrySet()) {
            if(entry.getValue().getCurrentCapacity() == 0) {
                return entry.getValue();
            }

            if (entry.getValue().getCurrentCapacity() < lowest) {
                lowest = entry.getValue().getCurrentCapacity();
                lowestCapacityElevator = entry.getValue();
            }
        }
        return lowestCapacityElevator;
    }


}
