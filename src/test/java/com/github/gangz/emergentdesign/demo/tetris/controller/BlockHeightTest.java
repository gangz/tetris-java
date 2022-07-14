package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.NullUI;
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockHeightTest
{
    @Test
    public void calc_block_height()
    {
        Block block = new Block(new ShapeFactory().makeO());
        assertEquals(2,block.height());
    }
}
