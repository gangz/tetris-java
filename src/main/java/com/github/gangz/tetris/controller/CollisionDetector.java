package com.github.gangz.tetris.controller;

public class CollisionDetector {

	public boolean isCollision(Block moveObj,
                               Block stillObj, Integer direction) {

        int offset[][]={{0,-1},{0,1},{1,0}};
        int index = direction-GameController.MOVE_LEFT;

        for (int i=0;i<moveObj.size();i++)
        {
                Cell cellMove = moveObj.getAt(i);
                for (int j=0;j<stillObj.size();j++){
                        Cell cellStill = stillObj.getAt(j);
                        if (cellMove.x+offset[index][0] == cellStill.x &&
                                cellMove.y+offset[index][1]  == cellStill.y)
                                return true;
                }
        }
        return false;
	}


}
