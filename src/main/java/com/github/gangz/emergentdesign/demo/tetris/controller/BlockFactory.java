package com.github.gangz.emergentdesign.demo.tetris.controller;

public class BlockFactory {
    public Block makeEmptyBlock() {
        return  new Block();
    }

    public Block makeRandomBlock() {
        Block block = new Block();
        block.addCell(new Cell(0,0));
        return block;
    }
}
