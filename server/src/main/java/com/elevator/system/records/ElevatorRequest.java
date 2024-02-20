package com.elevator.system.records;

import com.elevator.system.enums.ElevatorDirection;

import java.util.Objects;

public record ElevatorRequest(
    Integer floor,
    ElevatorDirection direction
) {
    //// OVERRIDE ////

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ElevatorRequest other))
            return false;

        return
            this.floor.equals(other.floor) &&
            this.direction.equals(other.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.floor,
            this.direction
        );
    }
}
