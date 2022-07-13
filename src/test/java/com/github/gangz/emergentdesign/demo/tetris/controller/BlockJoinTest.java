package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.NullUI;
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockJoinTest
{
    @Test
    public void should_join_2_blocks()
    {
        ShapeFactory factory = new ShapeFactory();
        Block b_1 = new Block(factory.makeI());
        Block b_2 = new Block(factory.makeT());

        b_1.join(b_2);
        assertEquals(8,b_1.size());
    }
}


