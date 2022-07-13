package com.github.gangz.emergentdesign.demo.tetris.controller;

import static org.junit.Assert.*;

import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import org.junit.Test;

public class TestBlockElimination {
	@Test
	public void eliminate_shape_should_success(){
		Block block = new Block(new ShapeFactory().makeHorzionalBar(8));
        int eliminatedRows = block.eliminate(8);
        assertEquals(1,eliminatedRows);
        assertEquals(0,block.getCells().size());
	}
}