package com.elevator.system.simulation.systems;

import com.elevator.system.simulation.factories.FCFSElevatorFactory;
import com.elevator.system.simulation.pickups.leastbusy.LeastBusyPickupStrategy;

public class FCFSElevatorSystem extends ElevatorSystem{
    public FCFSElevatorSystem(Integer elevatorCount) {
        super(
            new LeastBusyPickupStrategy(),
            new FCFSElevatorFactory(elevatorCount)
        );
    }
}
