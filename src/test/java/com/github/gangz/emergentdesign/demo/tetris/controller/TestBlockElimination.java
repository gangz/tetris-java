package com.github.gangz.emergentdesign.demo.tetris.controller;

import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import org.junit.Assert;
import org.junit.Test;

public class TestBlockElimination {
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