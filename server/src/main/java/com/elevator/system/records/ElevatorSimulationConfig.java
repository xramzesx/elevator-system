package com.elevator.system.records;

import com.elevator.system.enums.ElevatorFactoryVariant;
import com.elevator.system.enums.ElevatorPickupStrategyVariant;
import com.elevator.system.enums.ElevatorSystemVariant;

public record ElevatorSimulationConfig(
    ElevatorSystemVariant systemVariant,
    ElevatorFactoryVariant factoryVariant,
    ElevatorPickupStrategyVariant strategyVariant,
    Integer highestFloor,
    Integer lowestFloor,
    Integer elevatorCount,
    Integer delay
) {
}
