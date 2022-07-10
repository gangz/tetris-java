package com.github.gangz.emergentdesign.demo.tetris;

import com.github.gangz.emergentdesign.demo.tetris.controller.Game;
import com.github.gangz.emergentdesign.demo.tetris.ui.SwingGameUI;

/**
 * Tetris Project
 * A demo project for outside-in practices
 * @author gangz
 * @date 2022.07.08
 */
public class TetrisMain
{
    public static void main(String[] args)
    {
        new Game(new SwingGameUI());
    }
}
