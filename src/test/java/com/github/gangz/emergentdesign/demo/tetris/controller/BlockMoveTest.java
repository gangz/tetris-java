package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.NullUI;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test of Game Controller.
 */
public class BlockMoveTest
{
    @Test
    public void move_down_should_move_block_one_unit()
    {
        Game game = new Game(new NullUI());
        game.start();
        int x = game.getActiveBlock().getY();
        game.moveActiveBlockDown();
        assertEquals(x+1,game.getActiveBlock().getY());
    }
}
