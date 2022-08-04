package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.*;

import java.util.Observable;
import java.util.Observer;

public class AIPlayer implements Observer {
    private final Game controller;
    private int moveDistance = 0;
    private int turnTimes = 0;
    private boolean isActive = false;

    public AIPlayer(Game controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    public void pauseToogle() {
        isActive = !isActive;
    }

    @Override
    public void update(Observable observable, Object event) {
        if (!isActive) return;
        if (event instanceof BlockCreatedEvent)
            newBlockCreated();
    }

    private void newBlockCreated() {
        compute();
        for (int i=0;i<moveDistance;i++)
            controller.moveRight();
        for (int j=0;j<turnTimes;j++)
            controller.turn();
    }

    private void compute() {
        this.moveDistance = 2;
        this.turnTimes = 1;
        Block activeBlock = controller.getActiveBlock().deepClone();
        Block piledBlock = controller.getPiledBlock().deepClone();
        while(!CollisionDetector.isCollision(activeBlock,piledBlock, Direction.DOWN)){
            activeBlock.moveDown();
        }
    }
}
