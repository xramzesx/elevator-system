package com.avsystem.system.pickups.bestcost;


public class BalancedBestCostPickupStrategy extends BestCostPickupStrategy {
    private Integer currentElevator = 0;
    @Override
    protected Integer initialElevator() {
        return (currentElevator++) % this.elevators.size();
    }
}
