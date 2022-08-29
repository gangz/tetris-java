package com.github.gangz.emergentdesign.demo.tetris.ai;

import java.io.Serializable;

public class Parameter implements Serializable {
    public double heightWeight = 0.17112317836961635;
    public double removeLinesWeight  = 0.45910616745101074;
    public double holeCoverWeight = 0.0871102063425539 ;
    public double holeAddingWeight = 0.8503525804686778;
    public double bumpWellWeight = 0.19181858648380554;

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
