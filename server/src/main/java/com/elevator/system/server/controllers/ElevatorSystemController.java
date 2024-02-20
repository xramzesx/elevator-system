package com.elevator.system.server.controllers;

import com.elevator.system.records.ElevatorRequest;
import com.elevator.system.records.ElevatorSimulationConfig;
import com.elevator.system.records.ElevatorStatus;
import com.elevator.system.simulation.ElevatorSimulation;
import com.elevator.system.simulation.SimulationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ElevatorSystemController {

    @CrossOrigin
    @PostMapping("/simulation")
    public ResponseEntity<Map<String, String>> createSimulation(
        @RequestBody ElevatorSimulationConfig config
    ){

        if (config.elevatorCount() <= 0 || config.elevatorCount() > 16)
            return ResponseEntity.badRequest().build();

        if (config.highestFloor() < config.lowestFloor())
            return ResponseEntity.badRequest().build();

        if (config.delay() < 1000)
            return ResponseEntity.badRequest().build();

        String simulationId = SimulationManager.getInstance().create(config);

        Map<String, String> response = new HashMap<>();
        response.put("simulationId", simulationId);

        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @GetMapping("/simulations/config")
    public ResponseEntity<Map<String,ElevatorSimulationConfig>> getSimulationConfigs() {
        return ResponseEntity.ok(SimulationManager.getInstance().configurations());
    }

    @CrossOrigin
    @GetMapping("/simulation/{simulationId}/config")
    public ResponseEntity<ElevatorSimulationConfig> getSimulationConfig(
        @PathVariable String simulationId
    ) {
        ElevatorSimulation simulation = SimulationManager.getInstance().get(simulationId);

        if (simulation == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(simulation.configuration());
    }

    @CrossOrigin
    @GetMapping("/simulation/{simulationId}")
    public ResponseEntity<List<ElevatorStatus>> getSimulationStatus(@PathVariable String simulationId) {
        ElevatorSimulation simulation = SimulationManager.getInstance().get(simulationId);

        if (simulation == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(simulation.status());
    }

    @CrossOrigin
    @PostMapping("/simulation/{simulationId}/pickup")
    public ResponseEntity<ElevatorRequest> pickupElevator(
        @PathVariable String simulationId,
        @RequestBody ElevatorRequest request
    ) {
        ElevatorSimulation simulation = SimulationManager.getInstance().get(simulationId);
        if (simulation == null)
            return ResponseEntity.notFound().build();

        simulation.pickup(request);

        return ResponseEntity.ok(request);
    }

    @CrossOrigin
    @PostMapping("/simulation/{simulationId}/pickup-inside")
    public ResponseEntity<Map<String, String>> pickupInsideElevator(
            @PathVariable String simulationId,
            @RequestBody Map<String, String> request
    ) {
        if (!request.containsKey("elevatorId") || !request.containsKey("floor"))
            return ResponseEntity.badRequest().build();

        Integer elevatorId = Integer.valueOf(request.get("elevatorId"));
        Integer floor = Integer.valueOf(request.get("floor"));

        ElevatorSimulation simulation = SimulationManager.getInstance().get(simulationId);

        if (simulation == null)
            return ResponseEntity.notFound().build();

        simulation.pickupInside(floor, elevatorId);

        Map<String, String> result = new HashMap<>();
        result.put("Status", "OK");
        return ResponseEntity.ok(result);
    }
}
