package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollisionDetectorTest
{
    ShapeFactory factory = new ShapeFactory();
    @Test
    public void move_down_should_not_collision()
    {
        Block moveBlock = new Block(0,0,factory.makeO());
        Block stillBlock = new Block(0,3,factory.makeO());
        assertFalse(CollisionDetector.isCollision(moveBlock,stillBlock,Direction.DOWN));
    }

    @Test
    public void move_down_should_collision()
    {
        Block moveBlock = new Block(0,0,factory.makeO());
        Block stillBlock = new Block(0,2,factory.makeO());
        assertTrue(CollisionDetector.isCollision(moveBlock,stillBlock,Direction.DOWN));
    }
}
