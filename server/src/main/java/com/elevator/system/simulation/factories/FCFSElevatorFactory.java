package com.elevator.system.simulation.factories;

import com.elevator.system.simulation.elevators.Elevator;
import com.elevator.system.simulation.elevators.fcfs.FCFSElevator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FCFSElevatorFactory extends ElevatorFactory {
    public FCFSElevatorFactory(Integer elevatorCount) {
        super(elevatorCount);
    }


    @Override
    public List<Elevator> build(Integer elevatorCount) {
        this.elevatorCount = elevatorCount;

        List<Elevator> result = new CopyOnWriteArrayList<>();
        for (Integer elevatorId = 0; elevatorId < elevatorCount; elevatorId++) {
            result.add(new FCFSElevator(elevatorId));
        }

        return result;
    }

    @Override
    public List<Elevator> build() {
        return this.build(this.elevatorCount);
    }

}
