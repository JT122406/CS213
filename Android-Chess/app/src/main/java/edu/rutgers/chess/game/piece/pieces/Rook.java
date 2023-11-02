package edu.rutgers.chess.game.piece.pieces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import edu.rutgers.chess.R;
import edu.rutgers.chess.game.board.Board;
import edu.rutgers.chess.game.board.Space;
import edu.rutgers.chess.game.piece.Piece;
import edu.rutgers.chess.game.player.Color;
import edu.rutgers.chess.game.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Class for the rook piece
 */
public class Rook extends Piece {
	private ArrayList<Space> validMoves;

	/**
	 * Constructor for the rook piece
	 *
	 * @param x     the x coordinate of the rook
	 * @param y     the y coordinate of the rook
	 * @param color the name of the rook
	 */
	public Rook(int x, int y, Color color, Board board){
		this.setX(x);
		this.setY(y);
		this.setColor(color);
		this.setBoard(board);
		this.hasMoved = false;
		this.setSpace(board.getBoard().get(x).get(y));
		this.validMoves = new ArrayList<>();
		setValidMoves();
	}

	/**
	 * Checks if the move is valid
	 * @param space the space the rook is moving to
	 * @return true if the move is valid, false otherwise
	 */
	@Override
	public boolean isMoveValid(Space space){
		return validMoves.contains(space);
	}

	/**
	 * Sets the valid moves for the rook
	 */
	public void setValidMoves(){
		validMoves.clear();

		int newX;
		int newY;
		Space space;

		// Checks for valid moves in the 4 directions that the rook could go in
		for(int i = 0; i < 4; i++){
			switch (i) {
				case 0:
					for (int j = 1; ; j++) {
						newX = this.getX() - j;
						newY = this.getY();
						if (newX < 0) {
							break;
						}
						space = getBoard().getBoard().get(newX).get(newY);
						if (space.isOccupied()) {
							if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
						}
						validMoves.add(space);
					}
					break;
				case 1:
					for (int j = 1; ; j++) {
						newX = this.getX() + j;
						newY = this.getY();
						if (newX > 7) {
							break;
						}
						space = getBoard().getBoard().get(newX).get(newY);
						if (space.isOccupied()) {
							if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
						}
						validMoves.add(space);
					}
					break;
				case 2:
					for (int j = 1; ; j++) {
						newX = this.getX();
						newY = this.getY() + j;
						if (newY > 7) {
							break;
						}
						space = getBoard().getBoard().get(newX).get(newY);
						if (space.isOccupied()) {
							if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
						}
						validMoves.add(space);
					}
					break;
				case 3:
					for (int j = 1; ; j++) {
						newX = this.getX();
						newY = this.getY() - j;
						if (newY < 0) {
							break;
						}
						space = getBoard().getBoard().get(newX).get(newY);
						if (space.isOccupied()) {
							if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
						}
						validMoves.add(space);
					}
					break;
			}

		}
	}

	/**
	 * Moves the rook to the space
	 * @param space the space the rook is moving to
	 * @param player the player that is moving the rook
	 * @param move the move that is being made
	 * @return true if the move was successful, false otherwise
	 */
	public boolean movePiece(Space space, Player player, String move){
		setValidMoves();
		if (!validMoves.contains(space)){
			//System.out.print("Invalid move, try again");
			return false;
		}
		var board = getBoard().getBoard();
		board.get(this.getX()).get(this.getY()).setOccupied(false);

		this.setSpace(space);
		this.setX(space.getX());
		this.setY(space.getY());
		setValidMoves();
		board.get(this.getX()).get(this.getY()).setPiece(this);
		board.get(this.getX()).get(this.getY()).setOccupied(true);

		return true;
	}

	/**
	 * Gets the valid moves for the rook
	 * @return the valid moves for the rook
	 */
	@Override
	public ArrayList<Space> getValidMoves(){
		return validMoves;
	}

	/**
	 * gets the image of the piece
	 * @return image of the piece
	 */
	@SuppressLint("UseCompatLoadingForDrawables")
	@Override
	public Drawable getPieceImage(@NotNull Context context){
		return getColor() == Color.WHITE ? context.getResources().getDrawable(R.drawable.rook_white) : context.getResources().getDrawable(R.drawable.rook_black);
	}
}

