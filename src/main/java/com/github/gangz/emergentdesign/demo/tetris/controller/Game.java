package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import com.github.gangz.emergentdesign.demo.tetris.ui.GameUI;

public class Game {
    private static final int HORIZONAL_SIZE = 8;
    private static final int VERTICAL_SIZE = 16;
    private final GameUI ui;
    Block activeBlock;
    Block nextBlock;
    Block piledBlock;
    Block wall;
    ShapeFactory shapeFactory = new ShapeFactory();

    public Game(GameUI gameUI) {
        this.ui = gameUI;
        ui.setController(this);
        initGame();
    }

    private void initGame() {
        makeEmtpyPiledBlock();
        makeNextBlock();
        makeWall();
        dataChanged();
    }

    private void makeWall() {
        ShapeFactory factory = new ShapeFactory();
        Block bottom = new Block(0, VERTICAL_SIZE,factory.makeHorzionalBar(HORIZONAL_SIZE));
        Block left = new Block(-1, 0,factory.makeVerticalBar(VERTICAL_SIZE));
        Block right = new Block(HORIZONAL_SIZE, 0,factory.makeVerticalBar(VERTICAL_SIZE));
        wall = bottom;
        wall.join(left);
        wall.join(right);
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
        piledBlock = new Block(0,VERTICAL_SIZE-1,shapeFactory.makeVerticalBar(1));
    }

    public Block getActiveBlock() {
        return activeBlock;
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public Block getPiledBlock() {
        return piledBlock;
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
        return CollisionDetector.isCollision(activeBlock,piledBlock, Direction.DOWN) ||
                CollisionDetector.isCollision(activeBlock,wall, Direction.DOWN);
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
