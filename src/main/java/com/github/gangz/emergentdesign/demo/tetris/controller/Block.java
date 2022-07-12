package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;

import java.util.Collection;
import java.util.stream.Collectors;

public class Block {
    private Shape shape;
    private int x;
    private int y;

    public Block(Shape shape) {
        this.shape = shape;
    }

    public Collection<Cell> getCells() {
        return shape.getCells().stream().map(c->(new Cell(c.x+x,c.y+y))).collect(Collectors.toList());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void rotate() {
        shape.rotate();
    }
}
