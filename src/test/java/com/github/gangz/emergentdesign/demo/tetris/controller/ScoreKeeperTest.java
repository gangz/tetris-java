package com.github.gangz.emergentdesign.demo.tetris.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreKeeperTest
{
    @Test
    public void one_row_count_10()
    {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        assertEquals(new Integer(10),scoreKeeper.count(1).score());
    }
}
