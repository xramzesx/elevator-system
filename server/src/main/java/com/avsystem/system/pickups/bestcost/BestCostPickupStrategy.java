package com.avsystem.system.pickups.bestcost;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.system.elevators.Elevator;
import com.avsystem.system.pickups.ElevatorPickupStrategy;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BestCostPickupStrategy extends ElevatorPickupStrategy {

    protected Integer initialElevator() {
        return 0;
    }

    @Override
    public void pickup(Integer floor, ElevatorDirection direction) {
        if (elevators.size() == 0)
            return;
        if (this.requests().contains(new ElevatorRequest(floor, direction)))
            return;

        Elevator bestElevator = elevators.get(this.initialElevator());
        for (Elevator elevator: this.elevators) {
            ElevatorDirection elevatorDirection = elevator.status().getDirection();

            /// skip elevators going backward ///
            if (direction.reverse().equals((elevatorDirection)))
                continue;

            /// skip elevators with higher distance ///
            if (elevator.distance(floor) > bestElevator.distance(floor))
                continue;

            /// get elevator with lower distance ///
            if ( elevator.distance(floor) < bestElevator.distance(floor)) {
                bestElevator = elevator;
                continue;
            }

            /// get elevator in motion or least busy ///
            if (direction.equals(elevatorDirection) || bestElevator.remaining() > elevator.remaining()) {
                bestElevator = elevator;
            }
        }

        bestElevator.pickup(floor, direction);
        System.out.println(floor + " " + direction + " " + bestElevator.status().elevatorId());

    }

    @Override
    public void update(Integer elevatorId, Integer startFloor, Integer finalFloor) {
    }
}
