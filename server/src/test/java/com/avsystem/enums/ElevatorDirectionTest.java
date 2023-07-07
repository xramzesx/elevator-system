package com.avsystem.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorDirectionTest {
    @Test
    void getValueTest() {
        assertEquals(-1, ElevatorDirection.DOWN.getValue());
        assertEquals(0, ElevatorDirection.IDLE.getValue());
        assertEquals(1, ElevatorDirection.UP.getValue());
    }

    @Test
    void getUpDirectionTest() {
        assertEquals(ElevatorDirection.UP, ElevatorDirection.get(0,1));
        assertEquals(ElevatorDirection.UP, ElevatorDirection.get(-2,2));
        assertEquals(ElevatorDirection.UP, ElevatorDirection.get(2,20));
    }

    @Test
    void getDownDirectionTest() {
        assertEquals(ElevatorDirection.DOWN, ElevatorDirection.get(1,0));
        assertEquals(ElevatorDirection.DOWN, ElevatorDirection.get(2,-2));
        assertEquals(ElevatorDirection.DOWN, ElevatorDirection.get(20,2));
    }

    @Test
    void getIdleDirectionTest() {
        assertEquals(ElevatorDirection.IDLE, ElevatorDirection.get(0,0));
        assertEquals(ElevatorDirection.IDLE, ElevatorDirection.get(1,1));
        assertEquals(ElevatorDirection.IDLE, ElevatorDirection.get(20,20));
    }
}
