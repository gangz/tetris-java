/**
 * The main UI of Tetris game 
 * Gang ZHANG(gangz@emergentdesign.cn)
 * All rights reserved.
 */

package com.github.gangz.tetris.ui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.github.gangz.tetris.controller.GameController;
import com.github.gangz.tetris.controller.GameUI;
import com.github.gangz.tetris.controller.ShapePlacement;


public class SwingTetrisUI extends JFrame implements GameUI {
	private static final int WINDOW_HEIGHT = 400;
	private static final int WINDOW_WIDTH = 200;
	private static final long serialVersionUID = -5339939426923342316L;
	public SwingTetrisUI() {
	}
	public void displayUI() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}

	public void notifyGameOver() {
		mainBoard.timer.stop();
		int confirmed =  JOptionPane.showConfirmDialog(null, "Game is over, do you want to play a new one?",
				"Game Over", JOptionPane.YES_NO_OPTION);
		if (confirmed == JOptionPane.YES_OPTION) {
			controller.play();
		} else {
		}
	}

    ActiveBoard mainBoard;
    Board previewBoard;
    JLabel score;
    JButton startPauseStop;
	private GameController controller;
    
	public void init() {
		setSize(WINDOW_WIDTH*2, WINDOW_HEIGHT);
		setTitle("Tetris");
	   
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        mainBoard = new ActiveBoard(controller,8,20);
        previewBoard = new Board(4,4);
        this.add(mainBoard);
        this.add(previewBoard);
	    startPauseStop = new JButton("Start");
        this.add(startPauseStop);
        score= new JLabel("0");
        this.add(score);
        	        
        GridBagConstraints constraint= new GridBagConstraints();
        constraint.insets = new Insets(4, 4, 4, 4);
        constraint.weightx = 1;
        constraint.weighty=1;
        
	    constraint.fill = GridBagConstraints.BOTH;
	    constraint.gridwidth=20;
        constraint.gridheight=20;
        constraint.gridx = 0;
        constraint.gridy = 0;
        layout.setConstraints(mainBoard, constraint);
        
        constraint.gridx = 21;
        constraint.gridy = 1;
        constraint.gridwidth=1;
        constraint.gridheight=6;
        layout.setConstraints(previewBoard, constraint);

	    constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridheight=1;
        constraint.gridy = 10;
        layout.setConstraints(score, constraint);

        constraint.gridheight=1;
        constraint.gridy = 19;
        layout.setConstraints(startPauseStop, constraint);
        setVisible(true);
        
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmExit();
			}
			private void confirmExit() {
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure to exit the game?",
						"Confirm Exit", JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
				    setDefaultCloseOperation(DISPOSE_ON_CLOSE);//yes
				    System.exit(0);
				} else {
				    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//cancel
				}
			}
		});
		
		startPauseStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.play();
				startPauseStop.setFocusable(false);
			}
		});
	}

	public void setController(GameController controller) {
		this.controller = controller;
	}
	@Override
	public void updateNextShape() {
		
	}
	@Override
	public void updateScore() {
		
	}
	@Override
	public void updateSpeed() {
		
	}
	@Override
	public void connect(GameController gameController) {
		this.controller = gameController;
		init();
	}
	
	@Override
	public void refresh(List<ShapePlacement> mainShapes) {
		mainBoard.setShapes(mainShapes);
		mainBoard.repaint();
	}
}
