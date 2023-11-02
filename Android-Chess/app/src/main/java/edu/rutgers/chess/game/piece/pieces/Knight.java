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
 * Class for the knight piece
 */
public class Knight extends Piece {
	private ArrayList<Space> validMoves;

	/**
	 * Constructor for the knight piece
	 *
	 * @param x     the x coordinate of the knight
	 * @param y     the y coordinate of the knight
	 * @param color the name of the knight
	 */
	public Knight(int x, int y, Color color, Board board){
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
	 * @param space the space the knight is moving to
	 * @return true if the move is valid, false otherwise
	 */
	@Override
	public boolean isMoveValid(Space space){
		return validMoves.contains(space);
	}

	/**
	 * Sets the valid moves for the knight
	 */
	public void setValidMoves(){
		validMoves.clear();

		int newX;
		int newY;
		Space space;

		// Checks if each of the possible 8 moves of the knight is valid
		for (int i = 0; i < 8; i++){
			switch (i) {
				case 0:
					newX = this.getX() - 2;
					newY = this.getY() + 1;
					if (newX < 0 || newY > 7) {
						break;
					}
					space = getBoard().getBoard().get(newX).get(newY);
					if (space.isOccupied()) {
						if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
					}
					validMoves.add(space);
					break;
				case 1:
					newX = this.getX() - 1;
					newY = this.getY() + 2;
					if (newX < 0 || newY > 7) {
						break;
					}
					space = getBoard().getBoard().get(newX).get(newY);
					if (space.isOccupied()) {
						if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
					}
					validMoves.add(space);
					break;
				case 2:
					newX = this.getX() + 1;
					newY = this.getY() + 2;
					if (newX > 7 || newY > 7) {
						break;
					}
					space = getBoard().getBoard().get(newX).get(newY);
					if (space.isOccupied()) {
						if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
					}
					validMoves.add(space);
					break;
				case 3:
					newX = this.getX() + 2;
					newY = this.getY() + 1;
					if (newX > 7 || newY > 7) {
						break;
					}
					space = getBoard().getBoard().get(newX).get(newY);
					if (space.isOccupied()) {
						if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
					}
					validMoves.add(space);
					break;
				case 4:
					newX = this.getX() + 2;
					newY = this.getY() - 1;
					if (newX > 7 || newY < 0) {
						break;
					}
					space = getBoard().getBoard().get(newX).get(newY);
					if (space.isOccupied()) {
						if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
					}
					validMoves.add(space);
					break;
				case 5:
					newX = this.getX() + 1;
					newY = this.getY() - 2;
					if (newX > 7 || newY < 0) {
						break;
					}
					space = getBoard().getBoard().get(newX).get(newY);
					if (space.isOccupied()) {
						if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
					}
					validMoves.add(space);
					break;
				case 6:
					newX = this.getX() - 1;
					newY = this.getY() - 2;
					if (newX < 0 || newY < 0) {
						break;
					}
					space = getBoard().getBoard().get(newX).get(newY);
					if (space.isOccupied()) {
						if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
					}
					validMoves.add(space);
					break;
				case 7:
					newX = this.getX() - 2;
					newY = this.getY() - 1;
					if (newX < 0 || newY < 0) {
						break;
					}
					space = getBoard().getBoard().get(newX).get(newY);
					if (space.isOccupied()) {
						if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
						break;
					}
					validMoves.add(space);
					break;
			}
		}
	}

	/**
	 * Moves the piece to the space
	 * @param space space to move to
	 * @param player player moving the piece
	 * @param move the move the player is making
	 * @return true if the move was valid, false if the move was invalid
	 */
	@Override
	public boolean movePiece(Space space, Player player, String move){
		setValidMoves();
		if (!validMoves.contains(space)) return false;

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
	 * Returns the valid moves for the knight
	 * @return valid moves for the knight
	 */
	@Override
	public ArrayList<Space> getValidMoves(){
		return validMoves;
	}

	@SuppressLint("UseCompatLoadingForDrawables")
	@Override
	public Drawable getPieceImage(@NotNull Context context){
		return getColor() == Color.WHITE ? context.getResources().getDrawable(R.drawable.knight_white) : context.getResources().getDrawable(R.drawable.knight_black);
	}
}

