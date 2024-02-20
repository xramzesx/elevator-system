package com.elevator.system.interfaces;

public interface State {
    State enter();
    State step();
}
