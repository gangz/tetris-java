package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Block;
import com.github.gangz.emergentdesign.demo.tetris.controller.CollisionDetector;
import com.github.gangz.emergentdesign.demo.tetris.controller.Direction;

public class BlockJoiner {
    public Block joinBlock(Block activeBlock, Block piledBlock, Integer move, Integer turn) {
        Block testActiveBlock = activeBlock.deepClone();
        Block testPiledBlock = piledBlock.deepClone();
        for (int i=0;i<move;i++)
            testActiveBlock.moveRight();
        for (int j=0;j<turn;j++)
            testActiveBlock.rotate();
        while(!new CollisionDetector().isCollision(testActiveBlock,testPiledBlock, Direction.DOWN)){
            testActiveBlock.moveDown();
        }
        testPiledBlock.join(testActiveBlock);
        return testPiledBlock;
    }
}
