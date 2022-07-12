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
        createActiveBlock();
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

    public void moveDown() {
        if (activeBlock==null) {
            return;
        }
        if (isFallenBottom()){
            piledBlock.join(activeBlock);
            piledBlock.eliminate(8);
            fallDownIfPiledBlockHanged();
            checkGameOver();
            createActiveBlock();
        }else{
            activeBlock.moveDown();
        }
        dataChanged();
    }

    private void createActiveBlock() {
        this.activeBlock = nextBlock;
        makeNextBlock();
    }


    private void checkGameOver() {
    }

    private void fallDownIfPiledBlockHanged() {
    }

    private boolean isFallenBottom() {
        return CollisionDetector.isCollision(activeBlock,piledBlock, Direction.DOWN);
    }

    public void moveLeft() {
        if (activeBlock==null) {
            return;
        }
        activeBlock.moveLeft();
        dataChanged();
    }

    public void moveRight() {
        if (activeBlock==null) {
            return;
        }
        activeBlock.moveRight();
        dataChanged();
    }

    public void turn() {
        if (activeBlock==null) {
            return;
        }
        activeBlock.rotate();
        dataChanged();
    }
}
