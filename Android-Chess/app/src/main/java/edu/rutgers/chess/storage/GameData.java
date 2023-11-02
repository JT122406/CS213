package edu.rutgers.chess.storage;

import edu.rutgers.chess.game.board.Board;
import edu.rutgers.chess.game.player.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable {

	public static ArrayList<GameData> games = new ArrayList<>();

	private String name;
	private Board board;
	private Player white;
	private Player black;

	public GameData(String name, Board board, Player white, Player black) {
		this.name = name;
		this.board = board;
		this.white = white;
		this.black = black;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Board getBoard() {
		return board;
	}

	public Player getWhite() {
		return white;
	}

	public Player getBlack() {
		return black;
	}

}
