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
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ShapeRotateTest
{
    @Test
    public void shape_I_rotate_test()
    {
        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.makeI();
        shape.rotate();
        assertArrayEquals(new int[][]{{0,0},{1,0},{2,0},{3,0}},
                convertToArray(shape.getCells()));
    }

    @Test
    public void shape_Z_rotate_test()
    {
        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.makeZ();
        shape.rotate();
        assertArrayEquals(new int[][]{{0,1},{1,0},{1,1},{2,0}},
                convertToArray(shape.getCells()));
    }

    private Object[] convertToArray(Collection<Cell> cells) {
        List<int[]> result = cells.stream().map(c -> (new int[]{c.x, c.y})).collect(Collectors.toList());
        return result.toArray();
    }
}
