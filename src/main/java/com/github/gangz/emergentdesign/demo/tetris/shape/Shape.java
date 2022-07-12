package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

public class Shape{
    protected List<Cell> shape =new ArrayList<>();
    public Shape(){
    }

    public void rotate() {
        throw new UnsupportedOperationException("Not support rotation yet.");
    }

    public List<Cell> getCells() {
        return shape;
    }
}
