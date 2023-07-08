package com.avsystem.enums;

public enum ElevatorDirection {
    DOWN(-1), IDLE(0), UP(1);

    private final Integer value;
    private ElevatorDirection(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public ElevatorDirection reverse() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case IDLE -> IDLE;
        };
    }

    public static ElevatorDirection get(Integer startFloor, Integer finalFloor) {
        if (startFloor < finalFloor)
            return UP;
        if (startFloor > finalFloor)
            return DOWN;
        return IDLE;
    }
}
