package com.github.gangz.tetris.shapes;

import java.util.ArrayList;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.RotatableShape;

public class ShapeSquare extends RotatableShape{
	public ShapeSquare(){

		shape = new ArrayList<>();
		shape.add(new Cell(0,0));
		shape.add(new Cell(0,1));
		shape.add(new Cell(1,0));
		shape.add(new Cell(1,1));
		shapeList.add(shape);
		
		shape = shapeList.get(0);
		
	}
}
