package com.avsystem.system.factories;

import com.avsystem.system.elevators.Elevator;
import com.avsystem.system.elevators.fcfs.FCFSElevator;
import com.avsystem.system.elevators.scan.SCANElevator;

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
