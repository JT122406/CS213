package edu.rutgers.chess.game.piece;

import android.content.Context;
import android.graphics.drawable.Drawable;
import edu.rutgers.chess.game.board.Board;
import edu.rutgers.chess.game.board.Space;
import edu.rutgers.chess.game.player.Color;
import edu.rutgers.chess.game.player.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Class for the piece Object
 */
public class Piece implements Serializable {

	private int x;
	private int y;
	Space space;
	private Color color;
	public boolean hasMoved;
	private Board board;
	/**
	 * Returns the name of the piece
	 * @return color of the piece
	 */
	public Color getColor(){
		return color;
	}

	/**
	 * Sets the color of the piece
	 * @param color of the piece
	 */
	public void setColor(Color color){
		this.color = color;
	}

	/**
	 * gets the space the piece is on
	 * @return space
	 */
	public Space getSpace(){
		return space;
	}

	/**
	 * sets the space the piece is on
	 * @param space the space the piece is on
	 */
	public void setSpace(Space space){
		this.space = space;
	}

	/**
	 * gets the x coordinate of the piece
	 * @return x
	 */
	public int getX(){
		return this.x;
	}

	/**
	 * gets the y coordinate of the piece
	 * @return y
	 */
	public int getY(){
		return this.y;
	}

	/**
	 * sets the x coordinate of the piece
	 * @param x the x coordinate of the piece
	 */
	public void setX(int x){
		this.x = x;
	}

	/**
	 * sets the y coordinate of the piece
	 * @param y the y coordinate of the piece
	 */
	public void setY(int y){
		this.y = y;
	}


	/**
	 * gets the valid moves for the piece
	 * @return ArrayList of valid moves
	 */
	public ArrayList<Space> getValidMoves(){
		return new ArrayList<>();
	}

	/**
	 * moves the piece to the space
	 * @param space space to move to
	 * @param player player moving the piece
	 * @param move the move the player is making
	 * @return true if the move was successful
	 */
	public boolean movePiece(Space space, Player player, String move){
		return false;
	}

	/**
	 * gets if the piece has moved
	 * @return if the piece has moved
	 */
	public boolean getHasMoved(){
		return hasMoved;
	}

	/**
	 * sets if the piece has moved
	 * @param bool if the piece has moved
	 */
	public void setHasMoved(boolean bool) {
		hasMoved = bool;
	}

	/**
	 * Checks if the move is valid
	 * @param space the space the piece is moving to
	 * @return true if the move is valid, false otherwise
	 */
	public boolean isMoveValid(Space space){
		return false;
	}

	/**
	 * gets the image of the piece
	 * @return image of the piece
	 */
	public Drawable getPieceImage(@NotNull Context context){
		return null;
	}

	/**
	 * gets the board the piece is on
	 * @return board the piece is on
	 */
	public Board getBoard(){
		return board;
	}

	/**
	 * sets the board the piece is on
	 * @param board the piece is on
	 */
	public void setBoard(Board board){
		this.board = board;
	}
}
