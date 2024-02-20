package com.elevator.system.simulation.elevators.scan.states.main;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.interfaces.State;
import com.elevator.system.simulation.elevators.scan.SCANElevator;
import com.elevator.system.simulation.elevators.scan.states.SCANState;
import com.elevator.system.simulation.elevators.scan.states.queue.EndSCANState;
import com.elevator.system.simulation.elevators.scan.states.queue.InitSCANState;
import com.elevator.system.simulation.elevators.scan.states.queue.QueueSCANState;

public class UpSCANState extends SCANState {
    private QueueSCANState substate;

    public UpSCANState(SCANElevator elevator) {
        super(elevator);
        this.substate = new InitSCANState(
            elevator,
            ElevatorDirection.UP
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
        /// UP STATE ///
        if (!(this.substate instanceof EndSCANState))
            return this.enter();

        /// DOWN STATE ///
        if (this.elevator.getDownwardRequests().size() > 0)  {
            State state = new DownSCANState(elevator);
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
        return ElevatorDirection.UP;
    }

}
