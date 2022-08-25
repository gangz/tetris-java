package com.github.gangz.emergentdesign.demo.tetris.ai;


import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;
import com.github.gangz.emergentdesign.demo.tetris.shape.Shape;
import com.github.gangz.emergentdesign.demo.tetris.shape.ShapeFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShapeFeatureTest {
    @Test
    public void testColumnTransitions_O_is_zero(){
        Shape shape = new ShapeFactory().makeO();
        assertEquals(0,new ShapeFeature(shape.getCells(),2).columnTransitions());
    }

    @Test
    public void testColumnTransitions_x_is_6(){
        Shape shape = makeXShape();
        assertEquals(6,new ShapeFeature(shape.getCells(),3).columnTransitions());
    }

    @Test
    public void testHole_of_x_is_3(){
        Shape shape = makeXShape();
        assertEquals(3,new ShapeFeature(shape.getCells(),3).holes());
    }

    @Test
    public void testBump_of_x_is_2_in_case_of_width_3(){
        Shape shape = makeXShape();
        assertEquals(2,new ShapeFeature(shape.getCells(),3).bumpiness());
    }

    @Test
    public void testBump_of_x_is_5_in_case_of_width_3(){
        Shape shape = makeXShape();
        assertEquals(5,new ShapeFeature(shape.getCells(),4).bumpiness());
    }
    private Shape makeXShape() {
    /*
       1 0 1
       1 0 1
       0 1 0
       1 0 1
     */
        Shape shape = new Shape();
        shape.add(new Cell(0,0));
        shape.add(new Cell(0,2));
        shape.add(new Cell(1,1));
        shape.add(new Cell(2,0));
        shape.add(new Cell(2,2));
        return shape;
    }
}
