package com.elevator.system.simulation.pickups.leastbusy;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.records.ElevatorRequest;
import com.elevator.system.simulation.elevators.Elevator;
import com.elevator.system.simulation.pickups.ElevatorPickupStrategy;

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
