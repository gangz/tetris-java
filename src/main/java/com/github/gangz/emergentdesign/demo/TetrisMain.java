package com.github.gangz.emergentdesign.demo;

import com.github.gangz.emergentdesign.demo.controller.Game;
import com.github.gangz.emergentdesign.demo.ui.SwingGameUI;

/**
 * Tetris Project
 * A demo project for my book "Software Design"
 * @author gangz
 * @date 2022.07.08
 */
public class TetrisMain
{
    public static void main()
    {
        Game game = new Game(new SwingGameUI());
    }
}
