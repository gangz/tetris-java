package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import com.github.gangz.emergentdesign.demo.tetris.ui.GameUI;

public class Game {
    private final GameUI ui;
    Block activeBlock;
    Block nextBlock;
    Block piledBlock;
    ShapeFactory shapeFactory = new ShapeFactory();
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
        nextBlock = new Block(shapeFactory.makeRandom());
    }

    private void makeEmtpyPiledBlock() {
        piledBlock = new Block(shapeFactory.makeEmpty());
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
