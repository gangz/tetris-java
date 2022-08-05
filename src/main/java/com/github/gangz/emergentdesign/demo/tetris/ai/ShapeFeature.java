package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;
import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;

import java.util.Arrays;
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

    public int holes() {
        int holes = 0;
        for (int x=0;x<width;x++){
            int y=0;
            while(y<height && data[x][y]==0){
                y++;
            }
            for (;y<height;y++){
                if (data[x][y]==0)
                    holes++;
            }
        }
        return holes;
    }

    public int bumpiness() {
        int [] heights = new int[width+2]; //with wall
        heights[0]=height;
        heights[width+1]=height;
        for (int x=0;x<width;x++){
            int y=0;
            while(y<height && data[x][y]==0){
                y++;
            }
            heights[x+1] = height-y;
        }
        int value = 0;
        for (int x=1;x<width+2;x++)
            value += Math.abs(heights[x]-heights[x-1]);
        return value;
    }
}
