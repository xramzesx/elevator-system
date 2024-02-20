package com.elevator.system.simulation.elevators.scan.states.queue;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.interfaces.State;
import com.elevator.system.records.ElevatorStatus;
import com.elevator.system.simulation.elevators.scan.SCANElevator;

public class MoveSCANState extends QueueSCANState {

    public MoveSCANState(SCANElevator elevator, ElevatorDirection direction) {
        super(elevator, direction);
    }

    @Override
    public State enter() {
        Integer prevFloor = this.elevator.getCurrentFloor();
        Integer destination = this.forwardRequests.first().floor();

        /// TODO: do it better
        this.elevator.move(direction);
        ElevatorStatus status = this.elevator.status();

        this.log(
            "from: " + prevFloor +
            ". to: " + status.current()
        );

        return this.elevator
            .getCurrentFloor()
            .equals(
                this.forwardRequests.first().floor()
            )
                ? this.step()
                : this;

    }

    @Override
    public State step() {
        /// END STATE ///
        if (this.forwardRequests.size() == 0) {
            State state = new EndSCANState(elevator, direction);
            return state.enter();
        }

        int difference = this.compare(
            this.forwardRequests.first().floor(),
            this.elevator.getCurrentFloor()
        );

        /// MOVE STATE ///
        if (difference > 0) {
            return this.enter();
        }

        /// OPEN STATE ///
        if (difference == 0) {
            State state = new OpenSCANState(elevator, direction);
            return state.enter();
        }

        /// POSTPONE STATE ///
        State state = new PostponeSCANState(elevator, direction);
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
