package com.avsystem.simulation.systems;

import com.avsystem.simulation.factories.SCANElevatorFactory;
import com.avsystem.simulation.pickups.bestcost.BestCostPickupStrategy;

public class SCANElevatorSystem extends ElevatorSystem {
    public SCANElevatorSystem(Integer elevatorCount) {
        super(
            new BestCostPickupStrategy(),
            new SCANElevatorFactory(elevatorCount)
        );
    }
}
