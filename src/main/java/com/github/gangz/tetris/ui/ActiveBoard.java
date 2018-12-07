package com.github.gangz.tetris.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import com.github.gangz.tetris.controller.GameController;

public class ActiveBoard extends Board implements ActionListener {
	private static final long serialVersionUID = 1L;
	Timer timer;
	GameController controller;
	
	public ActiveBoard(GameController controller, int width, int height) {
		super(width,height);
		setFocusable(true);
		this.controller = controller;
		timer = new Timer(1000, this);
		timer.start();
		addKeyListener(new TAdapter(controller));
	}
	

	public void actionPerformed(ActionEvent e) {
		controller.tick();
	}
	
	class TAdapter extends KeyAdapter {
		GameController controller;

		TAdapter(GameController vm) {
			this.controller = vm;
		}

		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();
			if (keycode == 'p' || keycode == 'P') {
				controller.pause();
				return;
			}
			switch (keycode) {
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
				controller.rotate();
				break;
			case KeyEvent.VK_SPACE:
				break;
			}

		}
	}


}
