package com.github.gangz.tetris.controller;

public class Block {
	Shape shape;
	public int x;
	public int y;
	public Block(int x, int y) {
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

	public void join(Block rhs) {
		 for (int i=0;i<rhs.size();i++){
             Cell c = rhs.getAt(i);
             Cell newCell = new Cell(c.x-x,c.y-y);
             shape.add(newCell);
		 }
	}

	public void eliminate(int max_width) {
		shape.eliminate(max_width);
	}

	public void rotate() {
		((RotatableShape)shape).rotate();
	}


}
