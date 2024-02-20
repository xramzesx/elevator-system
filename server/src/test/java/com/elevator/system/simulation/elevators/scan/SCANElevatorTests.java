package com.elevator.system.simulation.elevators.scan;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.interfaces.State;
import com.elevator.system.records.ElevatorStatus;
import com.elevator.system.simulation.elevators.scan.states.main.DownSCANState;
import com.elevator.system.simulation.elevators.scan.states.main.IdleSCANState;
import com.elevator.system.simulation.elevators.scan.states.main.UpSCANState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SCANElevatorTests {
    @Test
    void simpleMoveUpwardTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);

        /// WHEN ///
        ///pickup inside elevator to 10th
        elevator.pickup(10);

        /// go to 1st floor
        elevator.step();

        /// THEN ///
        assertTrue(elevator.getState() instanceof UpSCANState);
    }

    @Test
    void simpleMoveDownwardTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);

        /// WHEN ///
        /// pickup inside elevator to -10th
        elevator.pickup(-10);

        /// go to -first floor
        elevator.step();

        /// THEN ///
        assertTrue(elevator.getState() instanceof DownSCANState);
    }

    @Test
    void simpleRouteTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new IdleSCANState(elevator);
        State upState, downState;
        Integer firstCheck, secondCheck;

        int firstFloor = 10;
        int secondFloor = 1;

        /// WHEN ///
        elevator.pickup(firstFloor);
        elevator.pickup(secondFloor, ElevatorDirection.DOWN);

        for (int i = 0; i < firstFloor; i++ ) {
            state = state.step();
        }

        firstCheck = elevator.getCurrentFloor();
        upState = state;

        for (int i = 0; i < firstFloor - secondFloor; i++) {
            state = state.step();
        }

        secondCheck = elevator.getCurrentFloor();
        downState = state;

        state = state.step();

        /// THEN ///
        assertEquals(firstCheck, firstFloor);
        assertEquals(secondCheck, secondFloor);

        assertTrue(upState instanceof UpSCANState);
        assertTrue(downState instanceof DownSCANState);
        assertTrue(state instanceof IdleSCANState);
    }

    @Test
    void simplePostponeRouteTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State upState, downState, postponeDownState, postponeUpState;
        ElevatorStatus upStatus, downStatus, postponeDownStatus, postponeUpStatus;
        Integer firstCheck, secondCheck;

        int firstFloor = 10;
        int secondFloor = 1;

        /// WHEN ///
        elevator.pickup(firstFloor);
        elevator.pickup(secondFloor, ElevatorDirection.DOWN);

        elevator.step();

        /// go to 10th floor and pickup every floors
        for (int i = 0; i < firstFloor - 1; i++ ) {
            elevator.pickup(i, ElevatorDirection.UP);
            elevator.step();
        }

        upStatus = elevator.status();
        firstCheck = elevator.getCurrentFloor();
        upState = elevator.getState();

        /// go to 1st floor
        for (int i = 0; i < firstFloor - secondFloor; i++) {
            elevator.step();
        }

        downStatus = elevator.status();
        secondCheck = elevator.getCurrentFloor();
        downState = elevator.getState();

        /// Move to ground floor
        elevator.step();
        postponeDownState = elevator.getState();
        postponeDownStatus = elevator.status();

        /// Move to 1st postponed request
        elevator.step();
        postponeUpState = elevator.getState();
        postponeUpStatus = elevator.status();

        /// Process remaining requests
        for (int i = 0; i < firstFloor - 2; i++ ) {
            elevator.step();
        }

        /// THEN ///
        assertEquals(new ElevatorStatus(0, 10, 10, true), upStatus);
        assertEquals(new ElevatorStatus(0, 1, 1, true), downStatus);
        assertEquals(new ElevatorStatus(0, 0, 0, true), postponeDownStatus);
        assertEquals(new ElevatorStatus(0, 1, 1, true), postponeUpStatus);

        assertEquals(firstCheck, firstFloor);
        assertEquals(secondCheck, secondFloor);

        assertTrue(upState instanceof UpSCANState);
        assertTrue(downState instanceof DownSCANState);
        assertTrue(postponeDownState instanceof DownSCANState);
        assertTrue(postponeUpState instanceof UpSCANState);
        assertTrue(elevator.getState() instanceof IdleSCANState);
    }


    @Test
    void simpleBehaviorTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);

        /// WHEN ///
        elevator.pickup(10);

        /// go to 2nd floor
        elevator.step();
        elevator.step();

        /// pickup at 2nd floor upward
        elevator.pickup(2, ElevatorDirection.UP);

        /// go to 6th floor
        elevator.step();
        elevator.step();
        elevator.step();
        elevator.step();

        /// pickup at 6th floor downward
        elevator.pickup(6,ElevatorDirection.DOWN);

        /// THEN ///
        assertTrue(elevator.getState() instanceof UpSCANState);
    }
}
