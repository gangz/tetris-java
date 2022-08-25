package com.github.gangz.emergentdesign.demo.tetris.ai;

import java.io.Serializable;

public class Parameter implements Serializable {
    public double heightWeight = 0.018541897930859582;
    public double removeLinesWeight  = 0.5073339640502378;
    public double columnTransitionWeight = 0.29008357836920445 ;
    public double holeAddingWeight = 0.8089926136397839;
    public double bumpWellWeight = 0.05942120100017882;

    public Parameter(){
    }
    public Parameter(double heightWeight, double removeLinesWeight,
                     double columnTransitionWeight, double holeAddingWeight, double bumpWeight){
        this.heightWeight = heightWeight;
        this.removeLinesWeight = removeLinesWeight;
        this.columnTransitionWeight = columnTransitionWeight;
        this.holeAddingWeight = holeAddingWeight;
        this.bumpWellWeight = bumpWeight;
        normalize();
    }

    private void normalize() {
        double norm = Math.sqrt(heightWeight*heightWeight +
                removeLinesWeight*removeLinesWeight +
                columnTransitionWeight*columnTransitionWeight+
                holeAddingWeight * holeAddingWeight +
                bumpWellWeight * bumpWellWeight);
        heightWeight/=norm;
        removeLinesWeight/=norm;
        columnTransitionWeight/=norm;
        holeAddingWeight /=norm;
        bumpWellWeight /=norm;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "heightWeight=" + heightWeight +
                ", removeLinesWeight=" + removeLinesWeight +
                ", columnTransitionWeight=" + columnTransitionWeight +
                ", holeWeight=" + holeAddingWeight +
                ", bumpWeight=" + bumpWellWeight +
                '}';
    }
}
