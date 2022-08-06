package com.github.gangz.emergentdesign.demo.tetris.genetic;

import com.github.gangz.emergentdesign.demo.tetris.ai.AIPlayer;
import com.github.gangz.emergentdesign.demo.tetris.ai.Parameter;
import com.github.gangz.emergentdesign.demo.tetris.controller.BlockCreatedEvent;
import com.github.gangz.emergentdesign.demo.tetris.controller.Game;
import com.github.gangz.emergentdesign.demo.tetris.ui.GameUI;

import java.util.Observable;
import java.util.Observer;

public class AutoGame implements GameUI, Observer {
    Game game = null;
    Integer score = 0;
    Integer blocks = 500;
    private Parameter parameter;

    public AutoGame(Parameter parameter) {
        this.parameter = parameter;
    }

    public static void main(String[] args){
        AutoGame game = new AutoGame(new Parameter());
        game.play();
        System.out.println(game.score);
    }
    @Override
    public void setController(Game game) {
    }
    @Override
    public void dataChanged() {
        if (game!=null)
            this.score = game.getScore();
    }

    @Override
    public void notifyGameOver() {
        score = game.getScore();
        this.blocks=-1;
    }

    public void play(){
        game = new Game(this);
        game.addObserver(this);
        AIPlayer player = new AIPlayer(parameter,game);
        player.start();
        while(this.blocks>0){
            game.moveDown();
        }
    }

    @Override
    public void update(Observable o, Object event) {
        if (event instanceof BlockCreatedEvent)
            this.blocks--;
    }
}
