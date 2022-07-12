package com.github.gangz.emergentdesign.demo.tetris.controller;

public enum Direction {
    DOWN(0),
    LEFT(1),
    RIGHT(2);
    public final int value;
    private Direction(int value){
        this.value = value;
    }
}
