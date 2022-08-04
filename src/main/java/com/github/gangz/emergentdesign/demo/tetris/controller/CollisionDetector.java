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

import java.util.ArrayList;
import java.util.List;

public class CollisionDetector {
    public boolean isCollision(Block moveBlock, Block stillBlock, Direction direction) {
        int[][] offsets = {{0,1},{-1,0},{1,0}};
        int index = direction.value-Direction.DOWN.value;
        int[] offset = offsets[index];

        List<Cell> moveBlockCells = moveBlock.getCells();
        List<Cell> stillBlockCells = stillBlock.getCells();
        for (int i=0;i<moveBlockCells.size();i++){
            Cell cellMove = moveBlockCells.get(i);
            for (int j=0; j<stillBlockCells.size();j++){
                Cell cellStill = stillBlockCells.get(j);
                if (cellMove.x+offset[0] == cellStill.x &&
                        cellMove.y+offset[1] == cellStill.y)
                    return true;
            }
        }
        return false;
    }
}
