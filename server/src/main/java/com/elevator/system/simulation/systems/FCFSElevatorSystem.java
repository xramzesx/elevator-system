package com.avsystem.simulation.systems;

import com.avsystem.simulation.factories.FCFSElevatorFactory;
import com.avsystem.simulation.pickups.leastbusy.LeastBusyPickupStrategy;

public class FCFSElevatorSystem extends ElevatorSystem{
    public FCFSElevatorSystem(Integer elevatorCount) {
        super(
            new LeastBusyPickupStrategy(),
            new FCFSElevatorFactory(elevatorCount)
        );
    }
}
