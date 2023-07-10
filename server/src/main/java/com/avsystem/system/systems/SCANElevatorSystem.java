package com.avsystem.system.systems;

import com.avsystem.system.factories.SCANElevatorFactory;
import com.avsystem.system.pickups.BestCostPickupStrategy;

public class SCANElevatorSystem extends ElevatorSystem {
    public SCANElevatorSystem(Integer elevatorCount) {
        super(
            new BestCostPickupStrategy(),
            new SCANElevatorFactory(elevatorCount)
        );
    }
}
