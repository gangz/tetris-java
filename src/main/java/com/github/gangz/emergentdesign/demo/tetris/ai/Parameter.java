package com.github.gangz.emergentdesign.demo.tetris.ai;

import java.io.Serializable;

public class Parameter implements Serializable {
    public double heightWeight = 0.11327985797952186;
    public double removeLinesWeight  = 0.5067290544621075;
    public double holeCoverWeight = 0.05020359878721567 ;
    public double holeAddingWeight = 0.8342647603984512;
    public double bumpWellWeight = 0.17853640347616853;

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
