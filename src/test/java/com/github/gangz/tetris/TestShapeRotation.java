package com.github.gangz.tetris;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.controller.ShapePlacement;
import com.github.gangz.tetris.shapes.ShapeFactory;
public class TestShapeRotation {
	ShapeFactory shapeFactory;
	ShapePlacement shapePlacement;
	@Before
	public void setup(){
		shapeFactory = new ShapeFactory();
		shapePlacement = new ShapePlacement(0,0);
	}

	@Test
	public void turn_a_bar_from_h_to_v(){
		Shape bar_h = shapeFactory.make(ShapeFactory.TYPE_BAR);
		shapePlacement.put(bar_h);
		shapePlacement.rotate();
		assertEquals(0,shapePlacement.getAt(0).x);
		assertEquals(2,shapePlacement.getAt(0).y);
		assertEquals(1,shapePlacement.getAt(1).x);
		assertEquals(2,shapePlacement.getAt(1).y);
		assertEquals(2,shapePlacement.getAt(2).x);
		assertEquals(2,shapePlacement.getAt(2).y);
		assertEquals(3,shapePlacement.getAt(3).x);
		assertEquals(2,shapePlacement.getAt(3).y);
	}
}
