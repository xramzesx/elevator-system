package com.avsystem.system.elevators.scan.states.queue;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.interfaces.State;
import com.avsystem.system.elevators.scan.SCANElevator;
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
}
