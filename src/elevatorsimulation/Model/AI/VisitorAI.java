package elevatorsimulation.Model.AI;


import elevatorsimulation.Callback.AIRunnable;
import elevatorsimulation.Model.BuildingVisitor;
import elevatorsimulation.Model.ElevatorSimulationGraph;
import elevatorsimulation.Model.Enums.BuildingVisitorEntryPoint;
import elevatorsimulation.Model.Enums.BuildingVisitorState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew on 2/14/2016.
 */
public class VisitorAI implements AIRunnable {

    ArrayList<Timeline> timelines = new ArrayList<>();

    private int entranceDelay = 15;

    ElevatorSimulationGraph elevatorSimulationGraph;
    ArrayList<BuildingVisitor> buildingVisitors;
    int numberOfVisitors = 0;

    private static VisitorAI visitorAI = null;


    private VisitorAI() {

        this.elevatorSimulationGraph = ElevatorSimulationGraph.getDefaultGraph();
    }

    public static VisitorAI getAI() {
        if (visitorAI == null) {
            visitorAI = new VisitorAI();
        }

        return visitorAI;
    }

    private void enterBuilding() {

        for (BuildingVisitor buildingVisitor : buildingVisitors) {
            if (buildingVisitor.getBuildingVisitorState() == BuildingVisitorState.OUTSIDE_OF_BUILDING) {
                //startFloorTimer
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(randomNumberGeneration(entranceDelay, 1)), event -> {


                }));

                timeline.setOnFinished(actionEvent -> {
                    buildingVisitor.setBuildingVisitorState(BuildingVisitorState.INSIDE_OF_BUILDING);

                    System.out.println(buildingVisitor.getBuildingVisitorState());
                });

                timelines.add(timeline);
            }
        }


        // Iterate through the visitors
        //if the visitor is not on an elevator, visitor must be on floor
    }

    private void playAllTimelines() {
        for (Timeline timeline : timelines) {
            timeline.play();
        }
    }

    private void stopAllTimelines() {
        for (Timeline timeline : timelines) {
            timeline.stop();
        }
    }


    private void waitOnFloor() {

        // timeline = new Timeline(new KeyFrame(Duration.seconds(buildingVisitor.getAverageTimeOnFloor())));
    }

    //PRIVATE IMPLEMENTATIONS ***********************************************

    private void populateFloorRequests() {
        for (BuildingVisitor buildingVisitor : buildingVisitors) {
            int randomFloor = findRandomFloor();
            if (buildingVisitor.getFloorRequests().isEmpty()) {
                System.out.println("New Visitor Added");
                for (int i = 0; i < randomFloor; i++) {


                    System.out.println("Request: " + i + " out of: " + randomFloor);
                    buildingVisitor.addToRequestQueue();

                }
            }
        }
    }

    private int randomNumberGeneration(int value, int deviation) {
        if (deviation == 0) {
            System.out.println("Division By zero, run visitor entering sequence operation terminated");
            return 0;
        }
        int min = value - value / deviation;
        int max = value + value / deviation;

        return ThreadLocalRandom.current().nextInt((max - min) + 1);
    }

    //SETTERS ***********************************************


    public void setAmountOfFloorRequests(int amountOfFloorRequests) {

    }

    //PUBLIC INTERFACE ***********************************************


    public void addVisitors(ArrayList<BuildingVisitor> buildingVisitors) {
        this.buildingVisitors = buildingVisitors;
    }



    @Override
    public void runAISystems() {
        // Populate floor requests
        populateFloorRequests();
        enterBuilding();
        playAllTimelines();

        // have visitors randomly enter the building

        //the visitors will begin making floor requests and move from floor to floor

        // the visitors will stay on a floor for a period of time
        // the visitors will then make another floor request until the 'N'
        //visitors will leave the building when floor requests are empty
        //

    }

    @Override
    public void stopAISystems() {
        stopAllTimelines();
    }

    public int findRandomFloor() {

        int min = 0;
        int max = elevatorSimulationGraph.getMaxY();

        int randomNum = ThreadLocalRandom.current().nextInt((max - min) + 1);

        return randomNum;

    }


    public void randomAverageTimeInBuilding(int averageTime) {

        for (BuildingVisitor buildingVisitor : buildingVisitors) {
            buildingVisitor.setTimeInBuilding(randomNumberGeneration(averageTime, 4));
        }
    }


    public void randomizeAverageTimeOnFloor(int averageTime, int deviation) {

        for (BuildingVisitor buildingVisitor : buildingVisitors) {
            buildingVisitor.setAverageTimeOnFloor(randomNumberGeneration(averageTime, deviation));
        }
    }

    public void randomizeEntryPoint() {


        for (BuildingVisitor buildingVisitor : buildingVisitors) {
            int randomNum = ThreadLocalRandom.current().nextInt();

            if (randomNum % 2 == 0) {

                buildingVisitor.setBuildingVisitorEntryPoint(BuildingVisitorEntryPoint.GARAGE);
                return;
            }

            buildingVisitor.setBuildingVisitorEntryPoint(BuildingVisitorEntryPoint.LOBBY);
        }
    }

    public void stopVisitorEnteringSquence(int delay, int deviation) {

    }

    public static void runVisitorEnteringSequence(int delay, int deviation) {


    }


}
