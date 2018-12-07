package com.github.gangz.tetris;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.controller.ShapePlacement;
import com.github.gangz.tetris.shapes.ShapeFactory;

public class TestShapeJoin {
		ShapeFactory shapeFactory;
		Shape bar;
		ShapePlacement shapePlacement;
		@Before
		public void setup(){
			shapeFactory  = new ShapeFactory();
			bar = shapeFactory.make(ShapeFactory.TYPE_BAR);
			shapePlacement = new ShapePlacement(0,0);
			shapePlacement.put(bar);
		}

		@Test
		public void join_two_shape(){
		Shape anotherBar = shapeFactory.make(ShapeFactory.TYPE_BAR);
		ShapePlacement anotherShapePlacement = new ShapePlacement(0,4);
		anotherShapePlacement.put(anotherBar);
		shapePlacement.join(anotherShapePlacement);
		assertEquals(8,shapePlacement.size());
		assertEquals(4,shapePlacement.getAt(4).y);
	}
}
