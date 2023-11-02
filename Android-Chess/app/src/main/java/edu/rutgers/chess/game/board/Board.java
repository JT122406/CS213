package edu.rutgers.chess.game.board;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {

	private static ArrayList<ArrayList<Space>> board;

	/**
	 * Array of what space is at what location in the board
	 */
	public static String [][] nameBoard = {
			{"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"},
			{"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
			{"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"},
			{"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5"},
			{"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4"},
			{"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"},
			{"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
			{"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"},
	};

	/**
	 * Constructor for Board
	 */
	public Board() {
		//Setup Board
		board = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			board.add(new ArrayList<>());
			for (int j = 0; j < 8; j++)
				board.get(i).add(new Space(i, j, false, null));
		}
	}

	/**
	 * Returns the board
	 * @return the board
	 */
	public ArrayList<ArrayList<Space>> getBoard() {
		return board;
	}

}
