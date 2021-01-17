package com.github.gangz.tetris;

import java.util.List;

import com.github.gangz.tetris.controller.Block;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.github.gangz.tetris.controller.CollisionDetector;
import com.github.gangz.tetris.controller.GameController;
import com.github.gangz.tetris.controller.GameUI;

@RunWith(MockitoJUnitRunner.class)
public class TestGameController {
	@Mock GameUI ui;
	@Mock CollisionDetector collisionDetector;
	GameController game;
	ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
	
	@Test
	public void play_game_will_create_a_block() {
		game = new GameController(ui,null);
		game.start();
		verify(ui).refresh(argument.capture());
		assertEquals(0,getExistBlock(0).size());
	}
	
	@Test
	public void moveDown_should_invoke_refresh() {
		when(collisionDetector.isCollision(any(), any(),any())).thenReturn(false);
		game = new GameController(ui,collisionDetector);
		game.start();
		verify(ui,times(1)).refresh(argument.capture());
		int prePos = getActiveBlock().x;
		game.moveDown();
		verify(ui,times(2)).refresh(argThat(new BlockListYMatcher(0,prePos+1)));
	}
	
	@Test
	public void moveDown_with_collision_should_join() {
		when(collisionDetector.isCollision(any(), any(),any())).thenReturn(true);
		game = new GameController(ui,collisionDetector);
		game.start();
		game.moveDown();
		verify(ui,times(2)).refresh(argument.capture());
		assertEquals(4,getExistBlock(1).size());
	}

	private Block getActiveBlock() {
		List blocks = argument.getValue();
		return (Block)(blocks.get(0));
	}
	
	private Block getExistBlock(int index) {
		List blocks = argument.getAllValues().get(index);
		return (Block)(blocks.get(1));
	}
	
	private static class BlockListYMatcher extends ArgumentMatcher<List<Block>> {
		private int index;
		private int x;
		public BlockListYMatcher(int index, int x) {
			this.index = index;
			this.x = x;	
		}
	    @Override
	    public boolean matches(Object argument) {
	        if (argument instanceof List) {
	        	List<Block> blockList = (List<Block>)argument;
	        	if (blockList.size()<=index) return false;
	        	Block block = blockList.get(index);
	        	if (block.x==x) return true;
	        }
	        return false;
	    }
	}	
}
