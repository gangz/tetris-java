package com.github.gangz.tetris.shapes;

import java.util.ArrayList;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.RotatableShape;

public class ShapeZ extends RotatableShape{
	public ShapeZ(){
		shape = new ArrayList<>();
		shape.add(new Cell(0,0));
		shape.add(new Cell(0,1));
		shape.add(new Cell(1,1));
		shape.add(new Cell(1,2));
		shapeList.add(shape);
		
		shape = new ArrayList<>();
		shape.add(new Cell(0,1));
		shape.add(new Cell(1,0));
		shape.add(new Cell(1,1));
		shape.add(new Cell(2,0));
		shapeList.add(shape);
		
		shape = shapeList.get(0);
		
	}
}
