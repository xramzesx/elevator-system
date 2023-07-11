package com.avsystem.system;

import com.avsystem.enums.ElevatorDirection;
import com.avsystem.records.ElevatorStatus;
import com.avsystem.system.systems.ElevatorSystem;
import com.avsystem.system.systems.SCANElevatorSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorSystemTests {
    @Test
    void simpleSCANElevatorSystemTest () {
        /// GIVEN ///
        ElevatorSystem elevatorSystem = new SCANElevatorSystem(3);

        /// WHEN ///
        elevatorSystem.pickup(2, ElevatorDirection.UP);
        elevatorSystem.step();
        elevatorSystem.pickup(-3, ElevatorDirection.UP);
        elevatorSystem.pickup(0, ElevatorDirection.UP);
        elevatorSystem.step();

        /// THEN ///
        assertEquals(new ElevatorStatus(0,2,2,true),elevatorSystem.status().get(0));
        assertEquals(new ElevatorStatus(1,-1,-3,false),elevatorSystem.status().get(1));
        assertEquals(new ElevatorStatus(2,0,0,true),elevatorSystem.status().get(2));
    }
}
