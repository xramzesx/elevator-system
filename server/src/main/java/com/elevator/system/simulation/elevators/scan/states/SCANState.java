package com.elevator.system.simulation.elevators.scan.states;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.interfaces.State;
import com.elevator.system.simulation.elevators.scan.SCANElevator;

public abstract class SCANState implements State {
    protected final SCANElevator elevator;

    public SCANState(SCANElevator elevator) {
        this.elevator = elevator;
    }

    public abstract boolean isOpen();
    public abstract Integer destination();
    public abstract Integer current();
    public abstract ElevatorDirection direction();
}
