package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.NullUI;
import com.github.gangz.emergentdesign.demo.tetris.controller.Game;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of Game Controller.
 */
public class GameTest
{
    @Test
    public void game_init_should_create_empty_piled_block()
    {
        Game game = new Game(new NullUI());
        Assert.assertEquals(0,game.piledBlock.size());
    }
}
