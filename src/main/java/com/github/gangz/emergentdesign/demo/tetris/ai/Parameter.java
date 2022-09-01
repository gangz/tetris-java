package com.github.gangz.emergentdesign.demo.tetris.ai;

import java.io.Serializable;

public class Parameter implements Serializable {
    public double heightWeight = 0.0822895136587764;
    public double removeLinesWeight  = 0.38725977841579506;
    public double holeCoverWeight = 0.03826401807560197 ;
    public double holeAddingWeight = 0.8968050723650041;
    public double bumpWellWeight = 0.19373906953493178;

    public Parameter(){
    }
    public Parameter(double heightWeight, double removeLinesWeight,
                     double holeCoverWeight, double holeAddingWeight, double bumpWeight){
        this.heightWeight = heightWeight;
        this.removeLinesWeight = removeLinesWeight;
        this.holeCoverWeight = holeCoverWeight;
        this.holeAddingWeight = holeAddingWeight;
        this.bumpWellWeight = bumpWeight;
        normalize();
    }

    private void normalize() {
        double norm = Math.sqrt(heightWeight*heightWeight +
                removeLinesWeight*removeLinesWeight +
                holeCoverWeight * holeCoverWeight +
                holeAddingWeight * holeAddingWeight +
                bumpWellWeight * bumpWellWeight);
        heightWeight/=norm;
        removeLinesWeight/=norm;
        holeCoverWeight /=norm;
        holeAddingWeight /=norm;
        bumpWellWeight /=norm;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "heightWeight=" + heightWeight +
                ", removeLinesWeight=" + removeLinesWeight +
                ", holeCoverWeight=" + holeCoverWeight +
                ", holeWeight=" + holeAddingWeight +
                ", bumpWeight=" + bumpWellWeight +
                '}';
    }
}
