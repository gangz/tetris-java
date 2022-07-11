package com.github.gangz.emergentdesign.demo.tetris.ui;

import com.github.gangz.emergentdesign.demo.tetris.controller.Block;
import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

public class CellBoard extends JPanel {
    private static final int CELL_PIXEL_SIZE = 35;
    private Block block;
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
        if (block == null) {
            return;
        }
        List<Cell> cells = block.getCells();
        //clone the cell list to avoid concurrency problem
        cells = (List<Cell>) ((ArrayList<Cell>)cells).clone();
        for (Cell cell : cells) {
            g.fill3DRect((block.getX()+cell.getX()) * CELL_PIXEL_SIZE,
                    (block.getY()+cell.getY()) * CELL_PIXEL_SIZE, CELL_PIXEL_SIZE, CELL_PIXEL_SIZE, true);
        }
    }

    public void refresh(Block block) {
        this.block = block;
        repaint();
    }
}
