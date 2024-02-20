package com.elevator.system.simulation.systems;

import com.elevator.system.simulation.factories.SCANElevatorFactory;
import com.elevator.system.simulation.pickups.bestcost.BestCostPickupStrategy;

public class SCANElevatorSystem extends ElevatorSystem {
    public SCANElevatorSystem(Integer elevatorCount) {
        super(
            new BestCostPickupStrategy(),
            new SCANElevatorFactory(elevatorCount)
        );
    }
}
