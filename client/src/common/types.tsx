export enum ElevatorDirection {
    UP = "UP",
    DOWN = "DOWN",
    IDLE = "IDLE"
}

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
