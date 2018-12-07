package com.github.gangz.tetris.controller;

public class ShapePlacement {
	Shape shape;
	public int x;
	public int y;
	public ShapePlacement(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void put(Shape shape) {
		this.shape = shape;
	}



	public int size() {
		//assert shape!=null;
		return shape.size();
	}

	public Cell getAt(int index) {
		Cell c = new Cell(shape.getAt(index).x+x,shape.getAt(index).y+y); //use value object
		return c;
	}

	public void moveDown() {
		x++;
	}
	
	public void moveRight() {
		y++;
		
	}

	public void moveLeft() {
		y--;
		
	}

	public void join(ShapePlacement rhs) {
		 for (int i=0;i<rhs.size();i++){
             Cell c = rhs.getAt(i);
             Cell newCell = new Cell(c.x-x,c.y-y);
             shape.add(newCell);
		 }
	}

	public void eleminate(int max_width) {
		shape.eleminate(max_width);
	}

	public void rotate() {
		((RotatableShape)shape).rotate();
	}


}
