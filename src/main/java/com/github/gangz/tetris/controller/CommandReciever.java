package com.github.gangz.tetris.controller;

public interface CommandReciever {
    void start();
    void pause();
    void tick();
    void moveRight();
    void moveLeft();
    void moveDown();
    void rotate();
}
