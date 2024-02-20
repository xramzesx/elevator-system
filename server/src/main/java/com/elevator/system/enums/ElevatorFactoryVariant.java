package com.elevator.system.enums;

import com.elevator.system.simulation.factories.ElevatorFactory;
import com.elevator.system.simulation.factories.FCFSElevatorFactory;
import com.elevator.system.simulation.factories.SCANElevatorFactory;

public enum ElevatorFactoryVariant {
    DEFAULT, SCAN, FCFS;

    public ElevatorFactory get(Integer elevatorCount) {
        return switch (this) {
            case DEFAULT, SCAN -> new SCANElevatorFactory(elevatorCount);
            case FCFS -> new FCFSElevatorFactory(elevatorCount);
        };
    }
}
