package com.github.gangz.tetris.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.gangz.tetris.shapes.ShapeFactory;

public class GameController implements CommandReciever{
	private GameUI output;
	private CollisionDetector collisionDetector;
	Block activeBlock;
	Block nextBlock;
	Block existedBlock;
	Block borderBlock;

	boolean isRunning;
	public static final int MOVE_LEFT = 0;
	public static final int MOVE_RIGHT = 1;
	public static final int MOVE_DOWN = 2;
	private ShapeFactory shapeFactory= new ShapeFactory();

	public GameController(GameUI gameUI){
		this(gameUI,new CollisionDetector());
	}

	public GameController(GameUI gameUI, CollisionDetector collisionDetector){
		//Link ui and controller bi-direction
		gameUI.connect(this);
		this.output = gameUI;
		this.collisionDetector = collisionDetector;
		createEmptyActiveBlock();
		createEmptyExistedBlock();
		createNextBlock();
		createWall();
		this.refresh();
        isRunning = false;
	}

	private void createEmptyActiveBlock() {
		activeBlock = new Block();
	}
	private void createEmptyExistedBlock() {
		existedBlock = new Block();
	}

	/**
	 * We create a virtual wall around the game area, which 
	 * prevent the shape go out of the wall.
	 */
	private void createWall() {
		borderBlock = new Block(-1,-1,
				shapeFactory.makeWall(22,10));
	}
	

	@Override
	public void start()
	{
		if (isRunning) return;
 		isRunning = true;
		createActiveBlock();
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
	@Override
	public void moveDown() {
		if (!isRunning) return;
		if (collisionDetector.isCollision(activeBlock, borderBlock, MOVE_DOWN)||
			collisionDetector.isCollision(activeBlock, existedBlock, MOVE_DOWN)){
			
			existedBlock.join(activeBlock);
			existedBlock.eliminate(8);
			fallDownExistedShapePlacement();
			createActiveBlock();
			refresh();
			checkGameOver();
			return;
		}
		activeBlock.moveDown();
		refresh();
	}

	private void fallDownExistedShapePlacement() {
		if (existedBlock.size()==0) return;
        while(!collisionDetector.isCollision(existedBlock, borderBlock,MOVE_DOWN))
                existedBlock.moveDown();
	}

	private boolean checkGameOver() {
		if (existedBlock.size()==0) return false;
		Cell c = existedBlock.getAt(existedBlock.size()-1);
        if (c.x == 0){
                output.notifyGameOver();
                isRunning=false;
                return true;
        }
        return false;
	}

	private void createActiveBlock() {
		activeBlock = nextBlock;
		createNextBlock();
	}

	private void createNextBlock() {
		nextBlock = new Block(0, 0,shapeFactory.make(new Random().nextInt(ShapeFactory.NULL)));
	}

	@Override
	public void rotate() {
		if (!isRunning) return;
		activeBlock.rotate();
		refresh();

	}
	@Override
	public void moveRight() {
		if (!isRunning) return;
		if (collisionDetector.isCollision(activeBlock, borderBlock, MOVE_RIGHT)||
			collisionDetector.isCollision(activeBlock, existedBlock, MOVE_RIGHT))
			return;		
		activeBlock.moveRight();
		refresh();

	}

	@Override
	public void moveLeft() {
		if (!isRunning) return;
		if (collisionDetector.isCollision(activeBlock, borderBlock, MOVE_LEFT)||
			collisionDetector.isCollision(activeBlock, existedBlock, MOVE_LEFT))
			return;		
		activeBlock.moveLeft();
		refresh();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void refresh() {
		List<Block> mainShapes = new ArrayList<>();
		mainShapes.add(activeBlock);
        mainShapes.add(existedBlock);
		this.output.refresh(mainShapes);
		this.output.updateNextShape(nextBlock);
		this.output.updateScore();
	}

	@Override
	public void tick() {
		moveDown();
	}

}
