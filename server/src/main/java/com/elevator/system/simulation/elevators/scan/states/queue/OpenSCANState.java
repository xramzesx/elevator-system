package com.avsystem.simulation.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.simulation.elevators.scan.SCANElevator;

public class OpenSCANState extends QueueSCANState {

    public OpenSCANState(SCANElevator elevator, ElevatorDirection direction) {
        super(elevator, direction);
    }

    @Override
    public State enter() {
        this.log("Open door at " + elevator.getCurrentFloor() + " floor");

        /// POP FIRST ELEMENT ///
        this.forwardRequests.pollFirst();

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
            State state = new MoveSCANState(elevator, direction);
            return state.enter();
        }

        /// OPEN STATE ///
        if (difference == 0) {
            return this.enter();
        }

        /// POSTPONE STATE ///
        State state = new PostponeSCANState(elevator, direction);
        return state.enter();
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public Integer destination() {
        return this.elevator.getCurrentFloor();
    }

    @Override
    public Integer current() {
        return this.elevator.getCurrentFloor();
    }
}
