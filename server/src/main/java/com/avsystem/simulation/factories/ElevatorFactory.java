package com.avsystem.system.factories;

import com.avsystem.system.elevators.Elevator;

import java.util.List;

public abstract class ElevatorFactory {
    protected Integer elevatorCount;

    public ElevatorFactory(Integer elevatorCount) {
        this.elevatorCount = elevatorCount;
    }

    public abstract List<Elevator> build(Integer elevatorCount);
    public abstract List<Elevator> build();
}
