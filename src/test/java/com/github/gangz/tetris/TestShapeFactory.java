package com.github.gangz.tetris;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.Shape;
import com.github.gangz.tetris.shapes.ShapeFactory;
public class TestShapeFactory {
	@Test
	public void can_produce_a_bar(){
        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.make(ShapeFactory.TYPE_BAR);
        assertEquals(4,shape.size());
        for(int i=0;i<4;i++){
                Cell c = shape.getAt(i);
                assertEquals(0,c.x);
                assertEquals(i,c.y);
        }
	}
	
	@Test
	public void can_produce_a_square(){
        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.make(ShapeFactory.TYPE_SQUARE);
        assertEquals(4,shape.size());

        assertEquals(0,shape.getAt(0).x);
        assertEquals(0,shape.getAt(0).y);

        assertEquals(0,shape.getAt(1).x);
        assertEquals(1,shape.getAt(1).y);
        
        assertEquals(1,shape.getAt(2).x);
        assertEquals(0,shape.getAt(2).y);

        assertEquals(1,shape.getAt(3).x);
        assertEquals(1,shape.getAt(3).y);

	}
	
	//Note: It is not necessary to write test for other shapes
	//      Low Risk!
/*	@Test
	public void can_produce_a_Z(){
	}

	@Test
	public void can_produce_a_inv_Z(){
	}
	@Test
	public void can_produce_a_L(){
	}
	@Test
	public void can_produce_a_inv_L(){
	}	
	@Test
	public void can_produce_a_T(){
	}
	
*/
}
