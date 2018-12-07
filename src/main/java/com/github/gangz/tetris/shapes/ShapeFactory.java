package com.github.gangz.tetris.shapes;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.Shape;

public class ShapeFactory {

	public static final int TYPE_BAR = 0;
	public static final int TYPE_SQUARE = 1;
	public static final int TYPE_T = 2;
	public static final int TYPE_INV_Z = 3;
	public static final int TYPE_INV_L = 4;
	public static final int TYPE_Z = 5;
	public static final int TYPE_L = 6;
	public static final int TYPE_NULL = 7;

	public Shape make(int type) {
		switch (type)
		{
		case TYPE_BAR:
			return new ShapeBar();
		case TYPE_SQUARE:
			return new ShapeSquare();
		case TYPE_L:
			return new ShapeL();
		case TYPE_INV_L:
			return new ShapeInvL();
		case TYPE_Z:
			return new ShapeZ();
		case TYPE_INV_Z:
			return new ShapeInvZ();
		case TYPE_T:
			return new ShapeT();
		}
		return new Shape();
	}

	public Shape makeWall(int x_length, int y_length) {
        Shape shape = new Shape();
        for (int x=0;x<x_length;x++)
        {
                shape.add(new Cell(x,0));
                shape.add(new Cell(x,y_length-1));
        }
        for (int y=0;y<y_length;y++)
        {
                shape.add(new Cell(0,y));
                shape.add(new Cell(x_length-1,y));
        }
        return shape;
	}

}
