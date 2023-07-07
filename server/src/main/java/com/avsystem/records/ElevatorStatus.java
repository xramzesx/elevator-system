package com.avsystem.records;

import com.avsystem.enums.ElevatorDirection;

import java.util.Objects;

public record ElevatorStatus(
        Integer elevatorId,
        Integer currentFloor,
        Integer destinationFloor
) {
    //// METHODS ////

    public ElevatorDirection getDirection() {
        return ElevatorDirection.get(currentFloor, destinationFloor);
    }

    //// OVERRIDE ////

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ElevatorStatus other))
            return false;

        return
            this.elevatorId.equals(other.elevatorId) &&
            this.currentFloor.equals(other.currentFloor) &&
            this.destinationFloor.equals(other.destinationFloor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.elevatorId,
            this.currentFloor,
            this.destinationFloor
        );
    }
}
