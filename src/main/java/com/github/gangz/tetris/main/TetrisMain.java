package com.github.gangz.tetris.main;

import com.github.gangz.tetris.controller.CollisionDetector;
import com.github.gangz.tetris.controller.GameController;
import com.github.gangz.tetris.ui.SwingTetrisUI;

public class TetrisMain {

	public static void main(String[] args) {
		new GameController(new SwingTetrisUI(),new CollisionDetector());
	}
}
