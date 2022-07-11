package com.github.gangz.emergentdesign.demo.tetris.controller;

import java.util.ArrayList;
import java.util.List;

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

    public List getCells() {
        return cells;
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
}
