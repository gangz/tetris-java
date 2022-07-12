package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.NullUI;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test of Game Controller.
 */
public class BlockRotateTest
{
    @Test
    public void shape_I_rotate_test()
    {
        BlockFactory factory = new BlockFactory();
        Block blockI = factory.makeBlockI();
        blockI.rotate();
        assertArrayEquals(new int[][]{{1,0},{1,0},{2,0},{3,0}},
                convertToArray(blockI.getCells()));
    }

    private Object[] convertToArray(Collection<Cell> cells) {
        List<int[]> result = cells.stream().map(c -> (new int[]{c.x, c.y})).collect(Collectors.toList());
        return result.toArray();
    }
}
