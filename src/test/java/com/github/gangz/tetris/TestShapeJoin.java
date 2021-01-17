package com.github.gangz.tetris;

import static org.junit.Assert.assertEquals;

import com.github.gangz.tetris.controller.Block;
import org.junit.Before;
import org.junit.Test;

import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.shapes.ShapeFactory;

public class TestShapeJoin {
		ShapeFactory shapeFactory;
		Shape bar;
		Block block;
		@Before
		public void setup(){
			shapeFactory  = new ShapeFactory();
			bar = shapeFactory.make(ShapeFactory.I);
			block = new Block(0,0);
			block.put(bar);
		}

		@Test
		public void join_two_shape(){
		Shape anotherBar = shapeFactory.make(ShapeFactory.I);
		Block anotherBlock = new Block(0,4);
		anotherBlock.put(anotherBar);
		block.join(anotherBlock);
		assertEquals(8, block.size());
		assertEquals(4, block.getAt(4).y);
	}
}
