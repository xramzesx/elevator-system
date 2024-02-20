package com.elevator.system.records;

import com.elevator.system.enums.ElevatorDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ElevatorRequestTests {
    @Test
    void equalsTest() {
        /// GIVEN ///
        ElevatorRequest request1 = new ElevatorRequest(0, ElevatorDirection.UP);
        ElevatorRequest request2 = new ElevatorRequest(0, ElevatorDirection.UP);
        ElevatorRequest request3 = new ElevatorRequest(1, ElevatorDirection.UP);
        ElevatorRequest request4 = new ElevatorRequest(1, ElevatorDirection.DOWN);

        /// THEN ///
        assertEquals(request1, request2);
        assertNotEquals(request1, request3);
        assertNotEquals(request1, request4);
        assertNotEquals(request3, request4);
    }

    @Test
    void hashCodeTest() {
        /// GIVEN ///
        ElevatorRequest request1 = new ElevatorRequest(0, ElevatorDirection.UP);
        ElevatorRequest request2 = new ElevatorRequest(0, ElevatorDirection.UP);
        ElevatorRequest request3 = new ElevatorRequest(1, ElevatorDirection.UP);
        ElevatorRequest request4 = new ElevatorRequest(1, ElevatorDirection.DOWN);

        /// THEN ///
        assertEquals(request1.hashCode(), request2.hashCode());
        assertNotEquals(request1.hashCode(), request3.hashCode());
        assertNotEquals(request1.hashCode(), request4.hashCode());
        assertNotEquals(request3.hashCode(), request4.hashCode());

    }
}
