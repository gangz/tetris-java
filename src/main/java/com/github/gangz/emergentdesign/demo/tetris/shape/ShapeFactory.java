package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {
    List<Shape> shapes = new ArrayList<>();
    public ShapeFactory(){
        shapes.add(makeI());
        shapes.add(makeZ());
        shapes.add(makeS());
        shapes.add(makeO());
        shapes.add(makeL());
        shapes.add(makeJ());
        shapes.add(makeT());
    }

    public Shape makeRandom() {
        int index = (int) (Math.random()*shapes.size());
        return shapes.get(index);
    }

    public Shape makeI(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{0,2},{0,3}},
                        {{1,0},{1,0},{2,0},{3,0}}}
        );
    }
    public Shape makeO(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{1,0},{1,1}}}
        );
    }
    public Shape makeZ(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{1,1},{1,2}},
                        {{0,1},{1,0},{1,1},{2,0}},
                        {{0,0},{0,1},{1,1},{1,2}},
                        {{0,1},{1,0},{1,1},{2,0}}}
        );
    }
    public Shape makeS(){
        return new PredefinedShape(
                new int[][][]{{{0,1},{0,2},{1,0},{1,1}},
                        {{0,0},{1,0},{1,1},{2,1}},
                        {{0,1},{0,2},{1,0},{1,1}},
                        {{0,0},{1,0},{1,1},{2,1}}}
        );
    }
    public Shape makeL(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{0,2},{1,2}},
                        {{0,1},{1,1},{2,1},{2,0}},
                        {{0,0},{1,0},{1,1},{1,2}},
                        {{0,0},{0,1},{1,0},{2,0}}}
        );
    }
    public Shape makeJ(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{0,2},{1,0}},
                        {{0,0},{1,0},{2,0},{2,1}},
                        {{1,0},{1,1},{1,2},{0,2}},
                        {{0,0},{0,1},{1,1},{2,1}}}
        );
    }

    public Shape makeT(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{0,2},{1,1}},
                        {{0,0},{1,0},{2,0},{1,1}},
                        {{1,0},{1,1},{1,2},{0,1}},
                        {{0,1},{1,1},{2,1},{1,0}}}
        );
    }

    public Shape makeEmpty(){
        return new Shape();
    }

    public Shape makeHorzionalBar(int size) {
        Shape shape  = new Shape();
        for (int x=0;x<size;x++)
            shape.add(new Cell(x,0));
        return shape;
    }

    public Shape makeVerticalBar(int size) {
        Shape shape  = new Shape();
        for (int y=0;y<size;y++)
            shape.add(new Cell(0,y));
        return shape;
    }
}
