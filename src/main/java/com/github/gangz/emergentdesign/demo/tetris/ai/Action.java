package com.github.gangz.emergentdesign.demo.tetris.ai;

public class Action {
    public int moveDistance = 0;
    public int turnTimes = 0;

    public Action(int moveDistance, int turnTimes) {
        this.moveDistance = moveDistance;
        this.turnTimes = turnTimes;
    }
}
