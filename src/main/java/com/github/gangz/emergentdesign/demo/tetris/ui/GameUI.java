package com.github.gangz.emergentdesign.demo.tetris.ui;

import com.github.gangz.emergentdesign.demo.tetris.controller.Game;

public interface GameUI {
    void setController(Game game);
    void dataChanged();
}
