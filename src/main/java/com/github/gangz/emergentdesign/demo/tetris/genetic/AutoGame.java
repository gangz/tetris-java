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
    int blocks = 500;
    private Parameter parameter;

    public AutoGame(Parameter parameter, int max_blocks) {
        this.parameter = parameter;
        this.blocks = max_blocks;
    }

    public static void main(String[] args){
        AutoGame game = new AutoGame(new Parameter(),500);
        game.play();
    }
    @Override
    public void setController(Game game) {
    }

    @Override
    public void dataChanged() {
    }

    @Override
    public void notifyGameOver() {
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

    public int verticalSize() {
        int size = Game.VERTICAL_SIZE - game.getPiledBlock().height();
        return size>0?size:0;
    }
}
