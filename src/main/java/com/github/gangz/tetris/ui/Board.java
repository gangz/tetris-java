package com.github.gangz.tetris.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.github.gangz.tetris.controller.Cell;
import com.github.gangz.tetris.controller.ShapePlacement;

public class Board extends JPanel {
	private static final long serialVersionUID = 1L;
	int width = 8;
	int height = 20;
	Color colors[] = { new Color(204, 102, 102), new Color(102, 204, 102),
			new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), new Color(102, 204, 204),
			new Color(218, 170, 0) };
	private List<ShapePlacement> shapes;

	public Board(int width, int height) {
		setBackground(Color.BLACK);
		setFocusable(false);
		this.height  = height;
		this.width = width;
	}


	int squareWidth() {
		return (int) getSize().getWidth() / width;
	}

	int squareHeight() {
		return (int) getSize().getHeight() / height;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Dimension size = getSize();
		int boardTop = (int) size.getHeight() - height * squareHeight();
		if (shapes==null) return;
		for (ShapePlacement shape:shapes) {
			for (int i=0;i<shape.size();i++) {
				Cell cell = shape.getAt(i);
				int index = new Random().nextInt(colors.length);
				index = 1;
				drawSquare(g, 0 + cell.y * squareWidth(), boardTop + cell.x * squareHeight(), index);
			}
		}
	}

	private void drawSquare(Graphics g, int x, int y, int colorIndex) {
		Color color = colors[colorIndex];
		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
	}

	public void setShapes(List<ShapePlacement> shapes) {
		this.shapes = shapes;
	}

}