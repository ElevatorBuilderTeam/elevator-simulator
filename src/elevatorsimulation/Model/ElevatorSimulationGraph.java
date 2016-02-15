package elevatorsimulation.Model;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by andrewlincoln on 1/30/16.
 */
public class ElevatorSimulationGraph implements Serializable {

    // Not sure if we need these.
    private LineChart<Integer, Integer> lineChart;
    private transient NumberAxis floorAxis;
    private transient NumberAxis timeAxis;
    private BuildingScenario buildingScenario;
    private int maxY, xBounds;

    private transient Timeline timeline;

    public ElevatorSimulationGraph() {


        timeline = new Timeline();
        timeline.setCycleCount(2);

    }

    public void setBuildingScenario(BuildingScenario buildingScenario) {
        this.buildingScenario = buildingScenario;

        this.maxY = buildingScenario.getNumberOfFloors();

    }

    public void setUpChart(LineChart lineChart, NumberAxis floorAxis, NumberAxis timeAxis) {

        this.lineChart = lineChart;
        this.floorAxis = floorAxis;
        this.timeAxis = timeAxis;

        lineChart.setCreateSymbols(false);
        // this.lineChart.setAnimated(false);
        setUpAxis();

        buildChart();
    }

    private void setUpAxis() {
        floorAxis.setAutoRanging(false);
        floorAxis.setTickUnit(1);
        timeAxis.setTickUnit(1);
        floorAxis.setMinorTickCount(1);
        timeAxis.setMinorTickCount(1);

        floorAxis.setLabel("Floor");
        timeAxis.setLabel("Time (in seconds)");
    }

    private void buildChart() {
        // make sure timeline exists since the timeline is a transient property
        if (timeline == null) {
            timeline = new Timeline();
        }

        this.floorAxis.setUpperBound(maxY);
        timeline.setCycleCount(1);

        Timeline secondTimeline = new Timeline();

        timeline.getKeyFrames().addAll(interpolationTest(0, 10, 5, 1, 0));
        timeline.getKeyFrames().addAll(interpolationTest(0, 10, 1, 5, 1));

        timeline.getKeyFrames().addAll(interpolationTest(0, 5, 3, 9, 2));

        timeline.play();

    }


    private void addVisitorsToChart() {

    }


    private ArrayList<KeyFrame> interpolationTest(int startTime, int endTime, int currentFloor, int destinationFloor, int elevatorID) {
        ArrayList<KeyFrame> frames = new ArrayList<>(endTime - startTime);

        // to hold a reference to the difference in time since the keyframe works in the reverse fashion.
        for (int i = startTime; i <= endTime; i++) {

            int dt = startTime;

            int dy = currentFloor;
            frames.add(new KeyFrame(Duration.seconds(startTime), event -> addChartData(dt, dy, elevatorID)));
            System.out.println("Start Time" + (startTime) + " X: " + dt + " Y: " + dy + " Current Floor: " + currentFloor);

            if (dy < destinationFloor) {
                currentFloor++;
            }
            if (dy > destinationFloor) {
                currentFloor--;
            }

            startTime++;
        }

        return frames;
    }

    private void addChartData(int xPos, int yPos, int elevator) {
        XYChart.Series<Integer, Integer> series;
        final int ASCII_CHARACTER__SHIFT = 65;
        try {
            // Every visitor gets its own series
            // the series has an this element, so get that
            // add the the new position to the current series

            series = lineChart.getData().get(elevator);

            System.out.println(elevator);

        } catch (IndexOutOfBoundsException e) {

            series = new XYChart.Series<>();
            series.setName("Elevator " + (char) (elevator + ASCII_CHARACTER__SHIFT));

            lineChart.getData().add(elevator, series);

        }
        // populating the series with data

        series.getData().add(new XYChart.Data<>(xPos, yPos));
    }
}

