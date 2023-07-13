package com.avsystem.enums;

import com.avsystem.system.pickups.*;
import com.avsystem.system.pickups.bestcost.BalancedBestCostPickupStrategy;
import com.avsystem.system.pickups.bestcost.BestCostPickupStrategy;
import com.avsystem.system.pickups.bestcost.RandomizedBestCostPickupStrategy;
import com.avsystem.system.pickups.leastbusy.LeastBusyPickupStrategy;

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
