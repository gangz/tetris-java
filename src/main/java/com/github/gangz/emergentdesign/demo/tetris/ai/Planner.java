package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Block;
import com.github.gangz.emergentdesign.demo.tetris.controller.CollisionDetector;
import com.github.gangz.emergentdesign.demo.tetris.controller.Direction;

public class Planner {
    private final int horizonalSize;
    private final Parameter parameter;

    public Planner(Parameter parameter,int horizonalSize) {
        this.horizonalSize = horizonalSize;
        this.parameter = parameter;
    }

    public Action computeAction(Block activeBlock, Block piledBlock) {
        Block testActiveBlock = activeBlock.deepClone();
        Block testPiledBlock = piledBlock.deepClone();
        while(!CollisionDetector.isCollision(testActiveBlock,testPiledBlock, Direction.DOWN)){
            testActiveBlock.moveDown();
        }
        return new Action(2,1);
    }
}
