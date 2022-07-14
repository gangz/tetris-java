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
package com.github.gangz.emergentdesign.demo.tetris.ui;

import com.github.gangz.emergentdesign.demo.tetris.controller.Block;
import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.util.Collection;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

public class CellBoard extends JPanel {
    private static final int CELL_PIXEL_SIZE = 35;
    private List<Block> blocks;
    public  CellBoard(JFrame parent, int left, int top,int horizonalCellCount, int verticalCellCount){
        init(horizonalCellCount,verticalCellCount);
        parent.add(this);
        setBounds(left, top, getWidth(), getHeight());
    }

    protected void init(int horizonalCellCount, int verticalCellCount) {
        this.setBackground(Color.DARK_GRAY);
        this.setSize(new Dimension(horizonalCellCount * CELL_PIXEL_SIZE, verticalCellCount * CELL_PIXEL_SIZE));
        setBorder(new EtchedBorder());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        blocks.forEach(block->displayBlock(g,block));
    }

    private void displayBlock(Graphics g, Block block) {
        if (block ==null) return;
        Collection<Cell> cells = block.getCells();
        //clone the cell list to avoid concurrency problem
        cells = (Collection<Cell>) ((ArrayList<Cell>)cells).clone();
        for (Cell cell : cells) {
            g.fill3DRect((cell.getX()) * CELL_PIXEL_SIZE,
                    (cell.getY()) * CELL_PIXEL_SIZE, CELL_PIXEL_SIZE, CELL_PIXEL_SIZE, true);
        }
    }

    public void refresh(List<Block> blocks) {
        this.blocks = blocks;
        repaint();
    }
}
