package com.github.gangz.emergentdesign.demo.tetris.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShapeZ extends Shape{
    int index = 0;
    int[][][] data = new int[][][]{
            {{0,0},{0,1},{1,1},{1,2}},
            {{0,1},{1,0},{1,1},{2,0}},
            {{0,0},{0,1},{1,1},{1,2}},
            {{0,1},{1,0},{1,1},{2,0}}};

    public ShapeZ(){
        index = 0;
    }

    @Override
    public void rotate() {
        index = (index +1)%data.length;
    }

    @Override
    public Collection<Cell> getCells() {
        List<Cell> cells =new ArrayList<>();
        for (int i=0;i<4;i++) {
            cells.add(new Cell(data[index][i][0],data[index][i][1]));
        }
        return cells;
    }
}
