package com.github.gangz.emergentdesign.demo.tetris.ai;

import java.io.Serializable;

public class Parameter implements Serializable {
    public double heightWeight = 0.018541897930859582;
    public double removeLinesWeight  = 0.5073339640502378;
    public double rowTransitionWeight  = 0.010956114762031389;
    public double columnTransitionWeight = 0.29008357836920445 ;
    public double holeWeight  = 0.8089926136397839;
    public double bumpWeight = 0.05942120100017882;

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
        normalize();
    }

    private void normalize() {
        double norm = Math.sqrt(heightWeight*heightWeight +
                removeLinesWeight*removeLinesWeight +
                rowTransitionWeight*rowTransitionWeight+
                columnTransitionWeight*columnTransitionWeight+
                holeWeight*holeWeight +
                bumpWeight*bumpWeight);
        heightWeight/=norm;
        removeLinesWeight/=norm;
        rowTransitionWeight/=norm;
        columnTransitionWeight/=norm;
        holeWeight/=norm;
        bumpWeight/=norm;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "heightWeight=" + heightWeight +
                ", removeLinesWeight=" + removeLinesWeight +
                ", rowTransitionWeight=" + rowTransitionWeight +
                ", columnTransitionWeight=" + columnTransitionWeight +
                ", holeWeight=" + holeWeight +
                ", bumpWeight=" + bumpWeight +
                '}';
    }
}
