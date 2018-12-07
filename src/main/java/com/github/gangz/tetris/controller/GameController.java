package com.github.gangz.tetris.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.gangz.tetris.shapes.ShapeFactory;

public class GameController {
	private GameUI output;
	private CollisionDetector collisionDetector;
	ShapePlacement activeShapePlacement;
	ShapePlacement borderShapePlacement;
	ShapePlacement existedBlockPlacement;
	
	boolean isRunning;
	public static final int MOVE_LEFT = 0;
	public static final int MOVE_RIGHT = 1;
	public static final int MOVE_DOWN = 2;
	public GameController(GameUI gameUI, CollisionDetector collisionDetector){
		//Link ui and controller bi-direction
		gameUI.connect(this);
		this.output = gameUI;
		
		this.collisionDetector = collisionDetector;
        createWall();
        isRunning = false;
	}

	/**
	 * We create a virtual wall around the game area, which 
	 * prevent the shape go out of the wall.
	 */
	private void createWall() {
		borderShapePlacement = new ShapePlacement(-1,-1);
        ShapeFactory shapeFactory = new ShapeFactory();
        borderShapePlacement.put(shapeFactory.makeWall(22,10));
	}
	
	/**
	 * Start game
	 * - if it is already started, it is no effect
	 * - a shape will appear in top of window
	 * - existed block is clean
	 * - notify external ui to update the shape;
	 */
	public void play()
	{
		if (isRunning) return;
 		isRunning = true;
		activeShapePlacement = new ShapePlacement(0, 0);
		createShape();
        existedBlockPlacement = new ShapePlacement(0,0); 
        existedBlockPlacement.put(new Shape());
        refresh();
	}

	/**
	 * When move the shape down, the active shape placement will move down 1 if no collison
	 * otherwise it will join with existedBlockPlacement
	 * if there are whole row, it will be eleminated
	 * if the elemnated shape is hang, it will fall down.
	 * if the game's existed shape already reach top of window, the game is over.
	 * a new shape will be created and appear on top of window
	 */
	public void moveDown() {
		if (!isRunning) return;
		if (collisionDetector.isCollision(activeShapePlacement, borderShapePlacement, MOVE_DOWN)||
			collisionDetector.isCollision(activeShapePlacement, existedBlockPlacement, MOVE_DOWN)){
			
			existedBlockPlacement.join(activeShapePlacement);
			existedBlockPlacement.eleminate(8);
			fallDownExistedShapePlacement();
			activeShapePlacement = new ShapePlacement(0, 0);
			createShape();
			refresh();
			checkGameOver();
			return;
		}
		activeShapePlacement.moveDown();
		refresh();
	}

	private void fallDownExistedShapePlacement() {
		if (existedBlockPlacement.size()==0) return;
        while(!collisionDetector.isCollision(existedBlockPlacement,borderShapePlacement,MOVE_DOWN))
                existedBlockPlacement.moveDown();
	}

	private boolean checkGameOver() {
		if (existedBlockPlacement.size()==0) return false;
		Cell c =existedBlockPlacement.getAt(existedBlockPlacement.size()-1);
        if (c.x == 0){
                output.notifyGameOver();
                isRunning=false;
                return true;
        }
        return false;
	}

	private void createShape() {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape activeShape = shapeFactory.make(new Random().nextInt(ShapeFactory.TYPE_NULL));
        activeShapePlacement.put(activeShape);
	}

	public void rotate() {
		if (!isRunning) return;
		activeShapePlacement.rotate();
		refresh();

	}

	public void moveRight() {
		if (!isRunning) return;
		if (collisionDetector.isCollision(activeShapePlacement, borderShapePlacement, MOVE_RIGHT)||
			collisionDetector.isCollision(activeShapePlacement, existedBlockPlacement, MOVE_RIGHT))
			return;		
		activeShapePlacement.moveRight();
		refresh();

	}

	public void moveLeft() {
		if (!isRunning) return;
		if (collisionDetector.isCollision(activeShapePlacement, borderShapePlacement, MOVE_LEFT)||
			collisionDetector.isCollision(activeShapePlacement, existedBlockPlacement, MOVE_LEFT))
			return;		
		activeShapePlacement.moveLeft();
		refresh();
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void refresh() {
		List<ShapePlacement> mainShapes = new ArrayList<>();
		mainShapes.add(activeShapePlacement);
        mainShapes.add(existedBlockPlacement);
		this.output.refresh(mainShapes);
	}
	
	public void tick() {
		moveDown();
	}

}
