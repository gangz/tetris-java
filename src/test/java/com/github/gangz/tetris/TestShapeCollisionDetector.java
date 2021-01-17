package com.github.gangz.tetris;
import static org.junit.Assert.*;

import com.github.gangz.tetris.controller.Block;
import org.junit.*;

import com.github.gangz.tetris.controller.CollisionDetector;
import com.github.gangz.tetris.controller.GameController;
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
		Block block_1 = new Block(0,0);
		block_1.put(shapeFactory.make(ShapeFactory.I));

		Block block_2 = new Block(2,0);
		block_2.put(shapeFactory.make(ShapeFactory.I));

		assertFalse(collisionDetector.isCollision(block_1, block_2, GameController.MOVE_DOWN));
	}

	@Test public void move_down_shoudld_be_collision(){
		Block block_1 = new Block(0,0);
		block_1.put(shapeFactory.make(ShapeFactory.I));
		block_1.moveDown();

		Block block_2 = new Block(2,0);
		block_2.put(shapeFactory.make(ShapeFactory.I));

		assertTrue(collisionDetector.isCollision(block_1, block_2, GameController.MOVE_DOWN));
	}

	@Test public void move_left_shoudld_be_collision(){
		Block block_1 = new Block(0,4);
		block_1.put(shapeFactory.make(ShapeFactory.I));

		Block block_2 = new Block(0,0);
		block_2.put(shapeFactory.make(ShapeFactory.I));

		assertTrue(collisionDetector.isCollision(block_1, block_2, GameController.MOVE_LEFT));
	}

	@Test public void move_left_shoudld_be_ok(){
		Block block_1 = new Block(0,5);
		block_1.put(shapeFactory.make(ShapeFactory.I));

		Block block_2 = new Block(0,0);
		block_2.put(shapeFactory.make(ShapeFactory.I));

		assertFalse(collisionDetector.isCollision(block_1, block_2, GameController.MOVE_LEFT));
	}

	@Test public void move_right_shoudld_be_ok(){
		Block block_1 = new Block(0,0);
		block_1.put(shapeFactory.make(ShapeFactory.I));

		Block block_2 = new Block(0,5);
		block_2.put(shapeFactory.make(ShapeFactory.I));

		assertFalse(collisionDetector.isCollision(block_1, block_2, GameController.MOVE_RIGHT));
	}

	@Test public void move_right_shoudld_be_collision(){
		Block block_1 = new Block(0,0);
		block_1.put(shapeFactory.make(ShapeFactory.I));

		Block block_2 = new Block(0,4);
		block_2.put(shapeFactory.make(ShapeFactory.I));

		assertTrue(collisionDetector.isCollision(block_1, block_2, GameController.MOVE_RIGHT));
	}

}

