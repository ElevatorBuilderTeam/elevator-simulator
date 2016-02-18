package elevatorsimulation.Model.AI;


import elevatorsimulation.Callback.AIRunnable;
import elevatorsimulation.Callback.CompletionHandler;
import elevatorsimulation.Model.BuildingVisitor;
import elevatorsimulation.Model.Elevator;
import elevatorsimulation.Model.ElevatorSimulationGraph;
import elevatorsimulation.Model.Enums.BuildingVisitorEntryPoint;
import elevatorsimulation.Model.FloorRequest;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew on 2/14/2016.
 */
public class VisitorAI implements AIRunnable {

    Timer timer = new Timer();

    ElevatorSimulationGraph elevatorSimulationGraph;
    BuildingVisitor buildingVisitor;


    //Inner Class that will provide a random timing mechanism

    // *******************************************************
    public class Task extends TimerTask {

        int value;
        int deviation;
        int randomTime;
        CompletionHandler completionHandler;


        public Task(int value, int deviation, CompletionHandler handler) {
            this.value = value;
            this.deviation = deviation;
            this.completionHandler = handler;
            this.randomTime = randomNumberGeneration(value, deviation);
        }

        @Override
        public void run() {

            timer.schedule(new Task(value, deviation, completionHandler), randomTime);
            completionHandler.completed();
            //  System.out.println(new Date());
        }
    }
// ***********************************************************
    // end inner class

    public VisitorAI(BuildingVisitor buildingVisitor) {
        this.buildingVisitor = buildingVisitor;
        this.elevatorSimulationGraph = ElevatorSimulationGraph.getDefaultGraph();


    }

    private void waitOnFloor() {

        //tempTime is the wait period the floor,
        //we begin counting this down
        int tempTime = buildingVisitor.getAverageTimeOnFloor();


        timer.schedule(new TimerTask() {
            // local reference
            int i = tempTime;

            @Override
            public void run() {
                i--;
                // the timer ran out and there is an elevator available
                if (i == 0 && floorRequestAvailable()) {


                    // make cancel this process and request a floor
                    timer.cancel();
                }

            }
        }, 0, tempTime);

    }

    //PRIVATE IMPLEMENTATIONS ***********************************************

    private void populateFloorRequests() {

        int randomFloor = findRandomFloor();
        if (this.buildingVisitor.getFloorRequests().isEmpty()) {
            System.out.println("New Visitor Added");
            for (int i = 0; i < randomFloor; i++) {


                System.out.println("Request: " + i + " out of: " + randomFloor);
                this.buildingVisitor.addToRequestQueue();

            }
        }
    }

    private Elevator getLastElevatorIndex() {
        if (buildingVisitor.getFloorRequests().isEmpty()) {
            System.out.println("empty requests");
            return null;
        }

        FloorRequest request = buildingVisitor.getFloorRequests().get(buildingVisitor.getFloorRequests().size() - 1);
        return request.callNextAvailableElevator(findRandomFloor());
    }

    private boolean floorRequestAvailable() {
        return !buildingVisitor.getFloorRequests().isEmpty();
    }

    private int randomNumberGeneration(int value, int deviation) {
        if (deviation == 0) {
            System.out.println("Division By zero, run visitor entering sequence operation terminated");
            return 0;
        }
        int min = (int) Duration.seconds(value - value / deviation).toMillis();
        int max = (int) Duration.seconds(value + value / deviation).toMillis();

        return ThreadLocalRandom.current().nextInt((max - min) + 1);
    }

    private void requestFloor() {


        Elevator requestElevator = getLastElevatorIndex();
    }


    //SETTERS ***********************************************


    public void setAmountOfFloorRequests(int amountOfFloorRequests) {

    }


    //PUBLIC INTERFACE ***********************************************


    @Override
    public void runAISystems() {
        // Populate floor requests
        populateFloorRequests();


        // have visitors randomly enter the building

        //the visitors will begin making floor requests and move from floor to floor
        // the visitors will stay on a floor for a period of time
        // the visitors will then make another floor request until the 'N'
        //visitors will leave the building when floor requests are empty
        buildingVisitor.exitBuilding();
        //

    }

    public int findRandomFloor() {

        int min = 0;
        int max = elevatorSimulationGraph.getMaxY();

        int randomNum = ThreadLocalRandom.current().nextInt((max - min) + 1);

        return randomNum;

    }


    public void randomAverageTimeInBuilding(int averageTime) {

        int randomNum = randomNumberGeneration(averageTime, 4);
        buildingVisitor.setTimeInBuilding(randomNum);
    }


    public void randomizeAverageTimeOnFloor(int averageTime, int deviation) {


        int randomNum = randomNumberGeneration(averageTime, deviation);

        buildingVisitor.setAverageTimeOnFloor(randomNum);
    }

    public void randomizeEntryPoint() {

        int randomNum = ThreadLocalRandom.current().nextInt();

        if (randomNum % 2 == 0) {

            buildingVisitor.setBuildingVisitorEntryPoint(BuildingVisitorEntryPoint.GARAGE);
            return;
        }

        buildingVisitor.setBuildingVisitorEntryPoint(BuildingVisitorEntryPoint.LOBBY);
    }

    public void runVisitorEnteringSequence(int delay, int deviation) {
        new Task(delay, deviation, () -> {

            runAISystems();
            waitOnFloor();
            requestFloor();


        }).run();

    }


}
