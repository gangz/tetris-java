package com.github.gangz.emergentdesign.demo.tetris.genetic;

import com.github.gangz.emergentdesign.demo.tetris.ai.AIPlayer;
import com.github.gangz.emergentdesign.demo.tetris.ai.Parameter;
import com.github.gangz.emergentdesign.demo.tetris.controller.Block;
import com.github.gangz.emergentdesign.demo.tetris.controller.BlockCreatedEvent;
import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;
import com.github.gangz.emergentdesign.demo.tetris.controller.Game;
import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;
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
        game.getPiledBlock().join(createRandomBlockWithHole());
        game.addObserver(this);
        AIPlayer player = new AIPlayer(parameter,game);
        player.start();
        while(this.blocks>0){
            game.moveDown();
        }
    }

    private Block createRandomBlockWithHole() {
        Shape shape = new Shape();
        int lines = 8;
        for (int x=0;x<Game.HORIZONAL_SIZE;x++){
            for (int y=0;y<lines;y++){
                if (Math.random()>0.2){
                    shape.add(new Cell(x,y));
                }
            }
        }
        int elimimateLines = shape.eliminate(Game.HORIZONAL_SIZE);
        return  new Block(0,Game.VERTICAL_SIZE-lines+elimimateLines,shape);
    }

    @Override
    public void update(Observable o, Object event) {
        if (event instanceof BlockCreatedEvent)
            this.blocks--;
    }

    public int score() {
        //if game is over, give penalty;
        if (this.blocks<0)
            return game.getScore()/2;
        return game.getScore();
    }
}
