/*
MIT License

Copyright (c) 2022 ZHANG Gang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import com.github.gangz.emergentdesign.demo.tetris.ui.GameUI;

import java.util.Observable;
import java.util.Timer;

public class Game extends Observable {
    public static final int HORIZONAL_SIZE = 10;
    public static final int VERTICAL_SIZE = 20;
    private final GameUI ui;
    Block activeBlock;
    Block nextBlock;
    Block piledBlock;
    Block wall;
    ShapeFactory shapeFactory = new ShapeFactory();
    private ScoreKeeper scoreKeeper = new ScoreKeeper();
    private CollisionDetector collisionDetector = new CollisionDetector();
    public Game(GameUI gameUI) {
        this.ui = gameUI;
        ui.setController(this);
        initGame();
    }

    private void initGame() {
        makeEmtpyPiledBlock();
        makeNextBlock();
        makeWall();
        createActiveBlock();
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

    public Block getWall() { return wall;}

    public void moveDown() {
        if (activeBlock==null) {
            return;
        }
        if (isFallenBottom()){
            piledBlock.join(activeBlock);
            int rows = piledBlock.eliminate(HORIZONAL_SIZE);
            scoreKeeper.count(rows);
            createActiveBlock();
            checkGameOver();
        }else{
            activeBlock.moveDown();
        }
        dataChanged();
    }

    private void createActiveBlock() {
        this.activeBlock = nextBlock;
        makeNextBlock();
        this.setChanged();
        notifyObservers(new BlockCreatedEvent());
    }


    private void checkGameOver() {
        if (piledBlock.height()>=VERTICAL_SIZE)
            notifyGameOver();
    }

    private void notifyGameOver() {
        ui.notifyGameOver();
        this.initGame();
    }

    synchronized public void moveLeft() {
        if (activeBlock==null)
            return;
        if (isReachLeft())
            return;
        activeBlock.moveLeft();
        dataChanged();
    }

    synchronized public void moveRight() {
        if (activeBlock==null)
            return;
        if (isReachRight())
            return;

        activeBlock.moveRight();
        dataChanged();
    }

    synchronized public void turn() {
        if (activeBlock==null)
            return;
        activeBlock.rotate();
        dataChanged();
    }

    private boolean isFallenBottom() {
        return collisionDetector.isCollision(activeBlock,piledBlock, Direction.DOWN) ||
                collisionDetector.isCollision(activeBlock,wall, Direction.DOWN);
    }

    private boolean isReachLeft() {
        return collisionDetector.isCollision(activeBlock,piledBlock, Direction.LEFT) ||
                collisionDetector.isCollision(activeBlock,wall, Direction.LEFT);
    }

    private boolean isReachRight() {
        return collisionDetector.isCollision(activeBlock,piledBlock, Direction.RIGHT) ||
                collisionDetector.isCollision(activeBlock,wall, Direction.RIGHT);
    }

    public Integer getScore() {
        return scoreKeeper.score();
    }

    public void pauseToogle() {
        if (isPaused()) {
            resume();
        } else {
            pause();
        }
    }

    boolean paused = true;
    private Timer timer;

    private void pause() {
        paused = true;
        timer.cancel();
    }

    private void resume() {
        paused=false;
        timer = new Timer();
        timer.schedule(new BlockDownTask(this), 0,50);
    }
    private boolean isPaused() {
        return paused;
    }
}
