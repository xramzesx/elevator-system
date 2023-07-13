package com.avsystem.enums;

import com.avsystem.simulation.pickups.*;
import com.avsystem.simulation.pickups.bestcost.BalancedBestCostPickupStrategy;
import com.avsystem.simulation.pickups.bestcost.BestCostPickupStrategy;
import com.avsystem.simulation.pickups.bestcost.RandomizedBestCostPickupStrategy;
import com.avsystem.simulation.pickups.leastbusy.LeastBusyPickupStrategy;

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
