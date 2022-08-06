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
package com.github.gangz.emergentdesign.demo.tetris.ai;

import com.github.gangz.emergentdesign.demo.tetris.controller.Block;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class PlannerTest
{
    @Mock BlockJoiner blockJoiner;
    @Mock Block wall;
    @InjectMocks Planner planner = new Planner(new Parameter(),8, wall);
    @Ignore
    public void should_return_1move_0_turn_when_height_diff()
    {
        Block activeBlock = Mockito.mock(Block.class);
        Block piledBlock = Mockito.mock(Block.class);
        when(piledBlock.height()).thenReturn(1).thenReturn(0).thenReturn(1);
        when(blockJoiner.joinBlock(any(),any(),any(),any())).thenReturn(piledBlock);
        Action action = planner.computeAction(activeBlock, piledBlock);
        assertEquals(1,action.moveDistance);
    }

    @Ignore
    public void should_return_1move_0_turn_when_row_diff()
    {
        Block activeBlock = Mockito.mock(Block.class);
        Block piledBlock = Mockito.mock(Block.class);
        when(piledBlock.height()).thenReturn(0);
        when(piledBlock.eliminate(eq(planner.horizonalSize))).thenReturn(0).thenReturn(1).thenReturn(0);
        when(blockJoiner.joinBlock(any(),any(),any(),any())).thenReturn(piledBlock);
        Action action = planner.computeAction(activeBlock, piledBlock);
        assertEquals(1,action.moveDistance);
    }
}


