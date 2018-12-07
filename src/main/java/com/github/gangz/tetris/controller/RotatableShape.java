package com.github.gangz.tetris.controller;

import java.util.ArrayList;

public class RotatableShape extends Shape {
	
	protected ArrayList<ArrayList<Cell>> shapeList;
	int currentShapeIndex = 0;
	public RotatableShape(){
		shapeList = new ArrayList<ArrayList<Cell>>();
	}
	public void rotate() {
		currentShapeIndex++;
		currentShapeIndex%=shapeList.size();
		this.shape = shapeList.get(currentShapeIndex);
	}
}
