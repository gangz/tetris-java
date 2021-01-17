package com.github.gangz.tetris;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.controller.Block;
import com.github.gangz.tetris.shapes.ShapeFactory;
public class TestBlock {
	ShapeFactory shapeFactory;
	Shape bar;
	Block block;

	@Before
	public void init() {
		shapeFactory = new ShapeFactory();
		bar = shapeFactory.make(ShapeFactory.I);
		block = new Block(0,0);
		block.put(bar);
	}
	
	@Test
	public void  place_vertical_should_add_one_if_move_down(){
		//action
		block.moveDown();
		//check
		assertEquals(4, block.size());
		for(int i=0;i<4;i++){
			Cell c = block.getAt(i);
			assertEquals(1,c.x);
			assertEquals(i,c.y);
		}
	}
	
	
	@Test
	public void  place_horinzal_should_add_one_if_move_right() {
		//action
		block.moveRight();

		//check
		assertEquals(4, block.size());
		for(int i=0;i<4;i++){
			Cell c = block.getAt(i);
			assertEquals(0,c.x);
			assertEquals(i+1,c.y);
		}
		//tear down
	}

	@Test
	public void   place_horinzal_should_sub_one_if_move_left() {
		//action
		block.moveLeft();

		//check
		assertEquals(4, block.size());
		for(int i=0;i<4;i++){
			Cell c = block.getAt(i);
			assertEquals(0,c.x);
			assertEquals(i-1,c.y);
		}
		//tear down
	}
}








