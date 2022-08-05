package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;
import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ShapeFeature {
    private final Shape shape;
    int[][] data;
    int width;
    int height;
    public ShapeFeature(Shape shape){
        this.shape = shape;
        convertToMatrix(shape);
    }

    private void convertToMatrix(Shape shape) {
        width = shape.width();
        height = shape.height();
        data = new int[width][height];
        List<Cell> cells = shape.getCells();
        int minX = cells.stream().min(Comparator.comparingInt(Cell::getX)).get().getX();
        int minY = cells.stream().min(Comparator.comparingInt(Cell::getY)).get().getY();
        cells.forEach(cell->{cell.x -= minX; cell.y -=minY;});
        cells.forEach(cell->{data[cell.x][cell.y]=1;});
    }

    public int columnTransitions(){
        int value = 0;
        for (int x=1;x<width;x++){
            for (int y=0;y<height;y++){
                if (data[x][y]!=data[x-1][y])
                    value++;
            }
        }
        return value;
    }

    public int rowTransitions(){
        int value = 0;
        for (int x=0;x<width;x++){
            for (int y=1;y<height;y++){
                if (data[x][y]!=data[x][y-1])
                    value++;
            }
        }
        return value;
    }

}
