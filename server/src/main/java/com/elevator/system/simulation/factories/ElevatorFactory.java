package com.avsystem.simulation.factories;

import com.avsystem.simulation.elevators.Elevator;

import java.util.List;

public abstract class ElevatorFactory {
    protected Integer elevatorCount;

    public ElevatorFactory(Integer elevatorCount) {
        this.elevatorCount = elevatorCount;
    }

    public abstract List<Elevator> build(Integer elevatorCount);
    public abstract List<Elevator> build();
}
