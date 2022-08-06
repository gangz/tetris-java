package com.github.gangz.emergentdesign.demo.tetris.ai;

public class Parameter {
    public double heightWeight = -40;
    public double removeLinesWeight  = 8;
    public double rowTransitionWeight  = 0;
    public double columnTransitionWeight = 0 ;
    public double holeWeight  = -20;
    public double bumpWeight = -7;
    public Parameter(){
    }
    public Parameter(double heightWeight,double removeLinesWeight,double rowTransitionWeight,
                     double columnTransitionWeight,double holeWeight,double bumpWeight){
        this.heightWeight = heightWeight;
        this.removeLinesWeight = removeLinesWeight;
        this.rowTransitionWeight = rowTransitionWeight;
        this.columnTransitionWeight = columnTransitionWeight;
        this.holeWeight = holeWeight;
        this.bumpWeight = bumpWeight;
    }
}
