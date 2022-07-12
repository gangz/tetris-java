package com.github.gangz.emergentdesign.demo.tetris.controller;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetector {
    public static boolean isCollision(Block moveBlock, Block stillBlock, Direction direction) {
        int[][] offset = {{0,-1},{-1,0},{1,0}};
        int index = direction.value-Direction.DOWN.value;
        List<Cell> moveBlockCells = moveBlock.getCells();
        List<Cell> stillBlockCells = stillBlock.getCells();
        for (int i=0;i<moveBlockCells.size();i++){
            Cell cellMove = moveBlockCells.get(i);
            for (int j=0; j<stillBlockCells.size();j++){
                Cell cellStill = stillBlockCells.get(j);
                if (cellMove.x+offset[index][0] == cellStill.x &&
                        cellMove.y+offset[index][1] == cellStill.y)
                    return true;
            }
        }
        return false;
    }
}
