package com.avsystem.records;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorStatusTests {
    @Test
    void hashCodeTest() {
        /// GIVEN ///
        ElevatorStatus status1 = new ElevatorStatus(0, 1, 2, false);
        ElevatorStatus status2 = new ElevatorStatus(0, 1, 2, false);
        ElevatorStatus status3 = new ElevatorStatus(1, 1, 2, false);

        /// THEN ///
        assertEquals(status1.hashCode(), status2.hashCode());
        assertNotEquals(status1.hashCode(), status3.hashCode());
    }

    @Test
    void equalsTest() {
        /// GIVEN ///
        ElevatorStatus status1 = new ElevatorStatus(0, 1, 2, false);
        ElevatorStatus status2 = new ElevatorStatus(0, 1, 2, false);
        ElevatorStatus status3 = new ElevatorStatus(1, 1, 2, false);

        /// THEN ///
        assertEquals(status1, status2);
        assertNotEquals(status1, status3);
    }
}
