package com.github.gangz.tetris;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.shapes.ShapeFactory;

public class TestShape {
	@Test
	public void eleminate_a_whole_row(){
		ShapeFactory factory = new ShapeFactory();
		Shape shape = factory.make(ShapeFactory.I);
		int rows = shape.eliminate(4);
		assertEquals(1,rows);
		assertEquals(0,shape.size());
	}


	@Test
	public void eleminate_a_row_should_be_compressed(){
		Shape shape = new Shape();
		shape.add(new Cell(0,0));
		shape.add(new Cell(1,0));
		shape.add(new Cell(1,1));

		int rows = shape.eliminate(2);
		assertEquals(1,rows);
		assertEquals(1,shape.size());
		assertEquals(0,shape.getAt(0).x);
		assertEquals(0,shape.getAt(0).y);
	}

}
