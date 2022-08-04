package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Game;

import java.util.Observable;
import java.util.Observer;

public class AIPlayer implements Observer {
    private final Game controller;

    public AIPlayer(Game controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    public void pauseToogle() {
    }

    @Override
    public void update(Observable observable, Object event) {
        controller.moveRight();
        controller.moveRight();
        controller.turn();
    }
}
