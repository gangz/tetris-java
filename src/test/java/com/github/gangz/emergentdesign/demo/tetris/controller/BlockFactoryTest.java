package com.github.gangz.emergentdesign.demo.tetris.controller;

import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test of Game Controller.
 */
public class BlockFactoryTest
{
    @Test
    public void produce_block_I(){
        BlockFactory blockFactory = new BlockFactory();
        Block bar = blockFactory.makeBlockI();
        assertArrayEquals(new int[][]{{0,0},{0,1},{0,2},{0,3}},
                convertToArray(bar.getCells()));
    }

    @Test
    public void produce_block_Z(){
        BlockFactory blockFactory = new BlockFactory();
        Block bar = blockFactory.makeBlockZ();
        assertArrayEquals(new int[][]{{0,0},{0,1},{1,1},{1,2}},
                convertToArray(bar.getCells()));
    }

    private Object[] convertToArray(Collection<Cell> cells) {
        List<int[]> result = cells.stream().map(c -> (new int[]{c.x, c.y})).collect(Collectors.toList());
        return result.toArray();
    }
}
