package com.avsystem.system.pickups.bestcost;

public class RandomizedBestCostPickupStrategy extends BestCostPickupStrategy {
    @Override
    protected Integer initialElevator() {
        return (int)(Math.random() * this.elevators.size());
    }
}
