package com.github.gangz.tetris.controller;

import java.util.List;

public interface GameUI {
	void updateNextShape();
	void updateScore();
	void updateSpeed();
	void notifyGameOver();
	void connect(CommandReciever gameController);
	void refresh(List<Block> mainShapes);
}
