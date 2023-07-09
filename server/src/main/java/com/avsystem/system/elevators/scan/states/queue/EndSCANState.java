package com.avsystem.system.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;

public class EndSCANState extends QueueSCANState {

    public EndSCANState(SCANElevator elevator, ElevatorDirection direction) {
        super(elevator, direction);
    }

    @Override
    public State enter() {
        System.out.println(direction.toString() + ": End reached");

        this.forwardRequests.addAll(this.postponedRequests);
        this.postponedRequests.clear();

        return this;
    }

    @Override
    public State step() {
        return this;
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
