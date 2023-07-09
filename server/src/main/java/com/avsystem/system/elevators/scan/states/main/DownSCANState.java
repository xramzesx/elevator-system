package com.avsystem.system.elevators.scan.states.main;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;
import com.avsystem.system.elevators.scan.states.SCANState;
import com.avsystem.system.elevators.scan.states.queue.EndSCANState;
import com.avsystem.system.elevators.scan.states.queue.InitSCANState;
import com.avsystem.system.elevators.scan.states.queue.QueueSCANState;

public class DownSCANState extends SCANState {
    private QueueSCANState substate;
    public DownSCANState(SCANElevator elevator) {
        super(elevator);
        this.substate = new InitSCANState(
            elevator,
            ElevatorDirection.DOWN
        );
    }

    @Override
    public State enter() {
        this.substate = (QueueSCANState) this.substate.step();

        if (this.substate instanceof EndSCANState) {
            return this.step();
        }

        return this;
    }

    @Override
    public State step() {
        /// DOWN STATE ///
        if (!(this.substate instanceof EndSCANState))
            return this.enter();

        /// UP STATE ///
        if (this.elevator.getUpwardRequests().size() > 0)  {
            State state = new UpSCANState(elevator);
            return state.enter();
        }

        /// IDLE STATE ///
        State state = new IdleSCANState(elevator);
        return state.enter();
    }

    @Override
    public boolean isOpen() {
        return this.substate.isOpen();
    }

    @Override
    public Integer destination() {
        return this.substate.destination();
    }

    @Override
    public Integer current() {
        return this.substate.current();
    }

    @Override
    public ElevatorDirection direction() {
        return ElevatorDirection.DOWN;
    }

}
