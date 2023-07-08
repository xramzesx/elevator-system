package com.avsystem.system.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InitSCANStateTests {
    @Test
    void endTest() {
        SCANElevator elevator = new SCANElevator(0);
        State state = new InitSCANState(elevator, ElevatorDirection.UP);

        assertTrue( state.step() instanceof EndSCANState);
    }

    @Test
    void backTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new InitSCANState(elevator, ElevatorDirection.UP);
        Integer initialUpwards, initialDownwards;

        /// WHEN ///
        elevator.pickup(-1, ElevatorDirection.UP);

        initialUpwards = elevator.getUpwardRequests().size();
        initialDownwards = elevator.getDownwardRequests().size();

        /// reach back and end state
        state = state.step();

        /// THEN ///
        assertEquals(1, initialUpwards);
        assertEquals(0, initialDownwards);

        assertTrue(state instanceof EndSCANState);
        assertEquals(0, elevator.getUpwardRequests().size());
        assertEquals(1, elevator.getDownwardRequests().size());
    }
}
