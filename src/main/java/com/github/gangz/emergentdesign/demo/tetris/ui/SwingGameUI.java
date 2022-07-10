package com.github.gangz.emergentdesign.demo.tetris.ui;

import com.github.gangz.emergentdesign.demo.tetris.controller.Game;

import javax.swing.*;
import java.awt.*;

public class SwingGameUI extends JFrame implements GameUI {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 640;
    private MainBoard mainBoard;
    private PreviewBoard previewBoard;
    private ScoreBoard scoreBoard;
    private Game controller;

    public SwingGameUI() {
        initGameBoard();
    }

    @Override
    public void setController(Game game) {
        this.controller = game;
    }

    /**
     * Init the game board
     */
    private void initGameBoard() {
        initWindow();
        createMainBoard();
        createPreviewBoard();
        createScoreBoard();
        this.setVisible(true);
    }


    private void initWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setTitle("Tetris");
        this.setLayout(null);
    }

    private void createMainBoard() {
        mainBoard = new MainBoard(this,20,20);
    }

    private void createPreviewBoard() {
        previewBoard = new PreviewBoard(this,
                mainBoard.getX()+mainBoard.getWidth()+40,
                mainBoard.getY());
    }

    private void createScoreBoard() {
        scoreBoard = new ScoreBoard();
    }

}
