package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RotatableShape extends  Shape{
    int index = 0;
    int[][][] data = null;
    public RotatableShape(int[][][]data){
        index = 0;
        this.data = data;
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
