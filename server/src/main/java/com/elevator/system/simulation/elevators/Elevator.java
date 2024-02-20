package com.elevator.system.simulation.elevators;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.records.ElevatorRequest;
import com.elevator.system.records.ElevatorStatus;

import java.util.Set;

/**
 * Elevator is the abstract base class for each individual elevator.
 */
public abstract class Elevator {

    //// ATTRIBUTES ////

    protected final Integer elevatorId;
    protected Integer currentFloor = 0;
    protected Integer destinationFloor = 0;

    //// CONSTRUCTOR ////

    public Elevator (Integer elevatorId) {
        this.elevatorId = elevatorId;
    }

    //// ABSTRACT METHODS ////

    public abstract void step();

    public abstract void pickup(Integer floor, ElevatorDirection direction);

    public abstract void update(Integer startFloor, Integer finalFloor);

    public abstract Set<ElevatorRequest> requests();
    public abstract Integer remaining();

    //// COMMON METHODS ////

    public void pickup(Integer floor) {
        this.pickup(
            floor,
            ElevatorDirection.get(
                this.currentFloor,
                floor
            )
        );
    }

    public ElevatorStatus status() {
        return new ElevatorStatus(
            this.elevatorId,
            this.currentFloor,
            this.destinationFloor,
            this.isOpen()
        );
    }

    public Integer distance(Integer floor) {
        return Math.abs(currentFloor - floor);
    }

    public Boolean isOpen() {
        return this.currentFloor.equals(this.destinationFloor);
    }

    //// GETTERS ///
    public Integer getCurrentFloor() {
        return currentFloor;
    }
}
