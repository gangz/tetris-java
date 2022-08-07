package com.github.gangz.emergentdesign.demo.tetris.ai;

public class Parameter {
    public double heightWeight = 0.40015474789845895;
    public double removeLinesWeight  = 0.6430262003488009;
    public double rowTransitionWeight  = 0.19746275594503213;
    public double columnTransitionWeight = 0.08417740442485587 ;
    public double holeWeight  = 0.6132016035229552;
    public double bumpWeight = 0.065573633688152;

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
        double norm = heightWeight +
                removeLinesWeight +
                rowTransitionWeight+
                columnTransitionWeight+
                holeWeight +
                bumpWeight;
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
