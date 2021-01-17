package com.github.gangz.tetris;

import static org.junit.Assert.assertEquals;

import com.github.gangz.tetris.controller.Block;
import org.junit.Before;
import org.junit.Test;

import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.shapes.ShapeFactory;
public class TestShapeRotation {
	ShapeFactory shapeFactory;
	Block block;
	@Before
	public void setup(){
	}

	@Test
	public void turn_a_bar_from_h_to_v(){
		shapeFactory = new ShapeFactory();
		Shape bar_h = shapeFactory.make(ShapeFactory.I);
		block = new Block(0,0,bar_h);
		block.rotate();
		assertEquals(0, block.getAt(0).x);
		assertEquals(2, block.getAt(0).y);
		assertEquals(1, block.getAt(1).x);
		assertEquals(2, block.getAt(1).y);
		assertEquals(2, block.getAt(2).x);
		assertEquals(2, block.getAt(2).y);
		assertEquals(3, block.getAt(3).x);
		assertEquals(2, block.getAt(3).y);
	}
}
