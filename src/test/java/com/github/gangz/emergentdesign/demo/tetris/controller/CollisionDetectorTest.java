/*
MIT License

Copyright (c) 2022 ZHANG Gang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
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
