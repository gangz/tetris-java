package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Block {
    private Shape shape;
    private int x =0 ;
    private int y =0 ;

    public Block(Shape shape) {
        this.shape = shape;
    }

    public Block(int x, int y, Shape shape) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    public List<Cell> getCells() {
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

    public void join(Block anotherBlock) {
        for (Cell cell:anotherBlock.getCells()){
            this.shape.add(new Cell(cell.getX()-x,
                    cell.getY()-y));
        }
    }

    public int size() {
        return shape.getCells().size();
    }

    public int eliminate(int rowWidth) {
        return shape.eliminate(rowWidth);
    }
}
