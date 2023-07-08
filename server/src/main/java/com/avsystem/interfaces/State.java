package com.avsystem.interfaces;

public interface State {
    State enter();
    State step();
}
