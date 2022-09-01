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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gangz.emergentdesign.demo.tetris.ai.AIPlayer;
import com.github.gangz.emergentdesign.demo.tetris.ai.Parameter;
import com.github.gangz.emergentdesign.demo.tetris.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SwingGameUI extends JFrame implements GameUI, KeyListener {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 800;
    private MainBoard mainBoard;
    private PreviewBoard previewBoard;
    private ScoreBoard scoreBoard;
    private Game controller;
    private JButton commandButton;
    private AIPlayer aiPlayer;

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
        createStartStopButton();
        createAIPlayerButton();
        this.setVisible(true);
        requestFocus();
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

    private void createStartStopButton() {
        commandButton = new JButton("Start/Stop");
        add(commandButton);
        commandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.pauseToogle();
                releaseFocus();
            }
        });
        commandButton.setBounds(scoreBoard.getX(), scoreBoard.getY()+scoreBoard.getHeight()+40,
                scoreBoard.getWidth(),60);
        commandButton.setFont(new Font ("Arial",Font.BOLD, 18));
    }

    private void createAIPlayerButton() {
        JButton command = new JButton("AI Player");
        add(command);
        command.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (aiPlayer==null) {
                    aiPlayer = new AIPlayer(readAIPlayerParameter(), controller);
                }
                aiPlayer.pauseToogle();
                releaseFocus();
            }

            private Parameter readAIPlayerParameter() {
                ObjectMapper mapper = new ObjectMapper();
                try {
                   return mapper.readValue(new File("./ai_parameters.json"), Parameter.class);
                } catch (IOException e) {
                    //use default value
                }
                return new Parameter();
            }
        });
        command.setBounds(commandButton.getX(), commandButton.getY()+commandButton.getHeight()+40,
                commandButton.getWidth(),60);
        command.setFont(new Font ("Arial",Font.BOLD, 18));
    }


    private void releaseFocus() {
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
                controller.pauseToogle();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
