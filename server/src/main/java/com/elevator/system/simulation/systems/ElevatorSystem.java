package com.elevator.system.simulation.systems;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.records.ElevatorRequest;
import com.elevator.system.records.ElevatorStatus;
import com.elevator.system.simulation.elevators.Elevator;
import com.elevator.system.simulation.factories.ElevatorFactory;
import com.elevator.system.simulation.pickups.ElevatorPickupStrategy;

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

    /**
     * Pickup elevator from inside
     * @param floor      destination floor
     * @param elevatorId specific elevator id
     */
    public void pickupInside(Integer floor, Integer elevatorId) {
        synchronized (mutex) {
            this.strategy.pickupInside(floor, elevatorId);
        }
    }

    /**
     * Pickup elevator from outside (using ElevatorRequest)
     * @param request elevator request
     */
    public void pickup(ElevatorRequest request) {
        this.pickup(request.floor(), request.direction());
    }

    /**
     * Pickup elevator from outside
     * @param floor
     * @param direction
     */
    public void pickup(Integer floor, ElevatorDirection direction) {
        synchronized (mutex) {
            this.strategy.pickup(floor, direction);
        }
    }

    /**
     * Change specific elevator state
     * @param elevatorId
     * @param startFloor
     * @param finalFloor
     */
    public void update(Integer elevatorId, Integer startFloor, Integer finalFloor ) {
        synchronized (mutex) {
            this.strategy.update(elevatorId, startFloor, finalFloor);
        }
    };

    /**
     * Next simulation step
     */
    public void step() {
        synchronized (mutex) {
            this.strategy.step();
        }
    }

    /**
     * Rebuild elevator system
     */
    public void reset() {
        synchronized (mutex) {
            this.elevators = this.factory.build();
            this.strategy.setContext(this.elevators);
        }
    }

    /**
     * Get elevator system status
     * @return ElevatorStatus records
     */
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
