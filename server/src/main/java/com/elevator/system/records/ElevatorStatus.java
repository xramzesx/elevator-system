package com.elevator.system.records;

import com.elevator.system.enums.ElevatorDirection;

import java.util.Objects;

public record ElevatorStatus(
        Integer elevatorId,
        Integer current,
        Integer destination,
        Boolean isOpen
) {
    //// METHODS ////

    public ElevatorDirection getDirection() {
        return ElevatorDirection.get(current, destination);
    }

    //// OVERRIDE ////

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ElevatorStatus other))
            return false;

        return
            this.elevatorId.equals(other.elevatorId) &&
            this.current.equals(other.current) &&
            this.destination.equals(other.destination) &&
            this.isOpen.equals(other.isOpen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.elevatorId,
            this.current,
            this.destination
        );
    }
}
