package com.avsystem.system.pickups;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.system.elevators.Elevator;

import java.util.Optional;

public class BestCostPickupStrategy extends ElevatorPickupStrategy {
    @Override
    public void pickup(Integer floor, ElevatorDirection direction) {
        if (elevators.size() == 0)
            return;
        Elevator bestElevator = elevators.get(0);
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

            /// get elevator in motion ///
            if (direction.equals(elevatorDirection) || bestElevator.requests() > elevator.requests()) {
                bestElevator = elevator;
            }
        }

        bestElevator.pickup(floor, direction);
        System.out.println(floor + " " + direction + " " + bestElevator.status().elevatorId());

    }

    @Override
    public void update(Integer elevatorId, Integer startFloor, Integer finalFloor) {
        Elevator elevator = this
            .elevators
            .stream()
            .filter(
                el -> el.status().elevatorId().equals(elevatorId)
            ).findFirst()
            .orElse(null);

        if (elevator == null)
            return;

        elevator.update(startFloor, finalFloor);
    }
}
