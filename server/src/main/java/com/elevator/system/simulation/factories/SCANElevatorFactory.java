package com.avsystem.simulation.factories;

import com.avsystem.simulation.elevators.Elevator;
import com.avsystem.simulation.elevators.scan.SCANElevator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SCANElevatorFactory extends ElevatorFactory {

    public SCANElevatorFactory(Integer elevatorCount) {
        super(elevatorCount);
    }

    @Override
    public List<Elevator> build(Integer elevatorCount) {
        this.elevatorCount = elevatorCount;

        List<Elevator> result = new CopyOnWriteArrayList<>();

        for (Integer elevatorId = 0; elevatorId < elevatorCount; elevatorId++) {
            result.add(new SCANElevator(elevatorId));
        }
        return result;
    }

    @Override
    public List<Elevator> build() {
        return this.build(this.elevatorCount);
    }
}
