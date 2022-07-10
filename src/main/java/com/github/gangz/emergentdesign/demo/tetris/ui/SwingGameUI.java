package com.github.gangz.emergentdesign.demo.tetris.ui;

import javax.swing.*;
import java.awt.*;

public class SwingGameUI extends JFrame implements GameUI {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 640;
    MainBoard mainBoard;
    PreviewBoard previewBoard;
    ScoreBoard scoreBoard;
    public SwingGameUI(){
        initGameBoard();
    }

    /**
     * Init the game board
     */
    private void initGameBoard() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setTitle("Tetris");
        this.setBackground(Color.GREEN);
        this.setLayout(null);
        this.setVisible(true);

    }

}
