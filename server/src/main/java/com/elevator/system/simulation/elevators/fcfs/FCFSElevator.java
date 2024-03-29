package com.elevator.system.simulation.elevators.fcfs;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.records.ElevatorRequest;
import com.elevator.system.simulation.elevators.Elevator;

import java.util.*;

public class FCFSElevator extends Elevator {

    private final Deque<ElevatorRequest> requests = new LinkedList<>();
    private ElevatorRequest currentRequest = null;

    public FCFSElevator(Integer elevatorId) {
        super(elevatorId);
    }

    private Boolean isDoorsOpen = false;

    @Override
    public void step() {
        this.isDoorsOpen = false;

        /// EMPTY QUEUE ///
        if (this.currentRequest == null && this.requests.size() == 0)
            return;

        if (this.currentRequest == null) {
            this.currentRequest = requests.poll();
            this.destinationFloor = this.currentRequest.floor();
        }

        ElevatorDirection currentDirection = ElevatorDirection.get(
            this.currentFloor,
            this.currentRequest.floor()
        );

        /// CHECK IF IDLE ///

        if (currentDirection.equals(ElevatorDirection.IDLE)) {
            this.isDoorsOpen = true;
            currentRequest = null;
            return;
        }

        /// MOVE ELEVATOR ///
        this.currentFloor += currentDirection.getValue();

        if (this.currentFloor.equals(this.currentRequest.floor())) {
            this.isDoorsOpen = true;
            this.currentRequest = null;
        }
    }

    @Override
    public void pickup(Integer floor, ElevatorDirection direction) {
        requests.add(new ElevatorRequest(floor, direction));
    }

    @Override
    public void update(Integer startFloor, Integer finalFloor) {
        if (currentRequest != null) {
            requests.addFirst(currentRequest);
            currentRequest = null;
        }

        this.currentFloor = startFloor;
        this.currentRequest = new ElevatorRequest(finalFloor, ElevatorDirection.get(startFloor, finalFloor));
    }

    @Override
    public Set<ElevatorRequest> requests() {
        Set<ElevatorRequest> requests = new HashSet<>(this.requests);

        if (this.currentRequest != null)
            requests.add(this.currentRequest);

        return requests;
    }

    @Override
    public Integer remaining() {
        return (this.currentRequest == null ? 0 : 1) + this.requests.size();
    }

    @Override
    public Boolean isOpen() {
        return this.isDoorsOpen;
    }
}
