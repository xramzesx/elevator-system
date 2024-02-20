package com.elevator.system.simulation.elevators.scan.states.queue;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.interfaces.State;
import com.elevator.system.records.ElevatorRequest;
import com.elevator.system.simulation.elevators.scan.SCANElevator;

public class InitSCANState extends QueueSCANState {

    public InitSCANState(SCANElevator elevator, ElevatorDirection direction) {
        super(elevator, direction);
    }

    @Override
    public State enter() {
        this.log("Init state machine");
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

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public Integer destination() {
        return this.forwardRequests.first().floor();
    }

    @Override
    public Integer current() {
        return this.elevator.getCurrentFloor();
    }
}
