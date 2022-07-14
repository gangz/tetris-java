package com.github.gangz.emergentdesign.demo.tetris.ui;

import com.github.gangz.emergentdesign.demo.tetris.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class SwingGameUI extends JFrame implements GameUI, KeyListener {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 640;
    private MainBoard mainBoard;
    private PreviewBoard previewBoard;
    private ScoreBoard scoreBoard;
    private Game controller;

    public SwingGameUI() {
        initGameBoard();
        addKeyListener(this);
    }

    @Override
    public void setController(Game game) {
        this.controller = game;
    }

    @Override
    public void dataChanged() {
        mainBoard.refresh(Arrays.asList(controller.getActiveBlock(),controller.getPiledBlock()));
        previewBoard.refresh(Arrays.asList(controller.getNextBlock()));
        scoreBoard.setScore(controller.getScore());
    }

    @Override
    public void notifyGameOver() {
        JOptionPane.showMessageDialog(null,
                "Game over",
                "Press OK to start a new one",
                JOptionPane.INFORMATION_MESSAGE);
    }    /**
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
        scoreBoard = new ScoreBoard(this,
                mainBoard.getX()+mainBoard.getWidth()+40,
                previewBoard.getY()+previewBoard.getHeight() +40,
                previewBoard.getWidth());
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                controller.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                controller.moveRight();
                break;
            case KeyEvent.VK_DOWN:
                controller.moveDown();
                break;
            case KeyEvent.VK_UP:
                controller.turn();
                break;
            case KeyEvent.VK_ENTER:
                controller.start();
                break;
        }
    }
}
