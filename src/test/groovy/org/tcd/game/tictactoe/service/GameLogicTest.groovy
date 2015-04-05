package org.tcd.game.tictactoe.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.tcd.game.tictactoe.domain.Game;
import org.tcd.game.tictactoe.domain.Level;
import org.tcd.game.tictactoe.domain.Move;
import org.tcd.game.tictactoe.domain.Player;
import org.tcd.game.tictactoe.domain.Position;


public class GameLogicTest {

	GameLogic logic;
	
	@Test
	public void testNextMoveFork() throws Exception {
		
		Game game = new Game(Level.HARD, Player.X, null);
		
		game.addMove(new Move(2,1, Player.X));
		game.addMove(new Move(2,2, Player.O));
		game.addMove(new Move(3,3, Player.X));
		game.addMove(new Move(2,3, Player.O));
		
		logic = new GameLogic(game);
		
		assertEquals(new Move(3, 1, Player.X), logic.nextMove());
		
	}

	@Test
	public void testNextMoveBlockFork() throws Exception {
		
		Game game = new Game(Level.HARD, Player.O, null);
		
		game.addMove(new Move(1,1, Player.X));
		game.addMove(new Move(2,2, Player.O));
		game.addMove(new Move(3,3, Player.X));
		
		logic = new GameLogic(game);
		
		assertEquals(new Move(1, 2, Player.O), logic.nextMove());
		
	}
	
	@Test
	public void testNextMoveOppositeCorner() throws Exception {
		
		Game game = new Game(Level.HARD, Player.O, null);
		
		game.addMove(new Move(1,1, Player.X));
		game.addMove(new Move(2,2, Player.O));
		game.addMove(new Move(3,2, Player.X));
		
		logic = new GameLogic(game);
		
		assertEquals(new Move(3, 3, Player.O), logic.nextMove());
		
	}
	
	@Test
	public void testTurn() throws Exception {
		
		Game game = emptyGame();
		logic = new GameLogic(game);
		assertEquals(Player.X, logic.turn());
		
		List<Move> moves = new ArrayList<Move>();
		moves.add(new Move( new Position(2,2), Player.X));
		game = new Game(null, moves, Level.HARD, Player.X, null);
		
		logic = new GameLogic(game);
		assertEquals(Player.O, logic.turn());
		
	}

	@Test
	public void testOpenPositions() {
		logic = new GameLogic(emptyGame());
		List<Position> positions = logic.getOpenPositions();
		assertEquals(9, positions.size());
	}
	
	@Test
	public void testOpenPositionsOneMissing() {
		Game game = emptyGame()
		game.addMove(new Move( new Position(2,2), Player.X))
		logic = new GameLogic(game);
		List<Position> positions = logic.getOpenPositions();
		assertEquals(8, positions.size());
	}
	
	@Test
	public void testIsOpen() {
		Game game = emptyGame()
		game.addMove(new Move( new Position(2,2), Player.X))
		logic = new GameLogic(game);
		
		assertTrue(logic.isOpen(new Position(3,3)));
		
		assertTrue(logic.isOpen(new Position(1,1)));
		
		assertFalse(logic.isOpen(new Position(2,2)));
		
	}
	
	private Game emptyGame() {
		return new Game(Level.HARD, Player.X, null);
	}
}
