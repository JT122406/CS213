package edu.rutgers.chess.game.player;

import edu.rutgers.chess.game.board.Space;
import edu.rutgers.chess.game.piece.Piece;
import edu.rutgers.chess.game.board.Board;
import edu.rutgers.chess.game.piece.pieces.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Player class
 */
public class Player implements Serializable {
	Color color;
	boolean isInCheck;
	boolean isMated;
	Player opp;
	Board board;

	public ArrayList<Piece> pieces;
	private ArrayList<Piece> capturedPieces;

	/**
	 * Creates a player
	 *
	 * @param color Color of the player
	 */
	public Player(Color color, Board board){
		this.color = color;
		this.capturedPieces = new ArrayList<>();
		this.board = board;
		pieces = new ArrayList<>();
		if (color == Color.WHITE) initWhitePlayer();
		else initBlackPlayer();
	}

	/**
	 * Creates the white player
	 */
	public void initWhitePlayer(){
		for (int i = 0; i < 8; i++){
			Pawn wp = new Pawn(6, i, Color.WHITE, board);
			board.getBoard().get(6).get(i).setPiece(wp);
			pieces.add(wp);
		}

		Rook wR1 = new Rook(7, 0, Color.WHITE, board);
		board.getBoard().get(7).get(0).setPiece(wR1);
		pieces.add(wR1);

		Rook wR2 = new Rook(7, 7, Color.WHITE, board);
		board.getBoard().get(7).get(7).setPiece(wR2);
		pieces.add(wR2);

		Knight wN1 = new Knight(7, 1, Color.WHITE, board);
		board.getBoard().get(7).get(1).setPiece(wN1);
		pieces.add(wN1);

		Knight wN2 = new Knight(7, 6, Color.WHITE, board);
		board.getBoard().get(7).get(6).setPiece(wN2);
		pieces.add(wN2);

		Bishop wB1 = new Bishop(7, 2, Color.WHITE, board);
		board.getBoard().get(7).get(2).setPiece(wB1);
		pieces.add(wB1);

		Bishop wB2 = new Bishop(7, 5, Color.WHITE, board);
		board.getBoard().get(7).get(5).setPiece(wB2);
		pieces.add(wB2);

		Queen wQ = new Queen(7, 3, Color.WHITE, board);
		board.getBoard().get(7).get(3).setPiece(wQ);
		pieces.add(wQ);

		King wK = new King(7, 4, Color.WHITE, board);
		board.getBoard().get(7).get(4).setPiece(wK);
		pieces.add(wK);
	}

	/**
	 * Creates the black player
	 */
	public void initBlackPlayer() {
		for (int i = 0; i < 8; i++){
			Pawn bp = new Pawn(1, i, Color.BLACK, board);
			board.getBoard().get(1).get(i).setPiece(bp);
			pieces.add(bp);
		}

		Rook bR1 = new Rook(0, 0, Color.BLACK, board);
		board.getBoard().get(0).get(0).setPiece(bR1);
		pieces.add(bR1);

		Rook bR2 = new Rook(0, 7, Color.BLACK, board);
		board.getBoard().get(0).get(7).setPiece(bR2);
		pieces.add(bR2);

		Knight bN1 = new Knight(0, 1, Color.BLACK, board);
		board.getBoard().get(0).get(1).setPiece(bN1);
		pieces.add(bN1);

		Knight bN2 = new Knight(0, 6, Color.BLACK, board);
		board.getBoard().get(0).get(6).setPiece(bN2);
		pieces.add(bN2);

		Bishop bB1 = new Bishop(0, 2, Color.BLACK, board);
		board.getBoard().get(0).get(2).setPiece(bB1);
		pieces.add(bB1);

		Bishop bB2 = new Bishop(0, 5, Color.BLACK, board);
		board.getBoard().get(0).get(5).setPiece(bB2);
		pieces.add(bB2);

		Queen bQ = new Queen(0, 3, Color.BLACK, board);
		board.getBoard().get(0).get(3).setPiece(bQ);
		pieces.add(bQ);

		King bK = new King(0, 4, Color.BLACK, board);
		board.getBoard().get(0).get(4).setPiece(bK);
		pieces.add(bK);
	}

	/**
	 * Promotes
	 */
	public void promote(char newType, Pawn pawn){
		Space space;

		// if no promotion piece is indicated, or is not valid,3 default is queen
		switch (newType) {
			case 'N':
				space = pawn.getSpace();
				Knight n = new Knight(pawn.getSpace().getX(), pawn.getSpace().getY(), pawn.getColor(), board);
				this.pieces.remove(pawn);
				this.pieces.add(n);
				board.getBoard().get(n.getSpace().getX()).get(n.getSpace().getY()).setPiece(n);
				break;
			case 'B':
				space = pawn.getSpace();
				Bishop b = new Bishop(pawn.getSpace().getX(), pawn.getSpace().getY(), pawn.getColor(), board);
				this.pieces.remove(pawn);
				this.pieces.add(b);
				board.getBoard().get(b.getSpace().getX()).get(b.getSpace().getY()).setPiece(b);
				break;
			case 'R':
				space = pawn.getSpace();
				Rook r = new Rook(pawn.getSpace().getX(), pawn.getSpace().getY(), pawn.getColor(), board);
				this.pieces.remove(pawn);
				this.pieces.add(r);
				board.getBoard().get(r.getSpace().getX()).get(r.getSpace().getY()).setPiece(r);
				break;
			default:
				space = pawn.getSpace();
				Queen q = new Queen(pawn.getSpace().getX(), pawn.getSpace().getY(), pawn.getColor(), board);
				this.pieces.remove(pawn);
				this.pieces.add(q);
				board.getBoard().get(q.getSpace().getX()).get(q.getSpace().getY()).setPiece(q);
				break;
		}
	}

	/**
	 * Checks if the player is in check
	 * @param opponentPieces Pieces of the opponent
	 * @return True if in check, false if not
	 */
	public boolean isInCheck(ArrayList <Piece> opponentPieces){
		Piece king;
		Space kingSpace = null;

		// Finds the player's king in pieces array
		for (Piece piece : pieces) {
			if (piece instanceof King) {
				king = piece;
				kingSpace = king.getSpace();
			}
		}

		// Searches through all possible spaces opponent can move and sees if any match the king's space
		for (Piece currentPiece : opponentPieces) {
			for (int j = 0; j < currentPiece.getValidMoves().size(); j++) {
				if (currentPiece.getValidMoves().get(j).getX() == kingSpace.getX() && currentPiece.getValidMoves().get(j).getY() == kingSpace.getY()) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the player is check mated
	 * @param opponentPieces ArrayList of opponent's pieces
	 * @return true if player is check mated, false otherwise
	 */
	public boolean isMated(ArrayList <Piece> opponentPieces){
		Piece king = null;
		Space kingSpace = null;
		boolean spaceIsValid = false;
		// Finds the player's king in pieces array
		for (Piece piece : pieces) {
			if (piece instanceof King) {
				king = piece;
				kingSpace = king.getSpace();
			}
		}

		// player.Player must be in check to be check mated
		if (!isInCheck(opponentPieces)){
			return false;
		}
		// Searches all of the king's possible moves to see if he can escape on his own
		for(int i = 0; i < king.getValidMoves().size(); i++){
			for (Piece currentPiece : opponentPieces) {
				spaceIsValid = true;
				for (int e = 0; e < currentPiece.getValidMoves().size(); e++) {
					if (currentPiece.getValidMoves().get(e).getX() == king.getValidMoves().get(i).getX() && currentPiece.getValidMoves().get(e).getY() == king.getValidMoves().get(i).getY()) {
						spaceIsValid = false;
						break;
					}
				}
			}
			// If the king has a space it can escape to, player.Player is not check mated
			if (spaceIsValid){
				return false;
			}
		}

		return !canCheckBeBlocked(opponentPieces);
	}

	/**
	 * Checks if the player.Player's check can be blocked by another piece
	 * @param opponentPieces the opponent's pieces
	 * @return true if the check can be blocked, false if it cannot
	 */
	private boolean canCheckBeBlocked(ArrayList<Piece> opponentPieces){
		Piece king = null;
		Space kingSpace = null;
		//boolean spaceIsValid = false;

		// Finds the player's king in pieces array
		for (Piece piece : pieces) {
			if (piece instanceof King) {
				king = piece;
				kingSpace = king.getSpace();
			}
		}

		if (!isInCheck(opponentPieces)){
			return true;
		}

		Piece checkPiece = null;

		// Finds the Piece that is checking the king
		for (Piece currentPiece : opponentPieces) {
			for (int j = 0; j < currentPiece.getValidMoves().size(); j++) {
				if (currentPiece.getValidMoves().get(j).getX() == kingSpace.getX() && currentPiece.getValidMoves().get(j).getY() == kingSpace.getY()) {
					checkPiece = currentPiece;
				}
			}
		}

		ArrayList <Space> checkSpaces = new ArrayList<>();
		int y = 0;

		// Another piece can get the king out of check if it can capture the threatening piece, or can block the check
		// The switch statement fills the checkSpaces array with the spaces that the player.Player can move to to avoid check
		// A check from a pawn or knight can only be blocked by another piece by capturing it
		// A king cannot check another king so that case isn't considered
		if (checkPiece instanceof Pawn) checkSpaces.add(checkPiece.getSpace());
		else if (checkPiece instanceof Knight) checkSpaces.add(checkPiece.getSpace());
		else if (checkPiece instanceof Rook) {
			if (checkPiece.getSpace().getX() == king.getSpace().getX()) {
				// If the rook is checking horizontally and to the right
				if (checkPiece.getSpace().getX() > king.getSpace().getX()) {
					for (int x = 0; x < checkPiece.getSpace().getX() - kingSpace.getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY()));
					}
				}
				// If the rook is checking horizontally and to the left
				else {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() + x).get(checkPiece.getSpace().getY()));
					}
				}
			} else if (checkPiece.getSpace().getY() == king.getSpace().getY()) {
				// If the rook is checking vertically and from above
				if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
					for (int x = 0; y < checkPiece.getSpace().getY() - kingSpace.getY(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() - x));
					}
				}
				// If the rook is checking vertically and from below
				else {
					for (int x = 0; y < kingSpace.getY() - checkPiece.getSpace().getY(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() + x));
					}
				}
			}
		} else if (checkPiece instanceof Bishop) {
			if (checkPiece.getSpace().getX() < king.getSpace().getX()) {
				// If the bishop is checking from the top left
				if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() + y));
						y++;
						if (y >= kingSpace.getY() - checkPiece.getSpace().getY()) {
							break;
						}
					}
				}
				// If the bishop is checking from the bottom left
				else {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() - y));
						y++;
						if (y >= kingSpace.getY() - checkPiece.getSpace().getY()) {
							break;
						}
					}
				}
			} else if (checkPiece.getSpace().getX() > king.getSpace().getX()) {
				// If the bishop is checking from the top right
				if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() - y));
						y++;
						if (y >= kingSpace.getY() - checkPiece.getSpace().getY()) {
							break;
						}
					}
				}
				// If the bishop is checking from the bottom right
				else {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() + y));
						y++;
						if (y >= kingSpace.getY() - checkPiece.getSpace().getY()) {
							break;
						}
					}
				}
			}
			// If the queen is being used like a rook
		} else if (checkPiece instanceof Queen) {
			if (checkPiece.getSpace().getX() == king.getSpace().getX()) {
				if (checkPiece.getSpace().getX() > king.getSpace().getX()) {
					for (int x = 0; x < checkPiece.getSpace().getX() - kingSpace.getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY()));
					}
				} else {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() + x).get(checkPiece.getSpace().getY()));
					}
				}
			} else if (checkPiece.getSpace().getY() == king.getSpace().getY()) {
				if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
					for (int x = 0; y < checkPiece.getSpace().getY() - kingSpace.getY(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() - x));
					}
				} else {
					for (int x = 0; y < kingSpace.getY() - checkPiece.getSpace().getY(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() + x));
					}
				}
			}

			// If the queen is being used like a bishop
			else if (checkPiece.getSpace().getX() < king.getSpace().getX()) {
				if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() + y));
						y++;
						if (y >= kingSpace.getY() - checkPiece.getSpace().getY()) {
							break;
						}
					}
				} else {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() - y));
						y++;
						if (y >= kingSpace.getY() - checkPiece.getSpace().getY()) {
							break;
						}
					}
				}
			} else if (checkPiece.getSpace().getX() > king.getSpace().getX()) {
				if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() - y));
						y++;
						if (y >= kingSpace.getY() - checkPiece.getSpace().getY()) {
							break;
						}
					}
				} else {
					for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
						checkSpaces.add(board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() + y));
						y++;
						if (y >= kingSpace.getY() - checkPiece.getSpace().getY()) {
							break;
						}
					}
				}
			}
		}

		// Searches all the player.Player's possible moves to see if he can move to a space that would get him out of check
		for (Piece currentPiece : pieces) {
			for (int j = 0; j < currentPiece.getValidMoves().size(); j++) {
				Space space = currentPiece.getValidMoves().get(j);
				if (checkSpaces.contains(space)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the player wants to resign or draw
	 * @param move the move the player wants to make
	 * @return the status of the game
	 */

	// Need to deal with check, checkmate, invalid moves
	public Status move(String move){
		if (move.equalsIgnoreCase("resign"))
			return this.color.equals(Color.WHITE) ? Status.BLACK_WON : Status.WHITE_WON;
		else if (move.equalsIgnoreCase("draw?")) return Status.DRAW;

		String firstSpace = move.substring(0, 2);
		String secondSpace = move.substring(3, 5);
		//System.out.println("firstSpace: " + firstSpace + " secondSpace: " + secondSpace);
		boolean firstSpaceExists = false;
		boolean secondSpaceExists = false;

		//checks if firstSpace and secondSpace are valid spaces on the board
		for (int i = 0; i < Board.nameBoard.length; i++){
			for (int j = 0; j < Board.nameBoard.length; j++){
				//if firstSpace and secondSpace are equal, secondSpaceExists stays false
				if (Board.nameBoard[i][j].equals(firstSpace)){
					firstSpaceExists = true;
				}
				else if (Board.nameBoard[i][j].equals(secondSpace)){
					secondSpaceExists = true;
				}
			}
		}

		if (!firstSpaceExists || !secondSpaceExists){
			//System.out.println("move failed!");
			return Status.MOVE_FAILED;
		}

		Piece piece = null;
		Space space = null;


		for (Piece value : pieces) {
			if (value.getSpace().getName().equals(firstSpace)) {
				//the piece that the player want to move
				piece = value;
				for (int j = 0; j < board.getBoard().size(); j++)
					for (int e = 0; e < board.getBoard().size(); e++)
						if (board.getBoard().get(j).get(e).getName().equals(secondSpace)) {
							//the space the player want to move to
							space = board.getBoard().get(j).get(e);
							break;
						}

			}
		}

		//if the player has no piece on firstSpace
		if (piece == null) return Status.MOVE_FAILED;

		boolean pieceMoved = piece.movePiece(space, this, move);

		if(!pieceMoved) return Status.MOVE_FAILED;

		return Status.IN_PROGRESS;
	}

	//if a move is found to be invalid after the user moves it, this takes back the move
	public void redoMove(String move, Player player){
		String firstSpace = move.substring(0, 2);
		String secondSpace = move.substring(3, 5);

		Piece piece = null;
		Space space = null;

		for (Piece value : pieces)
			if (value.getSpace().getName().equals(secondSpace)) {
				//the piece that the player has to move back
				piece = value;
				for (int j = 0; j < board.getBoard().size(); j++)
					for (int e = 0; e < board.getBoard().size(); e++)
						if (board.getBoard().get(j).get(e).getName().equals(firstSpace)) {
							//the space the player want to move to
							space = board.getBoard().get(j).get(e);
							break;
						}
			}
		//if the player captured an opponent's piece before redoing a move, the opponents piece needs to be return;
		board.getBoard().get(piece.getX()).get(piece.getY()).setPiece(null);


		piece.setSpace(space);
		piece.setX(space.getX());
		piece.setY(space.getY());
		board.getBoard().get(piece.getX()).get(piece.getY()).setPiece(piece);

	}

	/**
	 * Sets the opponent of the player
	 * @param opp the opponent of the player
	 */
	public void setOpp(Player opp){
		this.opp = opp;
	}

	/**
	 * Gets the opponent of the player
	 * @return the opponent of the player
	 */
	public Player getOpp(){
		return this.opp;
	}
}

