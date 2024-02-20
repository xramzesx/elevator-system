package com.elevator.system.simulation.elevators.fcfs;

import com.elevator.system.enums.ElevatorDirection;
import com.elevator.system.records.ElevatorRequest;
import com.elevator.system.records.ElevatorStatus;
import com.elevator.system.simulation.elevators.Elevator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FCFSElevatorTests {
    @Test
    void pickupTest() {
        Elevator elevator = new FCFSElevator(0);
        List<ElevatorRequest> requests = new ArrayList<>();
    }

    @Test
    void stepForwardTest() {
        /// GIVEN ///
        Elevator elevator = new FCFSElevator(0);

        /// WHEN ///
        elevator.pickup(4, ElevatorDirection.DOWN);

        // go to 3rd floor
        elevator.step();
        elevator.step();
        elevator.step();

        /// THEN ///
        Assertions.assertEquals(elevator.status(), new ElevatorStatus(0, 3, 4, false));
    }

    @Test
    void stepBackwardTest() {
        /// GIVEN ///
        Elevator elevator = new FCFSElevator(0);

        /// WHEN ///
        elevator.pickup(-4, ElevatorDirection.DOWN);

        // go to -3rd floor
        elevator.step();
        elevator.step();
        elevator.step();

        /// THEN ///
        Assertions.assertEquals(elevator.status(), new ElevatorStatus(0, -3, -4, false));
    }

    @Test
    void stepLimitTest() {
        /// GIVEN ///
        Elevator elevator = new FCFSElevator(0);

        /// WHEN ///
        elevator.pickup(4, ElevatorDirection.DOWN);

        // go to 4rd floor
        elevator.step();
        elevator.step();
        elevator.step();
        elevator.step();

        /// skip some steps
        elevator.step();
        elevator.step();
        elevator.step();

        /// THEN ///
        Assertions.assertEquals(elevator.status(), new ElevatorStatus(0, 4, 4, false));
    }

    @Test
    void stepClosedTest() {
        /// GIVEN ///
        Elevator elevator = new FCFSElevator(0);

        /// WHEN ///
        elevator.pickup(4, ElevatorDirection.DOWN);

        // go to 3rd floor
        elevator.step();
        elevator.step();
        elevator.step();

        // pickup
        elevator.pickup(3, ElevatorDirection.DOWN);

        /// THEN ///
        assertFalse(elevator.isOpen());
    }

    @Test
    void stepOpenTest() {
        /// GIVEN ///
        Elevator elevator = new FCFSElevator(0);

        /// WHEN ///
        elevator.pickup(4, ElevatorDirection.DOWN);

        // go to 3rd floor
        elevator.step();
        elevator.step();
        elevator.step();

        // pickup
        elevator.pickup(3, ElevatorDirection.DOWN);

        /// go to 4th floor
        elevator.step();

        /// THEN ///
        assertTrue(elevator.isOpen());
    }
}
