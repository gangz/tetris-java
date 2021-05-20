/**
 * The main UI of Tetris game 
 * Gang ZHANG(gangz@emergentdesign.cn)
 * All rights reserved.
 */

package com.github.gangz.tetris.ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import com.github.gangz.tetris.controller.Block;
import com.github.gangz.tetris.controller.CommandReciever;
import com.github.gangz.tetris.controller.GameUI;


public class SwingUI extends JFrame implements GameUI {
	private static final int WINDOW_HEIGHT = 600;
	private static final int WINDOW_WIDTH = 400;
	private static final long serialVersionUID = -5339939426923342316L;
	public SwingUI() {
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
			commandReciever.start();
		} else {
		}
	}

    ActiveBoard mainBoard;
    Board previewBoard;
    JLabel score;
    JButton startPauseStop;
	private CommandReciever commandReciever;
    
	public void init() {
		initGlobalFontSetting(new Font("SimHei", Font.PLAIN, 20));
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("Tetris");
	    setLayout(new BorderLayout(5,5));
		createControlPanel();
		createMainBoard();
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
				commandReciever.start();
				startPauseStop.setFocusable(false);
			}
		});
	}

	private void createMainBoard() {
		mainBoard = new ActiveBoard(commandReciever,8,20);
		mainBoard.setPreferredSize(new Dimension(240,600));
		this.add(mainBoard);
	}

	private void createControlPanel() {
		previewBoard = new Board(4,4);
		startPauseStop = new JButton("Start");
		score= new JLabel("score: 0");

		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.setPreferredSize(new Dimension(150,120));
		this.add(controlPanel,BorderLayout.EAST);
		previewBoard.setPreferredSize(new Dimension(120,120));
		controlPanel.add(previewBoard);
		score.setPreferredSize(new Dimension(120,40));
		controlPanel.add(score);

		startPauseStop.setPreferredSize(new Dimension(120,40));
		controlPanel.add(startPauseStop);
	}

	@Override
	public void updateNextShape(Block nextBlock) {
		previewBoard.setShapes(Arrays.asList(nextBlock));
		previewBoard.repaint();
	}

	@Override
	public void updateScore() {
		
	}

	@Override
	public void connect(CommandReciever commandReciever) {
		this.commandReciever = commandReciever;
		init();
	}
	
	@Override
	public void refresh(List<Block> mainShapes) {
		mainBoard.setShapes(mainShapes);
		mainBoard.repaint();
	}

	public static void initGlobalFontSetting(Font fnt){
		FontUIResource fontRes = new FontUIResource(fnt);
		for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource)
				UIManager.put(key, fontRes);
		}
	}

}
