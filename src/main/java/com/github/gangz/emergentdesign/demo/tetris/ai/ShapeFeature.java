package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;
import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ShapeFeature {
    int[][] data;
    int width;
    int height;
    public ShapeFeature(List<Cell> shape){
        convertToMatrix(shape);
    }

    private void convertToMatrix(List<Cell> cells) {
        int minX = cells.stream().min(Comparator.comparingInt(Cell::getX)).get().getX();
        int minY = cells.stream().min(Comparator.comparingInt(Cell::getY)).get().getY();
        int maxX = cells.stream().max(Comparator.comparingInt(Cell::getX)).get().getX();
        int maxY = cells.stream().max(Comparator.comparingInt(Cell::getY)).get().getY();
        width = maxX-minX+1;
        height = maxY-minY+1;
        data = new int[width][height];
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
