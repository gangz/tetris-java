package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.ui.GameUI;

public class Game {
    private final GameUI ui;
    Block activeBlock;
    Block nextBlock;
    Block piledBlock;
    public Game(GameUI gameUI) {
        this.ui = gameUI;
        ui.setController(this);
        initGame();
    }

    private void initGame() {
        makeEmtpyPiledBlock();
        makeNextBlock();
        ui.dataChanged();
    }

    private void makeNextBlock() {
        BlockFactory factory = new BlockFactory();
        nextBlock = factory.makeRandomBlock();
    }

    private void makeEmtpyPiledBlock() {
        BlockFactory factory = new BlockFactory();
        piledBlock = factory.makeEmptyBlock();
    }

    public void start() {
        this.activeBlock = nextBlock;
        makeNextBlock();
    }

    public Block getActivePiledBlock() {
        return activeBlock;
    }

    public Block getNextBlock() {
        return nextBlock;
    }
}
