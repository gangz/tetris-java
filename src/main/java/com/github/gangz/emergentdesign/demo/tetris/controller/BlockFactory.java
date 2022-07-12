package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeI;
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeZ;

public class BlockFactory {
    public Block makeEmptyBlock() {
        return  new Block();
    }

    public Block makeRandomBlock() {
        return makeBlockI();
    }


    public Block makeBlockI() {
        return new Block(new ShapeI());
    }

    public Block makeBlockZ() {
        return new Block(new ShapeZ());
    }
}
