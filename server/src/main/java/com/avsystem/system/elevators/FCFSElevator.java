package com.avsystem.system.elevators;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This
 */
public class FCFSElevator extends Elevator{

    private final Queue<ElevatorRequest> requests = new LinkedList<>();
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
}
