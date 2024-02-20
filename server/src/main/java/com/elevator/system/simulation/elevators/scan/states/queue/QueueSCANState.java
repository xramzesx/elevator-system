package com.elevator.system.simulation.elevators.scan.states.queue;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.records.ElevatorRequest;
import com.elevator.system.simulation.elevators.scan.SCANElevator;
import com.elevator.system.simulation.elevators.scan.states.SCANState;

import java.util.Queue;
import java.util.TreeSet;

public abstract class QueueSCANState extends SCANState {
    protected final ElevatorDirection direction;

    //// REFERENCES ////
    protected final TreeSet<ElevatorRequest> forwardRequests;
    protected final TreeSet<ElevatorRequest> backwardRequests;
    protected final Queue<ElevatorRequest> postponedRequests;

    //// CONSTRUCTOR ////

    public QueueSCANState(SCANElevator elevator, ElevatorDirection direction) {
        super(elevator);
        this.direction = direction;

        this.postponedRequests = this.elevator.getPostponedRequests();

        switch (direction) {
            case UP -> {
                this.forwardRequests = (TreeSet<ElevatorRequest>) elevator.getUpwardRequests();
                this.backwardRequests = (TreeSet<ElevatorRequest>) elevator.getDownwardRequests();
            }
            case DOWN -> {
                this.forwardRequests = (TreeSet<ElevatorRequest>) elevator.getDownwardRequests();
                this.backwardRequests = (TreeSet<ElevatorRequest>) elevator.getUpwardRequests();
            }
            default -> {
                /// This part of the code will never be executed
                /// in SCAN state machine
                this.forwardRequests = null;
                this.backwardRequests = null;
            }
        }
    }

    protected int compare(Integer floorA, Integer floorB) {
        return  direction.equals(ElevatorDirection.UP)
            ? floorA - floorB
            : floorB - floorA;
    }

    @Override
    public ElevatorDirection direction() {
        return this.direction;
    }

    protected void log(String string) {
        System.out.println("> ["+this.elevator.status().elevatorId() + "][" + this.direction + "|"+ this.current() +"]: " + string);
    }
}
