package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.*;

import java.util.Observable;
import java.util.Observer;

public class AIPlayer implements Observer {
    private final Game controller;
    private boolean isActive = false;
    private Planner planner;
    public AIPlayer(Parameter parameter, Game controller) {
        this.controller = controller;
        controller.addObserver(this);
        planner = new Planner(parameter, Game.HORIZONAL_SIZE,
                controller.getWall());
    }

    public void pauseToogle() {
        isActive = !isActive;
    }

    public void start() {
            isActive=true;
    }

    @Override
    public void update(Observable observable, Object event) {
        if (!isActive) return;
        if (event instanceof BlockCreatedEvent)
            newBlockCreated();
    }

    private void newBlockCreated() {
        Action action = planner.computeAction(controller.getActiveBlock(), controller.getPiledBlock());
        for (int i=0;i<action.moveDistance;i++)
            controller.moveRight();
        for (int j=0;j<action.turnTimes;j++)
            controller.turn();
    }
}
