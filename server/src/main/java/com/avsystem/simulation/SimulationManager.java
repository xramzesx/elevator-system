package com.avsystem.simulation;

import com.avsystem.records.ElevatorSimulationConfig;

import java.util.*;

public class SimulationManager {

    //// SINGLETONE SETUP ////
    private static SimulationManager instance = null;

    public static SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }
        return instance;
    }

    private SimulationManager(){}

    //// MANAGER SETUP ////

    private Map<String, ElevatorSimulation> simulationMap = new HashMap<>();

    public synchronized String create(ElevatorSimulationConfig config) {
        String simulationId = UUID.randomUUID().toString();
        ElevatorSimulation simulation = new ElevatorSimulation(config, simulationId);

        this.simulationMap.put(simulationId, simulation);
        this.start(simulationId);

        return simulationId;
    }

    public synchronized void remove(String simulationId) {
        this.stop(simulationId);
        this.simulationMap.remove(simulationId);
    }

    public synchronized void stop(String simulationId) {
        ElevatorSimulation simulation = this.simulationMap.get(simulationId);

        if (simulation == null)
            return;

        simulation.stop();
    }

    public synchronized void start(String simulationId) {
        ElevatorSimulation simulation = this.simulationMap.get(simulationId);

        if (simulation == null || simulation.getRunningState())
            return;

        Thread simulationThread = new Thread(simulation);
        simulationThread.start();
    }

    public synchronized ElevatorSimulation get(String simulationId) {
        return simulationMap.get(simulationId);
    }

    public synchronized Map<String,ElevatorSimulationConfig> configurations(){
        Map<String, ElevatorSimulationConfig> configurations = new HashMap<>();

        this.simulationMap.forEach((simulationId, simulation) -> {
            configurations.put(simulationId, simulation.configuration());
        });

        return configurations;
    }
}
