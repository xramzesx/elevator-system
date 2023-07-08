package com.avsystem.system.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.records.ElevatorStatus;
import com.avsystem.system.elevators.scan.SCANElevator;

public class MoveSCANState extends QueueSCANState {

    public MoveSCANState(SCANElevator elevator, ElevatorDirection direction) {
        super(elevator, direction);
    }

    @Override
    public State enter() {
        /// TODO: do it better
        this.elevator.move(direction);
        ElevatorStatus status = this.elevator.status();

        System.out.println(
            direction + ": Moved el:" + status.elevatorId() + " to " + status.currentFloor() + ". floor"
        );
        return this;
    }

    @Override
    public State step() {
        /// END STATE ///
        if (this.forwardRequests.size() == 0) {
            State state = new EndSCANState(elevator, direction);
            return state.enter();
        }

        int difference = this.compare(
            this.forwardRequests.first().floor(),
            this.elevator.getCurrentFloor()
        );

        /// MOVE STATE ///
        if (difference > 0) {
            return this.enter();
        }

        /// OPEN STATE ///
        if (difference == 0) {
            State state = new OpenSCANState(elevator, direction);
            return state.enter();
        }

        /// POSTPONE STATE ///
        State state = new PostponeSCANState(elevator, direction);
        return state.enter();
    }
}
