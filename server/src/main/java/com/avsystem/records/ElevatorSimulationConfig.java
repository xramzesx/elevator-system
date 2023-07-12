package com.avsystem.records;

import com.avsystem.enums.ElevatorFactoryVariant;
import com.avsystem.enums.ElevatorPickupStrategyVariant;
import com.avsystem.enums.ElevatorSystemVariant;

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
