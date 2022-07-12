package com.github.gangz.emergentdesign.demo.tetris.controller;

public class BlockFactory {
    public Block makeEmptyBlock() {
        return  new Block();
    }

    public Block makeRandomBlock() {
        return makeBlockI();
    }

    public Block makeBlockI() {
        Block block = new Block();
        for (int y=0;y<4;y++) {
            block.addCell(new Cell(0,y));
        }
        return block;
    }

    public Block makeBlockZ() {
        Block block = new Block();
        int[][] data = new int[][]{{0,0},{0,1},{1,1},{1,2}};
        for (int y=0;y<4;y++) {
            block.addCell(new Cell(data[y][0],data[y][1]));
        }
        return block;
    }
}
