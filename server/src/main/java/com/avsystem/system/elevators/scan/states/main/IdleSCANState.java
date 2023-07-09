package com.avsystem.system.elevators.scan.states.main;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;
import com.avsystem.system.elevators.scan.states.SCANState;

public class IdleSCANState extends SCANState {
    public IdleSCANState(SCANElevator elevator) {
        super(elevator);
    }

    @Override
    public State enter() {
        System.out.println("Idle state reached");
        return this;
    }

    @Override
    public State step() {
        /// UP STATE ///
        if (this.elevator.getUpwardRequests().size() > 0) {
            State state = new UpSCANState(elevator);
            return state.enter();
        }

        /// DOWN STATE ///
        if (this.elevator.getDownwardRequests().size() > 0) {
            State state = new DownSCANState(elevator);
            return state.enter();
        }

        /// IDLE STATE ///
        return this.enter();
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

    @Override
    public ElevatorDirection direction() {
        return ElevatorDirection.IDLE;
    }

}
