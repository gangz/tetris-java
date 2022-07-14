package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.util.ArrayList;
import java.util.List;

public class PredefinedShape extends Shape{
    private final int[][][] data;
    int currentIndex = 0;
    List<List<Cell>> shapeList = new ArrayList<>();
    public PredefinedShape(int[][][]data){
        this.data = data;
        currentIndex = 0;
        for (int index=0;index<data.length;index++) {
            List<Cell> shape = new ArrayList<>();
            for (int i = 0; i < data[index].length; i++) {
                shape.add(new Cell(data[index][i][0], data[index][i][1]));
            }
            shapeList.add(shape);
        }
        super.shape = shapeList.get(currentIndex);
    }
    @Override
    public void rotate() {
        currentIndex = (currentIndex +1)% shapeList.size();
        super.shape= shapeList.get(currentIndex);
    }

    public PredefinedShape copy() {
        return new PredefinedShape(this.data);
    }
}
