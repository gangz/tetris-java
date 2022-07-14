package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.NullUI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockMoveTest
{
    @Test
    public void move_down_should_move_block_one_unit()
    {
        Game game = new Game(new NullUI());
        int x = game.getActiveBlock().getY();
        game.moveDown();
        assertEquals(x+1,game.getActiveBlock().getY());
    }
}
