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
