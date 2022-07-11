package com.github.gangz.emergentdesign.demo.tetris.controller;

public class BlockFactory {
    public Block makeEmptyBlock() {
        return  new Block();
    }

    public Block makeRandomBlock() {
        return makeVerticalBar();
    }

    public Block makeVerticalBar() {
        Block block = new Block();
        for (int y=0;y<4;y++) {
            block.addCell(new Cell(0,y));
        }
        return block;
    }
}
