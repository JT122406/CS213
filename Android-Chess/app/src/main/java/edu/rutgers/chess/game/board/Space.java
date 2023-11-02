package edu.rutgers.chess.game.board;

import edu.rutgers.chess.game.piece.Piece;

import java.io.Serializable;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * This class represents a space on the board
 */
public class Space implements Serializable {
	private boolean isOccupied;
	private final int x;
	private final int y;
	private Piece piece;
	private final String name;

	/**
	 * Default constructor
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param isOccupied whether the space is occupied
	 * @param piece the piece occupying the space
	 */
	public Space(int x, int y, boolean isOccupied, Piece piece) {
		this.x = x;
		this.y = y;
		this.isOccupied = isOccupied;
		this.piece = piece;
		this.name = Board.nameBoard[x][y];
	}

	/**
	 * Returns whether the space is occupied
	 * @return isOccupied
	 */
	public boolean isOccupied() {
		return isOccupied;
	}

	/**
	 * Sets whether the space is occupied
	 * @param isOccupied sets isOccupied
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	/**
	 * Returns the x coordinate
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y coordinate
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the piece occupying the space
	 * @return piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * Sets the piece occupying the space
	 * @param piece sets piece
	 */
	public void setPiece(Piece piece) {
		this.isOccupied = piece != null;
		this.piece = piece;
	}

	/**
	 * Returns the name of the piece
	 * @return name
	 */
	public String getName(){
		return name;
	}

}
