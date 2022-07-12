package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.util.ArrayList;
import java.util.List;

public class Shape{
    List<Cell> shape =new ArrayList<>();
    public Shape(){
    }

    public void rotate() {
    }

    public List<Cell> getCells() {
        return shape;
    }
}
