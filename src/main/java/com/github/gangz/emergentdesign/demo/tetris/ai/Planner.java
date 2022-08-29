package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Block;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Planner {
    public static final int TURN_TIMES = 4;
    public final int horizonalSize;
    private final Parameter parameter;
    private BlockJoiner blockJoiner;

    public Planner(Parameter parameter,int horizonalSize, Block wall) {
        this.horizonalSize = horizonalSize;
        this.parameter = parameter;
        blockJoiner = new BlockJoiner(wall);
    }

    class Data {
        int move;
        int turn;
        double score;
        Data(int move, int turn, double score){
            this.move = move;
            this.turn = turn;
            this.score = score;
        }
    }

    public Action computeAction(Block activeBlock, Block piledBlock) {
        List<Data> dataList = new ArrayList<>();
        for (int turn = 0; turn< TURN_TIMES; turn++) {
            for (int move=0;move<horizonalSize;move++) {
                Block joinedBlock = blockJoiner.joinBlock(activeBlock, piledBlock, move, turn);
                if (joinedBlock!=null) {
                    double score = evaluate(piledBlock,joinedBlock);
                    dataList.add(new Data(move, turn, score));
                }
            }
        }
        dataList.sort(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (Math.abs(o1.score-o2.score)>1e-6)
                    return (o1.score-o2.score)>0?-1:1;
                return o1.move-o2.move;
            }
        });
        Data result = dataList.get(0);
        return new Action(result.move,result.turn);
    }

    private double evaluate(Block piledBlock, Block joinedBlock) {
        int eliminatedRows = joinedBlock.eliminate(horizonalSize);
        ShapeFeature currentFeature = new ShapeFeature(piledBlock.getCells(),horizonalSize);
        ShapeFeature newFeature = new ShapeFeature(joinedBlock.getCells(),horizonalSize);
        return parameter.removeLinesWeight * eliminatedRows
                -parameter.heightWeight*joinedBlock.height()
                -parameter.holeCoverWeight * newFeature.holeCover()
                -parameter.holeAddingWeight * (newFeature.holes()-currentFeature.holes())
                -parameter.bumpWellWeight *(newFeature.bumpiness()+newFeature.well());
    }
}
