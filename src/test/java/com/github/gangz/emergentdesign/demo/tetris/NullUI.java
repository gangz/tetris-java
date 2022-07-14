package com.github.gangz.emergentdesign.demo.tetris;

import com.github.gangz.emergentdesign.demo.tetris.controller.Game;
import com.github.gangz.emergentdesign.demo.tetris.ui.GameUI;

public class NullUI implements GameUI {
    @Override
    public void setController(Game game) {
    }

    @Override
    public void dataChanged() {

    }

    @Override
    public void notifyGameOver() {

    }
}
