package com.github.gangz.emergentdesign.demo.tetris.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Block {
    List<Cell> cells = new ArrayList<>();
    private int x;
    private int y;

    public int size() {
        return cells.size();
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public Collection<Cell> getCells() {
        return cells.stream().map(c->(new Cell(c.x+x,c.y+y))).collect(Collectors.toList());
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
