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
        dataChanged();
    }

    public void start() {
        this.activeBlock = nextBlock;
        makeNextBlock();
        dataChanged();
    }

    private void dataChanged() {
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

    public Block getActiveBlock() {
        return activeBlock;
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public void moveActiveBlockDown() {
        if (activeBlock==null) {
            return;
        }
        activeBlock.moveDown();
        dataChanged();
    }

    public void moveActiveBlockLeft() {
        if (activeBlock==null) {
            return;
        }
        activeBlock.moveLeft();
        dataChanged();
    }

    public void moveActiveBlockRight() {
        if (activeBlock==null) {
            return;
        }
        activeBlock.moveRight();
        dataChanged();
    }
}
