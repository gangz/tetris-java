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

    @Override
    public PredefinedShape deepClone() {
        return new PredefinedShape(this.data);
    }
}
