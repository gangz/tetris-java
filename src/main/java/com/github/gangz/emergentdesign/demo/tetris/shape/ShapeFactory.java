/*
MIT License

Copyright (c) 2022 ZHANG Gang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
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
        return shapes.get(index).deepClone();
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
