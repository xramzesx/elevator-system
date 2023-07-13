package com.avsystem.system.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveSCANStateTests {

    @Test
    void singleMoveForwardTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new MoveSCANState(elevator, ElevatorDirection.UP);

        /// WHEN ///
        elevator.pickup(4);

        /// go to 1st floor
        state = state.step();

        /// THEN ///
        assertTrue(state instanceof MoveSCANState);
        assertEquals(1, elevator.getCurrentFloor());
    }

    @Test
    void moveForwardTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new MoveSCANState(elevator, ElevatorDirection.UP);
        State openState, moveState;

        /// WHEN ///
        elevator.pickup(4);

        /// go to 3rd floor
        state = state.step();
        state = state.step();
        state = state.step();

        moveState = state;

        /// go to 4th floor and open door
        state = state.step();

        openState = state;

        /// reach end
        state = state.step();

        /// THEN ///
        assertTrue(state instanceof EndSCANState);
        assertTrue(openState instanceof OpenSCANState);
        assertTrue(moveState instanceof MoveSCANState);

        assertEquals(4, elevator.getCurrentFloor());
    }

    @Test
    void moveForwardWithPickups() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new MoveSCANState(elevator, ElevatorDirection.UP);
        State ignoreDownMoveState, openState, ignoreLowerFloorMoveState;
        Integer firstCheck, secondCheck, thirdCheck, fourthCheck;

        /// WHEN ///
        /// pickup inside elevator to 10th floor
        elevator.pickup(10);

        /// go to 3rd floor
        state = state.step();
        state = state.step();
        state = state.step();

        elevator.pickup(3, ElevatorDirection.DOWN);
        firstCheck = elevator.getCurrentFloor();

        /// go to 4th floor upward
        state = state.step();

        ignoreDownMoveState = state;
        secondCheck = elevator.getCurrentFloor();

        /// pickup at 4th floor upward
        elevator.pickup(5, ElevatorDirection.UP);

        /// go to 5th floor
        state = state.step();

        openState = state;
        thirdCheck = elevator.getCurrentFloor();

        /// go to 7th floor
        state = state.step();
        state = state.step();

        /// pickup at 4th floor upward
        elevator.pickup(4, ElevatorDirection.UP);

        state = state.step();

        ignoreLowerFloorMoveState = state;
        fourthCheck = elevator.getCurrentFloor();

        /// go to last floor
        state = state.step();
        state = state.step();

        /// THEN ///

        assertTrue(state instanceof OpenSCANState);
        assertEquals(10, elevator.getCurrentFloor());

        assertEquals(3, firstCheck);

        assertTrue(ignoreDownMoveState instanceof MoveSCANState);
        assertEquals(4, secondCheck);

        assertTrue(openState instanceof OpenSCANState);
        assertEquals(5, thirdCheck);

        assertTrue(ignoreLowerFloorMoveState instanceof MoveSCANState);
        assertEquals(8, fourthCheck);

    }
}
