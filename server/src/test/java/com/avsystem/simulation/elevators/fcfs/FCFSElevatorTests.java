package com.avsystem.system.elevators.fcfs;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorRequest;
import com.avsystem.records.ElevatorStatus;
import com.avsystem.system.elevators.Elevator;
import com.avsystem.system.elevators.fcfs.FCFSElevator;
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
        assertEquals(elevator.status(), new ElevatorStatus(0, 3, 4, false));
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
        assertEquals(elevator.status(), new ElevatorStatus(0, -3, -4, false));
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
        assertEquals(elevator.status(), new ElevatorStatus(0, 4, 4, false));
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
