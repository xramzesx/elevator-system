package com.avsystem.enums;

import com.avsystem.simulation.factories.ElevatorFactory;
import com.avsystem.simulation.factories.FCFSElevatorFactory;
import com.avsystem.simulation.factories.SCANElevatorFactory;

public enum ElevatorFactoryVariant {
    DEFAULT, SCAN, FCFS;

    public ElevatorFactory get(Integer elevatorCount) {
        return switch (this) {
            case DEFAULT, SCAN -> new SCANElevatorFactory(elevatorCount);
            case FCFS -> new FCFSElevatorFactory(elevatorCount);
        };
    }
}
