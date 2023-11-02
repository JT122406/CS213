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
 * Class for the king piece
 */
public class King extends Piece {
	public boolean isInCheck;
	public boolean isMated;

	private ArrayList<Space> validMoves;

	/**
	 * Constructor for the king piece
	 *
	 * @param x     the x coordinate of the king
	 * @param y     the y coordinate of the king
	 * @param color the name of the king
	 */
	public King(int x, int y, Color color, Board board) {
		this.setX(x);
		this.setY(y);
		this.setColor(color);
		this.setBoard(board);
		this.setHasMoved(false);
		this.validMoves = new ArrayList<>();
		this.setSpace(board.getBoard().get(x).get(y));
	}

	/**
	 * Checks if the move is valid
	 * @param space the space the king wants to move to
	 * @return true if the move is valid, false if it is not
	 */
	@Override
	public boolean isMoveValid(Space space){
		return validMoves.contains(space);
	}
	/*
	 *checks if player can castle
	 *@param player the player making the move
	 */
	public void canCastle(Player player){
		if (this.hasMoved) return;

		for(int i = 0; i < player.pieces.size(); i++){
			if (player.pieces.get(i) instanceof Rook){
				boolean castle = true;
				Piece rook = player.pieces.get(i);
				if (!rook.hasMoved){
					Space rookSpace = rook.getSpace();
					Space kingSpace = this.getSpace();

					//king cannot casting if any squares on his way to castle are being attacked by opponent or
					//if pieces are in the way
					if (rookSpace.getY() < kingSpace.getY()){
						Space cSpace1 = getBoard().getBoard().get(this.getX()).get(this.getY() - 1);
						Space cSpace2 = getBoard().getBoard().get(this.getX()).get(this.getY() - 2);

						if(cSpace1.isOccupied() || cSpace2.isOccupied()) continue;

						//checks if opponent can attack castle spaces
						for (int j = 0; j < player.getOpp().pieces.size(); j++){
							for (int e = 0; e < player.getOpp().pieces.get(j).getValidMoves().size(); e++){
								if (player.getOpp().pieces.get(j).getValidMoves().contains(cSpace1)||player.getOpp().pieces.get(j).getValidMoves().contains(cSpace2)){
									castle = false;
								}
							}
						}
						if (castle){
							validMoves.add(cSpace2);
						}
					}
					else if (rookSpace.getY() > kingSpace.getY()){
						Space cSpace1 = getBoard().getBoard().get(this.getX()).get(this.getY() + 1);
						Space cSpace2 = getBoard().getBoard().get(this.getX()).get(this.getY() + 2);

						if(cSpace1.isOccupied() || cSpace2.isOccupied()) continue;

						//checks if opponent can attack castle spaces
						for (int j = 0; j < player.getOpp().pieces.size(); j++){
							for (int e = 0; e < player.getOpp().pieces.get(j).getValidMoves().size(); e++){
								if (player.getOpp().pieces.get(j).getValidMoves().contains(cSpace1)||player.getOpp().pieces.get(j).getValidMoves().contains(cSpace2)){
									castle = false;
								}
							}
						}
						if (castle){
							validMoves.add(cSpace2);
						}
					}
				}
			}
		}
	}

	/**
	 * Sets the valid moves for the king
	 */
	public void setValidMoves(Player player){
		validMoves.clear();
		int newX;
		int newY;
		Space space;

		// Checks for valid moves in the 8 directions that the king can move
		for (int i = 0; i < 8; i++){
			switch (i) {
				case 0:
					newX = this.getX() - 1;
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
					break;
				case 1:
					newX = this.getX() + 1;
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
					break;
				case 2:
					newX = this.getX();
					newY = this.getY() + 1;
					if (newY > 7) {
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
					newX = this.getX();
					newY = this.getY() - 1;
					if (newY < 0) {
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
					newX = this.getX() - 1;
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
				case 5:
					newX = this.getX() + 1;
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
				case 6:
					newX = this.getX() + 1;
					newY = this.getY() - 1;
					if (newY < 0 || newX > 7) {
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
					newX = this.getX() - 1;
					newY = this.getY() - 1;
					if (newY < 0 || newX < 0) {
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
		canCastle(player);
	}

	/**
	 * Moves the king to the space
	 * @param space the space the king wants to move to
	 * @param player the player that is moving the king
	 * @param move the move the player wants to make
	 * @return true if the move was successful, false if it was not
	 */
	@Override
	public boolean movePiece(Space space, Player player, String move){
		setValidMoves(player);
		if (!validMoves.contains(space)) return false;

		var board = getBoard().getBoard();
		board.get(this.getX()).get(this.getY()).setOccupied(false);

		//if player is castling
		boolean castling = this.getSpace().getY() - space.getX() == 2 || this.getSpace().getY() - space.getY() == -2;

		this.setSpace(space);
		this.setX(space.getX());
		this.setY(space.getY());
		setValidMoves(player);
		board.get(this.getX()).get(this.getY()).setPiece(this);
		board.get(this.getX()).get(this.getY()).setOccupied(true);

		if (castling){
			//finds castling rook
			for (int i = 0; i < player.pieces.size(); i++)
				if (player.pieces.get(i) instanceof Rook){
					Rook rook = (Rook)player.pieces.get(i);
					if (rook.getY() - space.getY() < 0){
						rook.getSpace().setOccupied(false);
						rook.setSpace(getBoard().getBoard().get(rook.getX()).get(3));
						rook.setY(rook.getSpace().getY());
						board.get(rook.getX()).get(rook.getY()).setPiece(rook);
						board.get(rook.getX()).get(rook.getY()).setOccupied(true);
					}
					else {
						rook.getSpace().setOccupied(false);
						rook.setSpace(getBoard().getBoard().get(rook.getX()).get(5));
						rook.setY(rook.getSpace().getY());
						board.get(rook.getX()).get(rook.getY()).setPiece(rook);
						board.get(rook.getX()).get(rook.getY()).setOccupied(true);
					}
				}

		}

		return true;
	}

	/**
	 * gets valid moves
	 * @return the array of valid moves
	 */
	@Override
	public ArrayList<Space> getValidMoves(){
		return validMoves;
	}

	@SuppressLint("UseCompatLoadingForDrawables")
	@Override
	public Drawable getPieceImage(@NotNull Context context){
		return getColor() == Color.WHITE ? context.getResources().getDrawable(R.drawable.king_white) : context.getResources().getDrawable(R.drawable.king_black);
	}
}
