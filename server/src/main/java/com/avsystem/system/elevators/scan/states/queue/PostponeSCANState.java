package com.avsystem.system.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;

public class PostponeSCANState extends QueueSCANState {

    public PostponeSCANState(SCANElevator elevator, ElevatorDirection direction) {
        super(elevator, direction);
    }

    @Override
    public State enter() {
        System.out.println(
            direction + ": Postponed request from " + this.forwardRequests.first().floor() + ". floor"
        );
        this.postponedRequests.add(
            this.forwardRequests.pollFirst()
        );
        return this.step();
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
            State state = new OpenSCANState(elevator, direction);
            return state.enter();
        }

        /// POSTPONE STATE ///
        return this.enter();
    }
}
