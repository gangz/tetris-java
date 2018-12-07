package com.github.gangz.tetris.shapes;

import java.util.ArrayList;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.RotatableShape;

public class ShapeInvL extends RotatableShape{
	public ShapeInvL(){
		
		shape = new ArrayList<>();
		shape.add(new Cell(0,0));
		shape.add(new Cell(0,1));
		shape.add(new Cell(0,2));
		shape.add(new Cell(1,0));
		shapeList.add(shape);
		
		shape = new ArrayList<>();
		shape.add(new Cell(0,0));
		shape.add(new Cell(1,0));
		shape.add(new Cell(2,0));
		shape.add(new Cell(2,1));
		shapeList.add(shape);

		shape = new ArrayList<>();
		shape.add(new Cell(1,0));
		shape.add(new Cell(1,1));
		shape.add(new Cell(1,2));
		shape.add(new Cell(0,2));
		shapeList.add(shape);		

//      {{0,0},{0,1},{1,1},{2,1}}, //INV_L
		shape = new ArrayList<>();
		shape.add(new Cell(0,2));
		shape.add(new Cell(1,2));
		shape.add(new Cell(2,2));
		shape.add(new Cell(3,2));
		shapeList.add(shape);
		
		shape = shapeList.get(0);
		
	}
}
