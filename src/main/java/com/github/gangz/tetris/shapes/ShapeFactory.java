package com.github.gangz.tetris.shapes;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.Shape;

public class ShapeFactory {

	public static final int I = 0;
	public static final int O = 1;
	public static final int T = 2;
	public static final int S = 3;
	public static final int J = 4;
	public static final int Z = 5;
	public static final int L = 6;
	public static final int NULL = 7;

	public Shape make(int type) {
		switch (type)
		{
		case I:
			return new ShapeI();
		case O:
			return new ShapeO();
		case L:
			return new ShapeL();
		case J:
			return new ShapeJ();
		case Z:
			return new ShapeZ();
		case S:
			return new ShapeS();
		case T:
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
