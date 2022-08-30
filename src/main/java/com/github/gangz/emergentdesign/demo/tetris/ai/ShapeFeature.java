package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.util.Comparator;
import java.util.List;

public class ShapeFeature {
    int[][] data = new int[0][0];

    int height = 0;
    int width = 0;

    public ShapeFeature(List<Cell> shape, int width){
        if (shape.size()>0)
            convertToMatrix(shape,width);
    }

    private void convertToMatrix(List<Cell> cells, int width) {
        int minY = cells.stream().min(Comparator.comparingInt(Cell::getY)).get().getY();
        int maxY = cells.stream().max(Comparator.comparingInt(Cell::getY)).get().getY();
        height = maxY-minY+1;
        this.width = width;
        data = new int[width][height];
        cells.forEach(cell->{cell.y -=minY;});
        cells.forEach(cell->{
            data[cell.x][cell.y]=1;
        });
    }

    public int columnTransitions(){
        int value = 0;
        for (int x=0;x<width;x++){
            for (int y=1;y<height;y++){
                if (data[x][y]!=data[x][y-1])
                    value++;
            }
        }
        return value;
    }

    public int holeCover(){
        int value = 0;
        for (int x=0;x<width;x++){
            for (int y=height-1;y>=0;y--){
                if (data[x][y]==0){
                    value +=countAllCellsAbove(x,y);
                }
            }
        }
        return value;
    }

    private int countAllCellsAbove(int x, int y) {
        int value = 0;
        for (int i=0;i<y;i++){
            if (data[x][i]==1)
                value++;
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
        int [] heights = new int[width];
        for (int x=0;x<width;x++){
            int y=0;
            while(y<height && data[x][y]==0){
                y++;
            }
            heights[x] = height-y;
        }
        int value = 0;
        for (int x=1;x<width;x++)
            value += Math.abs(heights[x]-heights[x-1]);
        return value;
    }

    public int well() {
        int value = 0;
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                if (data[x][y]==0) {
                    if (x == 0) {
                        if (data[x + 1][y] == 1) {
                            value++;
                        }
                    } else if (x == width - 1) {
                        if (data[x - 1][y] == 1) {
                            value++;
                        }
                    } else {
                        if (data[x - 1][y] == 1 && data[x + 1][y] == 1) {
                            value++;
                        }
                    }
                }
            }
        }
        return value;
    }
}
