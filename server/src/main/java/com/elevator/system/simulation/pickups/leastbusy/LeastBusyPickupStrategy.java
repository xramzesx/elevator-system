package com.avsystem.simulation.pickups.leastbusy;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.simulation.elevators.Elevator;
import com.avsystem.simulation.pickups.ElevatorPickupStrategy;

public class LeastBusyPickupStrategy extends ElevatorPickupStrategy {
    @Override
    public void pickup(Integer floor, ElevatorDirection direction) {
        if (this.elevators.size() == 0)
            return;
        if (this.requests().contains(new ElevatorRequest(floor, direction)))
            return;

        Elevator bestElevator = this.elevators.get(0);

        for (Elevator elevator: this.elevators) {
            if (bestElevator.remaining() > elevator.remaining()) {
                bestElevator = elevator;
            }
        }

        bestElevator.pickup(floor, direction);
    }
}
