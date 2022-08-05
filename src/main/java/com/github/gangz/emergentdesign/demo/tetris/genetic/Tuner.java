package com.github.gangz.emergentdesign.demo.tetris.genetic;

import com.github.gangz.emergentdesign.demo.tetris.ai.AIPlayer;
import com.github.gangz.emergentdesign.demo.tetris.ai.Parameter;
import com.github.gangz.emergentdesign.demo.tetris.controller.Game;
import com.github.gangz.emergentdesign.demo.tetris.ui.GameUI;
import com.github.gangz.emergentdesign.demo.tetris.ui.NullUI;
import com.github.gangz.emergentdesign.demo.tetris.ui.SwingGameUI;

public class Tuner implements GameUI {
    Game game = null;
    public static void main(String[] args)
    {
        new Tuner().play();
    }

    @Override
    public void setController(Game game) {
        this.game = game;
    }
    @Override
    public void dataChanged() {
        if (game!=null)
            System.out.println(game.getScore());
    }

    @Override
    public void notifyGameOver() {
        play();
    }
    public void play(){
        game = new Game(this);
        AIPlayer player = new AIPlayer(new Parameter(),game);
        player.pauseToogle();
        game.pauseToogle();
    }
}
