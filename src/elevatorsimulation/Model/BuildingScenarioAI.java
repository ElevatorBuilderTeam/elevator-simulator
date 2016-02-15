package elevatorsimulation.Model;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew on 2/14/2016.
 */
public class BuildingScenarioAI {


    public BuildingScenarioAI() {

    }

    private void makeNewFloorRequest(BuildingVisitor buildingVisitor, BuildingScenario buildingScenario) {

        int tempTime = buildingVisitor.getAverageTimeOnFloor();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int i = tempTime;

                if (i == 0 && floorRequestAvailable(buildingVisitor)) {

                }

            }


        }, 0, tempTime);

    }


    private boolean floorRequestAvailable(BuildingVisitor buildingVisitor) {
        return !buildingVisitor.getFloorRequests().isEmpty();
    }

    public int findRandomFloor(BuildingScenario buildingScenario) {
        int min = 0;
        int max = buildingScenario.getNumberOfFloors();

        int randomNum = ThreadLocalRandom.current().nextInt((max - min) + 1);

        return randomNum;

    }

    public void randomAverageTimeInBuilding(BuildingVisitor buildingVisitor, int averageTime) {

        int min = averageTime - averageTime / 4;
        int max = averageTime + averageTime / 4;

        int randomNum = ThreadLocalRandom.current().nextInt((max - min) + 1);

        buildingVisitor.setTimeInBuilding(randomNum);
    }


    public void randomizeAverageTimeOnFloor(BuildingVisitor buildingVisitor, int averageTime) {
        int min = averageTime - averageTime / 4;
        int max = averageTime + averageTime / 4;

        int randomNum = ThreadLocalRandom.current().nextInt((max - min) + 1);

        buildingVisitor.setAverageTimeOnFloor(randomNum);
    }

    public void randomizeEntryPoint(BuildingVisitor buildingVisitor) {

        int randomNum = ThreadLocalRandom.current().nextInt();

        if (randomNum % 2 == 0) {

            buildingVisitor.setBuildingVisitorEntryPoint(BuildingVisitorEntryPoint.GARAGE);
            return;
        }

        buildingVisitor.setBuildingVisitorEntryPoint(BuildingVisitorEntryPoint.LOBBY);
    }

}
