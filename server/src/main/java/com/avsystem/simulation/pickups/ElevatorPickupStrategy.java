package com.avsystem.simulation.pickups;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.simulation.elevators.Elevator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ElevatorPickupStrategy {
    protected List<Elevator> elevators;

    public void pickupInside(Integer floor, Integer elevatorId) {
        if (this.elevators.size() <= elevatorId || elevatorId < 0)
            return;

        this.elevators.get(elevatorId).pickup(floor);
    }

    public abstract void pickup (Integer floor, ElevatorDirection direction);
    public void setContext(List<Elevator> elevators) {
        this.elevators = elevators;
    }
    public void step() {
        System.out.println("[step]: begin");
        for (Elevator elevator: this.elevators) {
            elevator.step();
        }
        System.out.println("[step]: finish");
    }

    public Set<ElevatorRequest> requests() {
        Set<ElevatorRequest> requests = new HashSet<>();

        this.elevators.forEach(
            elevator -> requests.addAll(elevator.requests())
        );

        return requests;
    }

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

    };

}
