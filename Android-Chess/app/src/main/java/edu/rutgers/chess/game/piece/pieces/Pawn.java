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
 * Class for the pawn piece
 */
public class Pawn extends Piece {
	private boolean firstMove;
	private ArrayList<Space> validMoves;

	/**
	 * Constructor for the pawn piece
	 *
	 * @param x     the x coordinate of the pawn
	 * @param y     the y coordinate of the pawn
	 * @param color the name of the pawn
	 */
	public Pawn(int x, int y, Color color, Board board){
		this.setX(x);
		this.setY(y);
		this.setColor(color);
		this.setBoard(board);
		this.setSpace(board.getBoard().get(x).get(y));
		this.validMoves = new ArrayList<>();
		this.firstMove = true;
		setValidMoves();
	}

	/**
	 * Checks if the move is valid
	 * @param space the space the pawn is moving to
	 * @return true if the move is valid, false otherwise
	 */
	@Override
	public boolean isMoveValid(Space space){
		return validMoves.contains(space);
	}


	/**
	 * n/a
	 */
	public void canEnPassant(){

	}

	//Maybe add this to the player.Player class

	/**
	 * n/a
	 */
	public void Promote(){

	}

	// if the pawn can capture a piece, the spaces it could capture is added to validMoves

	/**
	 * Can capture
	 * @param player to capture
	 */
	public void canCapture(Color player){
		int newX;
		int newY;
		int newY2;
		if (player == Color.WHITE){
			newX = this.getX() - 1;
		}
		else {
			newX = this.getX() + 1;
		}


		if(this.getY() > 0){
			newY = this.getY() - 1;
			Space space = getBoard().getBoard().get(newX).get(newY);
			if (space.isOccupied())
				if (space.getPiece().getColor() != this.getColor())validMoves.add(space);
		}

		if(this.getY() < 7){
			newY2 = this.getY() + 1;
			Space space = getBoard().getBoard().get(newX).get(newY2);
			if (space.isOccupied())
				if (space.getPiece().getColor() != this.getColor()) validMoves.add(space);
		}

		canEnPassant();
	}

	// Since the direction that a pawn depends on the player, 2 cases are considered

	/**
	 * Sets the valid moves for the pawn
	 */
	public void setValidMoves(){
		validMoves.clear();

		// if the pawn is at the end of the board, it has no moves and should promote
		if (this.getX() == 7 || this.getX() == 0){
			return;
		}

		int newX;
		int newY;

		Color player = getColor();
		// White player
		if (player == Color.WHITE) {
			//System.out.println("player.equals(wp)");
			newX = this.getX() - 1;
			newY = this.getY();

			Space space = getBoard().getBoard().get(newX).get(newY);
			if (!space.isOccupied()){
				validMoves.add(space);
			}

			if (firstMove){
				newX = this.getX() - 2;
				Space space2 = getBoard().getBoard().get(newX).get(newY);
				if(!space.isOccupied()){
					validMoves.add(space2);
				}
			}
		}
		// Black player
		if (player == Color.BLACK) {
			newX = this.getX() + 1;
			newY = this.getY();

			Space space = getBoard().getBoard().get(newX).get(newY);
			if (!space.isOccupied()){
				validMoves.add(space);
			}

			if (firstMove){
				newX = this.getX() + 2;
				Space space2 = getBoard().getBoard().get(newX).get(newY);
				if(!space.isOccupied()){
					validMoves.add(space2);
				}
			}
		}
		canCapture(player);
	}

	/**
	 * Moves the pawn to the specified space
	 * @param space the space the pawn is moving to
	 * @param player the player that is moving the pawn
	 * @param move the move that is being made
	 * @return true if the move is valid, false otherwise
	 */
	@Override
	public boolean movePiece(Space space, Player player, String move){
		setValidMoves();

		if (!validMoves.contains(space)) return false;
		getBoard().getBoard().get(this.getX()).get(this.getY()).setOccupied(false);

		this.setSpace(space);
		this.setX(space.getX());
		this.setY(space.getY());
		setValidMoves();
		this.firstMove = false;
		getBoard().getBoard().get(this.getX()).get(this.getY()).setPiece(this);
		getBoard().getBoard().get(this.getX()).get(this.getY()).setOccupied(true);

		if (this.getX() == 0 || this.getX() == 7){
			//if a promotion piece is not specified, default case is queen
			char newPiece = 'Q';
			if (move.length() >= 7){
				newPiece = move.charAt(6);
			}

			player.promote(newPiece, this);
		}

		return true;
	}

	/**
	 * Gets the valid moves for the pawn
	 * @return the valid moves for the pawn
	 */
	@Override
	public ArrayList<Space> getValidMoves(){
		return validMoves;
	}

	@SuppressLint("UseCompatLoadingForDrawables")
	@Override
	public Drawable getPieceImage(@NotNull Context context){
		return getColor() == Color.WHITE ? context.getResources().getDrawable(R.drawable.pawn_white) : context.getResources().getDrawable(R.drawable.pawn_black);
	}
}
