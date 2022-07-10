package com.github.gangz.emergentdesign.demo.tetris.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class CellBoard extends JPanel {
    private static final int CELL_PIXEL_SIZE = 35;
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
    }
}
