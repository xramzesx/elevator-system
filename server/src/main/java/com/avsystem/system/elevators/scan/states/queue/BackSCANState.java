package com.avsystem.system.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;

public class BackSCANState extends QueueSCANState {

    public BackSCANState(SCANElevator elevator, ElevatorDirection direction) {
        super(elevator, direction);
    }

    @Override
    public State enter() {
        System.out.println(direction + ": Back reached");

        /// TODO: check if opposed ElevatorDirection affects on algorithm
        this.backwardRequests.add(forwardRequests.pollFirst());

        return this.step();
    }

    @Override
    public State step() {
        State state = new EndSCANState(elevator, direction);
        return state.enter();
    }

    @Override
    public boolean isOpen() {
        return false;
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
