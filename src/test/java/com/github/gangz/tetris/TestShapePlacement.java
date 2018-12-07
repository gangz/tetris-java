package com.github.gangz.tetris;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.controller.ShapePlacement;
import com.github.gangz.tetris.shapes.ShapeFactory;
public class TestShapePlacement {
	ShapeFactory shapeFactory;
	Shape bar;
	ShapePlacement shapePlacement;

	@Before
	public void init() {
		shapeFactory = new ShapeFactory();
		bar = shapeFactory.make(ShapeFactory.TYPE_BAR);
		shapePlacement = new ShapePlacement(0,0);
		shapePlacement.put(bar);
	}
	
	@Test
	public void  place_vertical_should_add_one_if_move_down(){
		//action
		shapePlacement.moveDown();
		//check
		assertEquals(4,shapePlacement.size());
		for(int i=0;i<4;i++){
			Cell c = shapePlacement.getAt(i);
			assertEquals(1,c.x);
			assertEquals(i,c.y);
		}
	}
	
	
	@Test
	public void  place_horinzal_should_add_one_if_move_right() {
		//action
		shapePlacement.moveRight();

		//check
		assertEquals(4,shapePlacement.size());
		for(int i=0;i<4;i++){
			Cell c = shapePlacement.getAt(i);
			assertEquals(0,c.x);
			assertEquals(i+1,c.y);
		}
		//tear down
	}

	@Test
	public void   place_horinzal_should_sub_one_if_move_left() {
		//action
		shapePlacement.moveLeft();

		//check
		assertEquals(4,shapePlacement.size());
		for(int i=0;i<4;i++){
			Cell c = shapePlacement.getAt(i);
			assertEquals(0,c.x);
			assertEquals(i-1,c.y);
		}
		//tear down
	}
}








