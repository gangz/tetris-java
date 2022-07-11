package com.github.gangz.emergentdesign.demo.tetris.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Block {
    private Shape shape;
    private int x;
    private int y;

    public Block(){
        shape = new Shape();
    }
    public int size() {
        return shape.size();
    }

    public void addCell(Cell cell) {
        shape.add(cell);
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
}
