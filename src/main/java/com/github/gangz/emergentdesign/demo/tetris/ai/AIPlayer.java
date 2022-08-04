package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.BlockCreatedEvent;
import com.github.gangz.emergentdesign.demo.tetris.controller.Game;

import java.util.Observable;
import java.util.Observer;

public class AIPlayer implements Observer {
    private final Game controller;
    private int moveDistance = 2;
    private int turnTimes = 1;

    public AIPlayer(Game controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    public void pauseToogle() {
    }

    @Override
    public void update(Observable observable, Object event) {
        if (event instanceof  BlockCreatedEvent)
            newBlockCreated();
    }

    private void newBlockCreated() {
        for (int i=0;i<moveDistance;i++)
            controller.moveRight();
        for (int j=0;j<turnTimes;j++)
            controller.turn();
    }
}
