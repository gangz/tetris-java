package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeI;
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeZ;

import java.util.ArrayList;
import java.util.List;

public class BlockFactory {
    List<Shape> shapes = new ArrayList<>();
    public BlockFactory(){
        shapes.add(new ShapeI());
        shapes.add(new ShapeZ());
    }
    public Block makeEmptyBlock() {
        return  new Block();
    }

    public Block makeRandomBlock() {
        int index = (int) (Math.random()*shapes.size());
        return new Block(shapes.get(index));
    }


    public Block makeBlockI() {
        return new Block(new ShapeI());
    }

    public Block makeBlockZ() {
        return new Block(new ShapeZ());
    }
}
