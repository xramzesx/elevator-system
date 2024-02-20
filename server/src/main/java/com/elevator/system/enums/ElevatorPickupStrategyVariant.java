package com.elevator.system.enums;

import com.elevator.system.simulation.pickups.ElevatorPickupStrategy;
import com.elevator.system.simulation.pickups.bestcost.BalancedBestCostPickupStrategy;
import com.elevator.system.simulation.pickups.bestcost.BestCostPickupStrategy;
import com.elevator.system.simulation.pickups.bestcost.RandomizedBestCostPickupStrategy;
import com.elevator.system.simulation.pickups.leastbusy.LeastBusyPickupStrategy;

public enum ElevatorPickupStrategyVariant {
    DEFAULT,

    /// BEST COST STRATEGIES ///
    BEST_COST,
    BALANCED_BEST_COST,
    RANDOMIZED_BEST_COST,

    /// LEAST BUSY STRATEGIES ///
    LEAST_BUSY;

    public ElevatorPickupStrategy get() {
        return switch (this) {
            case DEFAULT, BEST_COST -> new BestCostPickupStrategy();
            case BALANCED_BEST_COST -> new BalancedBestCostPickupStrategy();
            case RANDOMIZED_BEST_COST -> new RandomizedBestCostPickupStrategy();
            case LEAST_BUSY -> new LeastBusyPickupStrategy();
        };
    }
}
