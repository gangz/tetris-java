package com.github.gangz.emergentdesign.demo.tetris.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MainBoard extends JPanel{
    private static final int CELL_PIXEL_SIZE = 35;
    int horizonalCellCount = 8;
    int verticalCellCount = 16;
    public MainBoard(JFrame parent, int left, int top) {
        init();
        parent.add(this);
        setBounds(20, 20, getWidth(), getHeight());
    }

    private void init() {
        this.setBackground(Color.DARK_GRAY);
        this.setSize(new Dimension(horizonalCellCount * CELL_PIXEL_SIZE, verticalCellCount * CELL_PIXEL_SIZE));
        setBorder(new EtchedBorder());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
