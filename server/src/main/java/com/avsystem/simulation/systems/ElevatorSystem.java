package com.avsystem.simulation.systems;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.records.ElevatorStatus;
import com.avsystem.simulation.elevators.Elevator;
import com.avsystem.simulation.factories.ElevatorFactory;
import com.avsystem.simulation.pickups.ElevatorPickupStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private final Object mutex = new Object();

    protected ElevatorPickupStrategy strategy;
    protected ElevatorFactory factory;
    protected List<Elevator> elevators;

    public ElevatorSystem(ElevatorPickupStrategy strategy, ElevatorFactory factory) {
        this.strategy = strategy;
        this.factory = factory;
        this.reset();
    }

    public void pickup(ElevatorRequest request) {
        this.pickup(request.floor(), request.direction());
    }

    public void pickup(Integer floor, ElevatorDirection direction) {
        synchronized (mutex) {
            this.strategy.pickup(floor, direction);
        }
    }
    public void update(Integer elevatorId, Integer startFloor, Integer finalFloor ) {
        synchronized (mutex) {
            this.strategy.update(elevatorId, startFloor, finalFloor);
        }
    };
    public void step() {
        synchronized (mutex) {
            this.strategy.step();
        }
    }

    public void reset() {
        synchronized (mutex) {
            this.elevators = this.factory.build();
            this.strategy.setContext(this.elevators);
        }
    }

    public List<ElevatorStatus> status() {
        List<ElevatorStatus> result = new ArrayList<>();

        synchronized (mutex) {
            for (Elevator elevator : this.elevators) {
                result.add(elevator.status());
            }
        }
        return result;
    }
}
