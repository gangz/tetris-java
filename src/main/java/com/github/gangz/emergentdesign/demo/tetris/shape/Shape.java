package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Shape {
    private List<Cell> cells = new ArrayList<>();

    public int size() {
        return cells.size();
    }
    public Collection<Cell> getCells() {
        return cells;
    }

    public abstract void rotate();
}
