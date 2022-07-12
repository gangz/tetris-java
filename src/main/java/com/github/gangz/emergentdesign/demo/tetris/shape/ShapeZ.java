package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShapeZ extends RotatableShape{
    public ShapeZ(){
        data = new int[][][]{
            {{0,0},{0,1},{1,1},{1,2}},
            {{0,1},{1,0},{1,1},{2,0}},
            {{0,0},{0,1},{1,1},{1,2}},
            {{0,1},{1,0},{1,1},{2,0}}};
    }
}
