package com.avsystem.system.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.system.elevators.scan.SCANElevator;

public class InitSCANState extends QueueSCANState {

    public InitSCANState(SCANElevator elevator, ElevatorDirection direction) {
        super(elevator, direction);
    }

    @Override
    public State enter() {
        System.out.println(direction + ": Init state machine");
        return this.step();
    }

    @Override
    public State step() {
        //// END STATE ////
        if (this.forwardRequests.size() == 0) {
            State state = new EndSCANState(elevator, direction);
            return state.enter();
        }

        ElevatorRequest request = this.forwardRequests.first();
        int difference = this.compare(request.floor(), this.elevator.getCurrentFloor());

        /// BACK STATE ///
        if (difference < 0) {
            State state = new BackSCANState(elevator, direction);
            return state.enter();
        }

        /// OPEN STATE ///
        if (difference == 0) {
            State state = new OpenSCANState(elevator, direction);
            return state.enter();
        }

        /// MOVE STATE ///
        State state = new MoveSCANState(elevator, direction);
        return state.enter();
    }
}
