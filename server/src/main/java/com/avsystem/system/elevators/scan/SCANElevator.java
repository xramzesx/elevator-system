package com.avsystem.system.elevators.scan;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.records.ElevatorStatus;
import com.avsystem.system.elevators.Elevator;
import com.avsystem.system.elevators.scan.states.SCANState;
import com.avsystem.system.elevators.scan.states.main.DownSCANState;
import com.avsystem.system.elevators.scan.states.main.IdleSCANState;
import com.avsystem.system.elevators.scan.states.main.UpSCANState;

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
    public void pickup(Integer floor) {
        if (!this.state.current().equals(floor)) {
            super.pickup(floor);
            return;
        }

        if (this.state instanceof IdleSCANState) {
            this.pickup(floor, ElevatorDirection.UP);
        } else {
            this.pickup(floor, this.state.direction());
        }

    }

    @Override
    public void pickup(Integer floor, ElevatorDirection direction) {
        System.out.println("["+ this.elevatorId +"][pickup|" + direction + "] floor: " + floor);

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

    @Override
    public void update(Integer startFloor, Integer finalFloor) {
        ElevatorDirection direction = ElevatorDirection.get(startFloor, finalFloor);
        this.currentFloor = startFloor;

        this.state = switch (direction) {
            case UP -> new UpSCANState(this);
            case DOWN -> new DownSCANState(this);
            case IDLE -> new IdleSCANState(this);
        };
    }

    @Override
    public Integer requests() {
        return
            this.upwardRequests.size() +
            this.downwardRequests.size() +
            this.postponedRequests.size() +
            this.idleRequests.size();
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

    public Set<ElevatorRequest> getIdleRequests() {
        return idleRequests;
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
