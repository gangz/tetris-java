package com.github.gangz.emergentdesign.demo.tetris.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Shape {
    private List<Cell> cells = new ArrayList<>();

    public int size() {
        return cells.size();
    }

    public void add(Cell cell) {
        cells.add(cell);
    }

    public Collection<Cell> getCells() {
        return cells;
    }

    public abstract void rotate();
}
