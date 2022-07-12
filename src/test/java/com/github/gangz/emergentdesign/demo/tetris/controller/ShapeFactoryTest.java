package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ShapeFactoryTest
{
    @Test
    public void produce_block_I(){
        ShapeFactory factory = new ShapeFactory();
        assertArrayEquals(new int[][]{{0,0},{0,1},{0,2},{0,3}},
                convertToArray(factory.makeI().getCells()));
    }

    @Test
    public void produce_block_Z(){
        ShapeFactory factory = new ShapeFactory();
        assertArrayEquals(new int[][]{{0,0},{0,1},{1,1},{1,2}},
                convertToArray(factory.makeZ().getCells()));
    }

    private Object[] convertToArray(Collection<Cell> cells) {
        List<int[]> result = cells.stream().map(c -> (new int[]{c.x, c.y})).collect(Collectors.toList());
        return result.toArray();
    }
}
