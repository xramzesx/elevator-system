package com.elevator.system.simulation.elevators.scan.states.main;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.interfaces.State;
import com.elevator.system.simulation.elevators.scan.SCANElevator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainSCANStateTests {
    @Test
    void simpleMoveUpwardTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new IdleSCANState(elevator);

        /// WHEN ///
        ///pickup inside elevator to 10th
        elevator.pickup(10);

        /// go to 1st floor
        state = state.step();

        /// THEN ///
        assertTrue(state instanceof UpSCANState);
    }

    @Test
    void simpleMoveDownwardTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new IdleSCANState(elevator);

        /// WHEN ///
        /// pickup inside elevator to -10th
        elevator.pickup(-10);

        /// go to -first floor
        state = state.step();

        /// THEN ///
        assertTrue(state instanceof DownSCANState);
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
        State state = new IdleSCANState(elevator);
        State upState, downState, postponeDownState, postponeUpState;
        Integer firstCheck, secondCheck;

        int firstFloor = 10;
        int secondFloor = 1;

        /// WHEN ///
        elevator.pickup(firstFloor);
        elevator.pickup(secondFloor, ElevatorDirection.DOWN);

        state = state.step();

        /// go to 10th floor and pickup every floors
        for (int i = 0; i < firstFloor - 1; i++ ) {
            elevator.pickup(i, ElevatorDirection.UP);
            state = state.step();
        }

        firstCheck = elevator.getCurrentFloor();
        upState = state;

        /// go to 1st floor
        for (int i = 0; i < firstFloor - secondFloor; i++) {
            state = state.step();
        }

        secondCheck = elevator.getCurrentFloor();
        downState = state;

        /// Move to ground floor
        state = state.step();
        postponeDownState = state;

        /// Move to 1st postponed request
        state = state.step();
        postponeUpState = state;

        /// Process remaining requests
        for (int i = 0; i < firstFloor - 2; i++ ) {
            state = state.step();
        }

        /// THEN ///
        assertEquals(firstCheck, firstFloor);
        assertEquals(secondCheck, secondFloor);

        assertTrue(upState instanceof UpSCANState);
        assertTrue(downState instanceof DownSCANState);
        assertTrue(postponeDownState instanceof DownSCANState);
        assertTrue(postponeUpState instanceof UpSCANState);
        assertTrue(state instanceof IdleSCANState);
    }


    @Test
    void simpleBehaviorTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new IdleSCANState(elevator);

        /// WHEN ///
        elevator.pickup(10);

        /// go to 2nd floor
        state = state.step();
        state = state.step();

        /// pickup at 2nd floor upward
        elevator.pickup(2, ElevatorDirection.UP);

        /// go to 6th floor
        state = state.step();
        state = state.step();
        state = state.step();
        state = state.step();

        /// pickup at 6th floor downward
        elevator.pickup(6,ElevatorDirection.DOWN);


        /// THEN ///
        assertTrue(state instanceof UpSCANState);
    }
}
