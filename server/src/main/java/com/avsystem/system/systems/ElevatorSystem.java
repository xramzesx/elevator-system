package com.avsystem.system.systems;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorStatus;
import com.avsystem.system.elevators.Elevator;
import com.avsystem.system.factories.ElevatorFactory;
import com.avsystem.system.pickups.ElevatorPickupStrategy;

import java.util.ArrayList;
import java.util.List;

public abstract class ElevatorSystem {
    private final Object mutex = new Object();

    protected ElevatorPickupStrategy strategy;
    protected ElevatorFactory factory;
    protected List<Elevator> elevators;

    public ElevatorSystem(ElevatorPickupStrategy strategy, ElevatorFactory factory) {
        this.strategy = strategy;
        this.factory = factory;
        this.reset();
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
            System.out.println("[step]: begin");
            for (Elevator elevator: this.elevators) {
                elevator.step();
            }
            System.out.println("[step]: finish");
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
