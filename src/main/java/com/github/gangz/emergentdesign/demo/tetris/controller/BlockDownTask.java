package com.github.gangz.emergentdesign.demo.tetris.controller;

import java.util.TimerTask;

public class BlockDownTask extends TimerTask {
    private Game game;

    public BlockDownTask(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        game.moveDown();
    }}
