package com.github.gangz.tetris;
import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.github.gangz.tetris.controller.CollisionDetector;
import com.github.gangz.tetris.controller.GameController;
import com.github.gangz.tetris.controller.ShapePlacement;
import com.github.gangz.tetris.shapes.ShapeFactory;
public class TestShapeCollisionDetector {
	ShapeFactory shapeFactory;
	CollisionDetector collisionDetector;
	@Before
	public void setup()
	{
		shapeFactory = new ShapeFactory();
		collisionDetector = new CollisionDetector();
	}
	
	@Test public void move_down_shoudld_be_ok(){
		ShapePlacement shapePlacement_1 = new ShapePlacement(0,0);
		shapePlacement_1.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		ShapePlacement shapePlacement_2 = new ShapePlacement(2,0);
		shapePlacement_2.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		assertFalse(collisionDetector.isCollision(shapePlacement_1, shapePlacement_2, GameController.MOVE_DOWN));
	}

	@Test public void move_down_shoudld_be_collision(){
		ShapePlacement shapePlacement_1 = new ShapePlacement(0,0);
		shapePlacement_1.put(shapeFactory.make(ShapeFactory.TYPE_BAR));
		shapePlacement_1.moveDown();

		ShapePlacement shapePlacement_2 = new ShapePlacement(2,0);
		shapePlacement_2.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		assertTrue(collisionDetector.isCollision(shapePlacement_1, shapePlacement_2, GameController.MOVE_DOWN));
	}

	@Test public void move_left_shoudld_be_collision(){
		ShapePlacement shapePlacement_1 = new ShapePlacement(0,4);
		shapePlacement_1.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		ShapePlacement shapePlacement_2 = new ShapePlacement(0,0);
		shapePlacement_2.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		assertTrue(collisionDetector.isCollision(shapePlacement_1, shapePlacement_2, GameController.MOVE_LEFT));
	}

	@Test public void move_left_shoudld_be_ok(){
		ShapePlacement shapePlacement_1 = new ShapePlacement(0,5);
		shapePlacement_1.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		ShapePlacement shapePlacement_2 = new ShapePlacement(0,0);
		shapePlacement_2.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		assertFalse(collisionDetector.isCollision(shapePlacement_1, shapePlacement_2, GameController.MOVE_LEFT));
	}

	@Test public void move_right_shoudld_be_ok(){
		ShapePlacement shapePlacement_1 = new ShapePlacement(0,0);
		shapePlacement_1.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		ShapePlacement shapePlacement_2 = new ShapePlacement(0,5);
		shapePlacement_2.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		assertFalse(collisionDetector.isCollision(shapePlacement_1, shapePlacement_2, GameController.MOVE_RIGHT));
	}

	@Test public void move_right_shoudld_be_collision(){
		ShapePlacement shapePlacement_1 = new ShapePlacement(0,0);
		shapePlacement_1.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		ShapePlacement shapePlacement_2 = new ShapePlacement(0,4);
		shapePlacement_2.put(shapeFactory.make(ShapeFactory.TYPE_BAR));

		assertTrue(collisionDetector.isCollision(shapePlacement_1, shapePlacement_2, GameController.MOVE_RIGHT));
	}

}

