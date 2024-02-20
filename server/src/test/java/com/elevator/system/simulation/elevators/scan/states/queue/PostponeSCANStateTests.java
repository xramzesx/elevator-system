package com.avsystem.simulation.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.simulation.elevators.scan.SCANElevator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostponeSCANStateTests {
    @Test
    void singlePostponeUpwardTest(){
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new MoveSCANState(elevator, ElevatorDirection.UP);

        /// WHEN ///
        elevator.pickup(-1, ElevatorDirection.UP);
        elevator.pickup(10);

        /// go to 1st floor and postpone
        state = state.step();

        /// THEN ///
        assertEquals(1,elevator.getPostponedRequests().size());
        assertEquals(1,elevator.getCurrentFloor());
    }

    @Test
    void singlePostponeDownwardTest(){
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new MoveSCANState(elevator, ElevatorDirection.DOWN);

        /// WHEN ///
        elevator.pickup(1, ElevatorDirection.DOWN);
        elevator.pickup(-10);

        /// go to 1st floor and postpone
        state = state.step();

        /// THEN ///
        assertEquals(1,elevator.getPostponedRequests().size());
        assertEquals(-1,elevator.getCurrentFloor());
    }

    @Test
    void multiPostponeUpwardTest() {
        /// GIVEN ///
        SCANElevator elevator = new SCANElevator(0);
        State state = new MoveSCANState(elevator, ElevatorDirection.UP);
        int floors = 10;

        /// WHEN ///
        elevator.pickup(floors);

        /// go to 1st floor and postpone
        state = state.step();

        for (int i = 0; i < floors - 1; i++ ) {
            elevator.pickup(i, ElevatorDirection.UP);
            state = state.step();
        }


        /// THEN ///
        assertEquals(9, elevator.getPostponedRequests().size());
        assertEquals(10, elevator.getCurrentFloor());

    }
}
