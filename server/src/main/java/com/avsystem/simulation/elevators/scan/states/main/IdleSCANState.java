package com.avsystem.simulation.elevators.scan.states.main;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.simulation.elevators.scan.SCANElevator;
import com.avsystem.simulation.elevators.scan.states.SCANState;

public class IdleSCANState extends SCANState {
    public IdleSCANState(SCANElevator elevator) {
        super(elevator);
    }

    @Override
    public State enter() {
        this.log("Idle state reached");
        this.elevator.getIdleRequests().remove(
            new ElevatorRequest(
                this.current(),
                ElevatorDirection.IDLE
            )
        );
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

    private void log(String string) {
        System.out.println(
            "> [" + this.elevator.status().elevatorId() + "][" +
            ElevatorDirection.IDLE + "|" + this.current() + "]: " + string
        );
    }
}
