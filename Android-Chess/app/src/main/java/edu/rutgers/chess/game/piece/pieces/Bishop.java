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
 * Class for the bishop piece
 */
public class Bishop extends Piece {
	private ArrayList<Space> validMoves;

	/**
	 * Constructor for the bishop piece
	 *
	 * @param x     x coordinate of the bishop
	 * @param y     y coordinate of the bishop
	 * @param color name of the piece
	 */
	public Bishop(int x, int y, Color color, Board board){
		this.setX(x);
		this.setY(y);
		this.setColor(color);
		this.setBoard(board);
		this.setSpace(board.getBoard().get(x).get(y));
		this.validMoves = new ArrayList<>();
		setValidMoves();
	}

	/**
	 * Checks if the move is valid
	 * @param space space to move to
	 * @return true if the move is valid, false otherwise
	 */
	@Override
	public boolean isMoveValid(Space space){
		return validMoves.contains(space);
	}

	/**
	 * Sets the valid moves for the bishop
	 */
	public void setValidMoves(){
		validMoves.clear();

		int newX;
		int newY;
		Space space;

		// Checks for valid moves in the 4 directions that the bishop could go in
		for(int i = 0; i < 4; i++){
			switch (i) {
				case 0:
					for (int j = 1; ; j++) {
						newX = this.getX() - j;
						newY = this.getY() + j;
						if (newX < 0 || newY > 7) {
							break;
						}
						space = getBoard().getBoard().get(newX).get(newY);
						if (space.isOccupied()) {
							if (space.getPiece().getColor() != this.getColor()) {
								validMoves.add(space);
							}
							break;
						}
						validMoves.add(space);
					}
					break;
				case 1:
					for (int j = 1; ; j++) {
						newX = this.getX() + j;
						newY = this.getY() + j;
						if (newX > 7 || newY > 7) {
							break;
						}
						space = getBoard().getBoard().get(newX).get(newY);
						if (space.isOccupied()) {
							if (space.getPiece().getColor() != this.getColor()) {
								validMoves.add(space);
							}
							break;
						}
						validMoves.add(space);
					}
					break;
				case 2:
					for (int j = 1; ; j++) {
						newX = this.getX() + j;
						newY = this.getY() - j;
						if (newY < 0 || newX > 7) {
							break;
						}
						space = getBoard().getBoard().get(newX).get(newY);
						if (space.isOccupied()) {
							if (space.getPiece().getColor() != this.getColor()) {
								validMoves.add(space);
							}
							break;
						}
						validMoves.add(space);
					}
					break;
				case 3:
					for (int j = 1; ; j++) {
						newX = this.getX() - j;
						newY = this.getY() - j;
						if (newY < 0 || newX < 0) {
							break;
						}
						space = getBoard().getBoard().get(newX).get(newY);
						if (space.isOccupied()) {
							if (space.getPiece().getColor() != this.getColor()) {
								validMoves.add(space);
							}
							break;
						}
						validMoves.add(space);
					}
					break;
			}
		}
	}

	/**
	 * Moves the bishop to the space
	 * @param space space to move to
	 * @param player player that is moving the piece
	 * @param move move that is being made
	 * @return true if the move was successful, false otherwise
	 */
	@Override
	public boolean movePiece(Space space, Player player, String move){
		setValidMoves();

		if (space == null || !validMoves.contains(space)) return false;
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
	 * Gets the valid moves for the bishop
	 * @return the valid moves for the bishop
	 */
	@Override
	public ArrayList<Space> getValidMoves(){
		return validMoves;
	}

	@SuppressLint("UseCompatLoadingForDrawables")
	@Override
	public Drawable getPieceImage(@NotNull Context context){
		return getColor() == Color.WHITE ? context.getResources().getDrawable(R.drawable.bishop_white) : context.getResources().getDrawable(R.drawable.bishop_black);
	}
}
