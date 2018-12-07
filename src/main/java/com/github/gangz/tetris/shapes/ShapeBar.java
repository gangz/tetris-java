package com.github.gangz.tetris.shapes;

import java.util.ArrayList;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.RotatableShape;

public class ShapeBar extends RotatableShape{
	public ShapeBar(){
		shape = new ArrayList<>();
		shape.add(new Cell(0,0));
		shape.add(new Cell(0,1));
		shape.add(new Cell(0,2));
		shape.add(new Cell(0,3));
		shapeList.add(shape);
		
		shape = new ArrayList<>();
		shape.add(new Cell(0,2));
		shape.add(new Cell(1,2));
		shape.add(new Cell(2,2));
		shape.add(new Cell(3,2));
		shapeList.add(shape);
		
		shape = shapeList.get(0);
		
	}
}
