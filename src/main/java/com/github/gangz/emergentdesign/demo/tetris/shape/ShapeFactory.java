package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {
    List<PredefinedShape> shapes = new ArrayList<>();

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
        return shapes.get(index).copy();
    }

    public PredefinedShape makeI(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{0,2},{0,3}},
                        {{0,0},{1,0},{2,0},{3,0}}}
        );
    }
    public PredefinedShape makeO(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{1,0},{1,1}}}
        );
    }
    public PredefinedShape makeZ(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{1,1},{1,2}},
                        {{0,1},{1,0},{1,1},{2,0}},
                        {{0,0},{0,1},{1,1},{1,2}},
                        {{0,1},{1,0},{1,1},{2,0}}}
        );
    }
    public PredefinedShape makeS(){
        return new PredefinedShape(
                new int[][][]{{{0,1},{0,2},{1,0},{1,1}},
                        {{0,0},{1,0},{1,1},{2,1}},
                        {{0,1},{0,2},{1,0},{1,1}},
                        {{0,0},{1,0},{1,1},{2,1}}}
        );
    }
    public PredefinedShape makeL(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{0,2},{1,2}},
                        {{0,1},{1,1},{2,1},{2,0}},
                        {{0,0},{1,0},{1,1},{1,2}},
                        {{0,0},{0,1},{1,0},{2,0}}}
        );
    }
    public PredefinedShape makeJ(){
        return new PredefinedShape(
                new int[][][]{{{0,0},{0,1},{0,2},{1,0}},
                        {{0,0},{1,0},{2,0},{2,1}},
                        {{1,0},{1,1},{1,2},{0,2}},
                        {{0,0},{0,1},{1,1},{2,1}}}
        );
    }

    public PredefinedShape makeT(){
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
