package com.github.gangz.emergentdesign.demo.tetris.controller;

public class ScoreKeeper {
    Integer score = 0;
    public ScoreKeeper count(int rows) {
        switch (rows){
            case 1:
                score+=10;
                break;
            case 2:
                score+=30;
                break;
            case 3:
                score+=50;
                break;
            case 4:
                score+=100;
                break;
            case 0:
                break;
        }
        return this;
    }

    public Integer score() {
        return score;
    }
}
