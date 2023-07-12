package com.avsystem.system.pickups;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.system.elevators.Elevator;

import java.util.List;

public abstract class ElevatorPickupStrategy {
    protected List<Elevator> elevators;

    public abstract void pickup (Integer floor, ElevatorDirection direction);
    public abstract void update(Integer elevatorId, Integer startFloor, Integer finalFloor);
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
}
