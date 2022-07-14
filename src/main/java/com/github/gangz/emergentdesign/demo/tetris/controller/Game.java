package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import com.github.gangz.emergentdesign.demo.tetris.ui.GameUI;

import java.util.Timer;
import java.util.stream.IntStream;

public class Game {
    private static final int HORIZONAL_SIZE = 8;
    private static final int VERTICAL_SIZE = 16;
    private final GameUI ui;
    Block activeBlock;
    Block nextBlock;
    Block piledBlock;
    Block wall;
    ShapeFactory shapeFactory = new ShapeFactory();
    private ScoreKeeper scoreKeeper = new ScoreKeeper();

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
    }


    private void checkGameOver() {
        if (piledBlock.height()>=VERTICAL_SIZE)
            notifyGameOver();
    }

    private void notifyGameOver() {
        ui.notifyGameOver();
        this.initGame();
    }

    public void moveLeft() {
        if (activeBlock==null)
            return;
        if (isReachLeft())
            return;
        activeBlock.moveLeft();
        dataChanged();
    }

    public void moveRight() {
        if (activeBlock==null)
            return;
        if (isReachRight())
            return;

        activeBlock.moveRight();
        dataChanged();
    }

    public void turn() {
        if (activeBlock==null)
            return;
        activeBlock.rotate();
        dataChanged();
    }

    private boolean isFallenBottom() {
        return CollisionDetector.isCollision(activeBlock,piledBlock, Direction.DOWN) ||
                CollisionDetector.isCollision(activeBlock,wall, Direction.DOWN);
    }

    private boolean isReachLeft() {
        return CollisionDetector.isCollision(activeBlock,piledBlock, Direction.LEFT) ||
                CollisionDetector.isCollision(activeBlock,wall, Direction.LEFT);
    }

    private boolean isReachRight() {
        return CollisionDetector.isCollision(activeBlock,piledBlock, Direction.RIGHT) ||
                CollisionDetector.isCollision(activeBlock,wall, Direction.RIGHT);
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
        timer.schedule(new BlockDownTask(this), 0,300);
    }
    private boolean isPaused() {
        return paused;
    }
}
