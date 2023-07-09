package com.avsystem.system.elevators.scan;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.records.ElevatorStatus;
import com.avsystem.system.elevators.Elevator;
import com.avsystem.system.elevators.scan.states.SCANState;
import com.avsystem.system.elevators.scan.states.main.IdleSCANState;

import java.util.*;

public class SCANElevator extends Elevator {

    /**
     * Set for upward requests. TreeSet here works like PriorityQueues without duplicates <br/>
     * {@link #upwardRequests.poll()} method return request with the lowest floor
     */
    private final Set<ElevatorRequest> upwardRequests = new TreeSet<>(
        Comparator.comparingInt(ElevatorRequest::floor)
    );

    /**
     * Set for downward requests. TreeSet here works like PriorityQueues without duplicates <br/>
     * {@link #downwardRequests.poll()} method return request with the highest floor
     */
    private final Set<ElevatorRequest> downwardRequests = new TreeSet<>(
        Comparator.comparingInt(ElevatorRequest::floor).reversed()
    );

    /**
     * Set for idle requests
     */
    private final Set<ElevatorRequest> idleRequests = new HashSet<>();

    /**
     * Queue for postponed request.
     */
    private final Queue<ElevatorRequest> postponedRequests = new LinkedList<>();


    private SCANState state;

    //// CONSTRUCTOR ////

    public SCANElevator(Integer elevatorId) {
        super(elevatorId);
        this.state = new IdleSCANState(this);
    }

    //// ELEVATOR CONTROLS ////

    @Override
    public void step() {
        this.state = (SCANState) state.step();
    }

    @Override
    public void pickup(Integer floor, ElevatorDirection direction) {
        System.out.println("[pickup][" + direction + "] floor: " + floor);

        switch (direction) {
            case UP -> {
                this.upwardRequests.add(new ElevatorRequest(floor, direction));
            }
            case DOWN -> {
                this.downwardRequests.add(new ElevatorRequest(floor, direction));
            }
            case IDLE -> {
                if (this.currentFloor.equals(floor)) {
                    this.idleRequests.add(new ElevatorRequest(floor, direction));
                }
            }
        }
    }

    //// INSTANCE ONLY ////

    public void move(ElevatorDirection direction) {
        this.currentFloor += direction.getValue();
    }

    //// GETTERS ////

    public Set<ElevatorRequest> getUpwardRequests() {
        return upwardRequests;
    }

    public Set<ElevatorRequest> getDownwardRequests() {
        return downwardRequests;
    }

    public Queue<ElevatorRequest> getPostponedRequests() {
        return postponedRequests;
    }

    public SCANState getState() {
        return state;
    }

    @Override
    public Boolean isOpen() {
        return this.state.isOpen();
    }

    @Override
    public ElevatorStatus status() {
        return new ElevatorStatus(
            this.elevatorId,
            this.state.current(),
            this.state.destination(),
            this.isOpen()
        );
    }
}
