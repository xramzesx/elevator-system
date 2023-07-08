package com.avsystem.system.elevators.scan.states;

import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;

public abstract class SCANState implements State {
    protected final SCANElevator elevator;

    public SCANState(SCANElevator elevator) {
        this.elevator = elevator;
    }
}
