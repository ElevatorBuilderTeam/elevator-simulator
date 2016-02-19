package elevatorsimulation.Model.AI;

import elevatorsimulation.Callback.AIRunnable;
import elevatorsimulation.Model.Elevator;
import elevatorsimulation.Model.ElevatorSimulationGraph;

/**
 * Created by Andrew on 2/18/2016.
 */
public class ElevatorAI implements AIRunnable {

    ElevatorSimulationGraph elevatorSimulationGraph;
    Elevator elevator;
    private static ElevatorAI elevatorAI;

    private ElevatorAI() {

    }

    public static ElevatorAI getAI() {
        if (elevatorAI == null) {
            elevatorAI = new ElevatorAI();
        }
        return elevatorAI;
    }

    public void moveSimulation() {

    }

    @Override
    public void runAISystems() {

    }

    @Override
    public void stopAISystems() {

    }
}
