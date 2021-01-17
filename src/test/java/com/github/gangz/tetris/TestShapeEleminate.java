package com.github.gangz.tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.controller.Block;
import com.github.gangz.tetris.shapes.ShapeFactory;

public class TestShapeEleminate {
        ShapeFactory shapeFactory;
        Shape shape;
        @Before
        public void setup(){
        	shapeFactory = new ShapeFactory();
        	shape = shapeFactory.make(ShapeFactory.I);
        }
        
        @Test
        public void eleminate_a_whole_row(){
            int eleminatedRows = shape.eliminate(4);
            assertEquals(1,eleminatedRows);
            assertEquals(0,shape.size());
        }
        
        @Test
        public void eleminate_should_compress_full_blank_rows(){
        	Block block_main = new Block(0,0,shape);
        	
        	Block block_line_1A = new Block(1,0,shapeFactory.make(ShapeFactory.I));
        	block_main.join(block_line_1A);

        	Block block_line_1B = new Block(1,4,shapeFactory.make(ShapeFactory.I));
        	block_main.join(block_line_1B);
        	
        	Block block_line_2 = new Block(2,0,shapeFactory.make(ShapeFactory.I));
        	block_main.join(block_line_2);
        	
        	block_main.eliminate(8);
        	
        	assertEquals(1, block_main.getAt(block_main.size()-1).x);
        }

        
        @Test
        public void can_fall_down_exist_shape_when_a_line_eliminated(){
        	//Note: do not test in here: it is not convenient.
        	//      it is the responsibility of the GameController.
        	//      add the test to GameController.
        }
}
