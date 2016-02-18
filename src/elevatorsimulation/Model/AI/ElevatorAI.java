package elevatorsimulation.Model.AI;

import elevatorsimulation.Model.Elevator;
import elevatorsimulation.Model.ElevatorSimulationGraph;

/**
 * Created by Andrew on 2/18/2016.
 */
public class ElevatorAI {

    ElevatorSimulationGraph elevatorSimulationGraph;
    Elevator elevator;

    public ElevatorAI(Elevator elevator) {
        this.elevatorSimulationGraph = ElevatorSimulationGraph.getDefaultGraph();
        this.elevator = elevator;
    }


    public void moveSimulation() {

    }
}
