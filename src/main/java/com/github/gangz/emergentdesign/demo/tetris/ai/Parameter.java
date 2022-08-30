package com.github.gangz.emergentdesign.demo.tetris.ai;

import java.io.Serializable;

public class Parameter implements Serializable {
    public double heightWeight = 0.09055176136518844;
    public double removeLinesWeight  = 0.5647313393824509;
    public double holeCoverWeight = 0.03994584977060058 ;
    public double holeAddingWeight = 0.8012332728631142;
    public double bumpWellWeight = 0.1711971506075142;

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
