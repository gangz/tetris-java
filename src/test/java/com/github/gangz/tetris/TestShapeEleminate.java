package com.github.gangz.tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.controller.ShapePlacement;
import com.github.gangz.tetris.shapes.ShapeFactory;

public class TestShapeEleminate {
        ShapeFactory shapeFactory;
        Shape shape;
        @Before
        public void setup(){
        	shapeFactory = new ShapeFactory();
        	shape = shapeFactory.make(ShapeFactory.TYPE_BAR);
        }
        
        @Test
        public void eleminate_a_whole_row(){
            int eleminatedRows = shape.eleminate(4);
            assertEquals(1,eleminatedRows);
            assertEquals(0,shape.size());
        }
        
        @Test
        public void eleminate_should_compress_full_blank_rows(){
        	ShapePlacement shapePlacement_main = new ShapePlacement(0,0);
        	shapePlacement_main.put(shape);
        	
        	ShapePlacement shapePlacement_line_1a = new ShapePlacement(1,0);
        	shapePlacement_line_1a.put(shapeFactory.make(ShapeFactory.TYPE_BAR));
        	shapePlacement_main.join(shapePlacement_line_1a);

        	ShapePlacement shapePlacement_line_1b = new ShapePlacement(1,4);
        	shapePlacement_line_1b.put(shapeFactory.make(ShapeFactory.TYPE_BAR));
        	shapePlacement_main.join(shapePlacement_line_1b);
        	
        	ShapePlacement shapePlacement_line_2 = new ShapePlacement(2,0);
        	shapePlacement_line_2.put(shapeFactory.make(ShapeFactory.TYPE_BAR));
        	shapePlacement_main.join(shapePlacement_line_2);
        	
        	shapePlacement_main.eleminate(8);
        	
        	assertEquals(1,shapePlacement_main.getAt(shapePlacement_main.size()-1).x);
        }

        
        @Test
        public void can_fall_down_exist_shape_when_a_line_eliminated(){
        	//Note: do not test in here: it is not convient. 
        	//      it is the reponsibility of the GameController.
        	//      add the test to GameController.
            
        	
        }
}
