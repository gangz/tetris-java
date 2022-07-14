package com.github.gangz.emergentdesign.demo.tetris.ui;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JLabel {
    public ScoreBoard(JFrame parent, int left, int top, int width) {
        setOpaque(true);
        setBackground(Color.lightGray);
        this.setScore(0);
        parent.add(this);
        setBounds(left, top,width, 50);
        this.setFont(new Font ("Arial",Font.BOLD, 18));
    }

    public void setScore(Integer score) {
        this.setText("score: " + score.toString());
    }
}
