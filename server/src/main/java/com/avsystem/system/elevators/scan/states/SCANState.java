package com.avsystem.system.elevators.scan.states;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;

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
