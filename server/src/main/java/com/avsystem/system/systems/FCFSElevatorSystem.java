package com.avsystem.system.systems;

import com.avsystem.system.factories.FCFSElevatorFactory;
import com.avsystem.system.pickups.leastbusy.LeastBusyPickupStrategy;

public class FCFSElevatorSystem extends ElevatorSystem{
    public FCFSElevatorSystem(Integer elevatorCount) {
        super(
            new LeastBusyPickupStrategy(),
            new FCFSElevatorFactory(elevatorCount)
        );
    }
}
