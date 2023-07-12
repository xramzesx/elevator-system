//// ENUMS ////

export enum ElevatorSystemVariant {
    CUSTOM = "CUSTOM",
    SCAN = "SCAN"
}

export enum ElevatorFactoryVariant {
    DEFAULT = "DEFAULT",
    SCAN = "SCAN",
    FCFS = "FCFS"
}

export enum ElevatorPickupStrategy {
    DEFAULT = "DEFAULT",
    BCOST = "BCOST"
}


export enum ElevatorDirection {
    UP = "UP",
    DOWN = "DOWN",
    IDLE = "IDLE"
}

//// TYPES ////

export type ElevatorRequest = {
    floor: number,
    direction: ElevatorDirection
}

export type ElevatorStatus = {
    elevatorId: number,
    current: number,
    destination: number,
    isOpen: boolean
}

export type SimulationConfig = {
    systemVariant: ElevatorSystemVariant,
    factoryVariant: ElevatorFactoryVariant,
    strategyVariant: ElevatorPickupStrategy,
    highestFloor: number,
    lowestFloor: number,
    elevatorCount: number,
    delay: number,
}