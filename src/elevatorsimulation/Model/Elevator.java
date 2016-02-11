package elevatorsimulation.Model;

import elevatorsimulation.Exceptions.BuildingFloorValueNotFoundException;

import java.io.Serializable;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class Elevator implements Serializable {

    private BuildingFloor currentFloor, destinationFloor, maxFloor;
    public int currentCapacity, maxCapacity, speed, maxSpeed;
    private boolean isOperational, isMoving, isEmpty, isAtCapacity, doorsOpen, doorsClosed, isLightOn;

    private ElevatorState elevatorState;
    private ElevatorDirection elevatorDirection;
    private ElevatorBank elevatorBank;

    private String identifier;

    public BuildingFloor getDestinationFloor() {
        return destinationFloor;
    }

    public Elevator(ElevatorBank elevatorBank) {
        this.elevatorBank = elevatorBank;
    }

    public BuildingFloor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(BuildingFloor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int currentSpeed) {
        this.speed = speed;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isAtCapacity() {
        return isAtCapacity;
    }

    public void setAtCapacity(boolean atCapacity) {
        isAtCapacity = atCapacity;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public ElevatorDirection getElevatorDirection() {
        return elevatorDirection;
    }
    
    public String getIdentifier() {
        return identifier;
    }


    // private implementations

    private BuildingFloor checkFloorAccessibility(int floorLevel) throws BuildingFloorValueNotFoundException {


        BuildingFloor value = this.elevatorBank.getBuilding().getBuildingFloors().get(floorLevel);
        if(value != null) {
            //check to see if the floor is not accessible
            // if it is not, return to the last floor
            return value;
        }

        if(this.elevatorBank.getBuilding().getBuildingFloors().containsKey(floorLevel)) {

            throw new BuildingFloorValueNotFoundException();
        }

        return null;
    }

    //public specification

    public void moveTo(BuildingFloor buildingFloor) {

    }

    public BuildingFloor moveTo(int floorLevel) {

        //update the state of the elevator
        if(floorLevel > this.currentFloor.getFloorLevel()) {
            this.elevatorState = ElevatorState.MOVINGUP;
            this.elevatorDirection = ElevatorDirection.UP;
        }

        if(floorLevel < this.currentFloor.getFloorLevel()) {
            this.elevatorState = ElevatorState.MOVINGDOWN;
            this.elevatorDirection = ElevatorDirection.DOWN;
        }

        if(floorLevel == this.currentFloor.getFloorLevel()) {
            System.out.println("we are currently not moving");
            return this.currentFloor;
        }

        try {
            currentFloor = checkFloorAccessibility(floorLevel);
            return currentFloor;
        } catch (BuildingFloorValueNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disable() {
        this.isOperational = false;

    }



    public void openDoors() {
        //make sure the elevator is not moving.
        if(this.speed == 0) {

            this.doorsOpen = true;
            this.doorsClosed = false;
            this.updateState();
        }
    }

    public void closeDoors() {
        this.doorsClosed = true;
        this.doorsOpen = false;
        this.updateState();
    }

    private void updateState() {
        /*
        The doors of the elevator can be open or closed. The doors can open only after a full stop.
        The normal door position is closed.
        The doors open only by the rider's request.
        The doors shall stay open only for a period of boarding time.
         */
        if (this.doorsClosed && this.speed == 0) {
            this.elevatorState = ElevatorState.STAND;
        } else if (this.doorsOpen && this.speed == 0) {

            this.elevatorState = ElevatorState.LOADING;
        } else {

            this.elevatorState = ElevatorState.MAINTENANCE;
        }

    }

}
