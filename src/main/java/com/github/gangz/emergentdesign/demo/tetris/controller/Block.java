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
package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;

import java.util.*;
import java.util.stream.Collectors;

public class Block {
    private Shape shape;
    private int x =0 ;
    private int y =0 ;

    public Block(Shape shape) {
        this.shape = shape;
    }

    public Block(int x, int y, Shape shape) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    public List<Cell> getCells() {
        return shape.getCells().stream().map(c->(new Cell(c.x+x,c.y+y))).collect(Collectors.toList());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void rotate() {
        shape.rotate();
    }

    public void join(Block anotherBlock) {
        for (Cell cell:anotherBlock.getCells()){
            this.shape.add(new Cell(cell.getX()-x,
                    cell.getY()-y));
        }
    }

    public int size() {
        return shape.getCells().size();
    }

    public int eliminate(int rowWidth) {
        return shape.eliminate(rowWidth);
    }

    public int height() {
        return shape.height();
    }

    public Block deepClone(){
        return new Block(x,y,shape.deepClone());
    }
}
