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
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockTest
{
    @Test
    public void should_join_2_blocks()
    {
        ShapeFactory factory = new ShapeFactory();
        Block b_1 = new Block(factory.makeI());
        Block b_2 = new Block(factory.makeT());

        b_1.join(b_2);
        assertEquals(8,b_1.size());
    }

    @Test
    public void calc_block_height()
    {
        Block block = new Block(new ShapeFactory().makeO());
        assertEquals(2,block.height());
    }

    @Test
    public void eliminate_shape_should_success(){
        Block block = new Block(new ShapeFactory().makeHorzionalBar(8));
        int eliminatedRows = block.eliminate(8);
        Assert.assertEquals(1,eliminatedRows);
        Assert.assertEquals(0,block.getCells().size());
    }

    @Test
    public void eliminate_shape_should_compress_low_y_value_rows(){

        Block block_1 = new Block(0,2,new ShapeFactory().makeHorzionalBar(1));
        Block block_2 = new Block(0,1,new ShapeFactory().makeHorzionalBar(8));
        Block block_3 = new Block(0,0,new ShapeFactory().makeHorzionalBar(1));

        block_1.join(block_2);
        block_1.join(block_3);
        block_1.eliminate(8);
        Assert.assertEquals(2,block_1.size());
        for (Cell cell:block_1.getCells()){
            Assert.assertTrue(cell.getY()>0);
        }
    }
}


