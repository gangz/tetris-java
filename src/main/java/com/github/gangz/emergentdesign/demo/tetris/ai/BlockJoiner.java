package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Block;
import com.github.gangz.emergentdesign.demo.tetris.controller.CollisionDetector;
import com.github.gangz.emergentdesign.demo.tetris.controller.Direction;

public class BlockJoiner {
    private final Block wall;

    public BlockJoiner(Block wall) {
        this.wall = wall;
    }

    public Block joinBlock(Block activeBlock, Block piledBlock, Integer move, Integer turn) {
        Block testActiveBlock = activeBlock.deepClone();
        Block testPiledBlock = piledBlock.deepClone();
        for (int j=0;j<turn;j++)
            testActiveBlock.rotate();
        for (int i=0;i<move;i++) {
            if(new CollisionDetector().isCollision(testActiveBlock,wall,Direction.RIGHT)){
                return null;
            }
            testActiveBlock.moveRight();
        }
        while(!new CollisionDetector().isCollision(testActiveBlock,testPiledBlock, Direction.DOWN) &&
              ! new CollisionDetector().isCollision(testActiveBlock,wall,Direction.DOWN)){
            testActiveBlock.moveDown();
        }
        testPiledBlock.join(testActiveBlock);
        return testPiledBlock;
    }
}
