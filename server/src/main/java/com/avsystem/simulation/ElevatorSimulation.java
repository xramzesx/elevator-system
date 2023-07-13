package com.avsystem.simulation;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.records.ElevatorSimulationConfig;
import com.avsystem.records.ElevatorStatus;
import com.avsystem.simulation.systems.ElevatorSystem;
import com.avsystem.simulation.systems.FCFSElevatorSystem;
import com.avsystem.simulation.systems.SCANElevatorSystem;

import java.util.List;

public class ElevatorSimulation implements Runnable{
    protected ElevatorSimulationConfig config;
    protected ElevatorSystem system;
    protected String simulationId;
    protected Boolean isRunning = false;

    public ElevatorSimulation(ElevatorSimulationConfig config, String simulationId) {
        this.config = config;
        this.simulationId = simulationId;

        switch (config.systemVariant()) {
            case SCAN -> {
                this.system = new SCANElevatorSystem(this.config.elevatorCount());
            }
            case FCFS -> {
                this.system = new FCFSElevatorSystem(this.config.elevatorCount());
            }
            case CUSTOM -> {
                this.system = new ElevatorSystem(
                    this.config.strategyVariant().get(),
                    this.config.factoryVariant().get(this.config.elevatorCount())
                );
            }
        }
    }

    protected synchronized Boolean getRunningState () {
        return this.isRunning;
    }

    protected synchronized void setRunningState(Boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void step() {
        try {
            this.system.step();
            Thread.sleep(this.config.delay());
        } catch (InterruptedException exception) {
            System.out.println("An error occur: " + exception.getMessage());
        }
    }

    @Override
    public void run() {
        this.setRunningState(true);
        while (this.getRunningState()) {
            this.step();
        }
    }

    public synchronized void stop() {
        this.setRunningState(false);
    }

    public void pickup(ElevatorRequest request) {
        this.pickup(request.floor(), request.direction());
    }

    public void pickup(Integer floor, ElevatorDirection direction) {
        if (this.config.highestFloor() < floor)
            return;
        if (this.config.lowestFloor() > floor)
            return;

        if (this.config.highestFloor().equals(floor) && ElevatorDirection.UP.equals(direction))
            return;
        if (this.config.lowestFloor().equals(floor) && ElevatorDirection.DOWN.equals(direction))
            return;

        this.system.pickup(floor, direction);
    }

    public List<ElevatorStatus> status() {
        return this.system.status();
    }

    public void update(Integer elevatorId, Integer currentFloor, Integer finalFloor) {
        this.system.update(elevatorId, currentFloor, finalFloor);
    }

    public ElevatorSimulationConfig configuration() {
        return config;
    }
}
