package com.avsystem.system.elevators.fcfs;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.system.elevators.Elevator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class FCFSElevator extends Elevator {

    private final Deque<ElevatorRequest> requests = new LinkedList<>();
    private ElevatorRequest currentRequest = null;

    public FCFSElevator(Integer elevatorId) {
        super(elevatorId);
    }

    @Override
    public void step() {
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
            currentRequest = null;
            return;
        }

        /// MOVE ELEVATOR ///
        this.currentFloor += currentDirection.getValue();

        if (this.currentFloor.equals(this.currentRequest.floor())) {
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
    public Integer requests() {
        return (this.currentRequest == null ? 0 : 1) + this.requests.size();
    }
}
