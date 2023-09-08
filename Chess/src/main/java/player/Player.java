package player;

import java.util.ArrayList;

import pieces.*;
import board.*;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Player class
 */
public class Player {
    Color color;
    boolean isInCheck;
    boolean isMated;
    Player opp;

    public ArrayList <Piece> pieces;
    private ArrayList <Piece> capturedPieces;

    /**
     * Creates a player
     * @param color Color of the player
     * @param pieces Pieces of the player
     */
    public Player(Color color, ArrayList<Piece> pieces){
        this.color = color;
        this.pieces = pieces;
        this.capturedPieces = new ArrayList<>();
    }

    /**
     * Creates the white player
     * @return White Player
     */
    public static Player initWhitePlayer(){
        ArrayList <Piece> whitePieces = new ArrayList<>();

        for (int i = 0; i < 8; i++){
            Pawn wp = new Pawn(6, i, "wp");
            Board.getBoard().get(6).get(i).setPiece("wp");
            Board.getBoard().get(6).get(i).setOccupied(true);
            whitePieces.add(wp);
        }

        Rook wR1 = new Rook(7, 0, "wR");
        Board.getBoard().get(7).get(0).setPiece("wR");
        Board.getBoard().get(7).get(0).setOccupied(true);
        whitePieces.add(wR1);

        Rook wR2 = new Rook(7, 7, "wR");
        Board.getBoard().get(7).get(7).setPiece("wR");
        Board.getBoard().get(7).get(7).setOccupied(true);
        whitePieces.add(wR2);

        Knight wN1 = new Knight(7, 1, "wN");
        Board.getBoard().get(7).get(1).setPiece("wN");
        Board.getBoard().get(7).get(1).setOccupied(true);
        whitePieces.add(wN1);

        Knight wN2 = new Knight(7, 6, "wN");
        Board.getBoard().get(7).get(6).setPiece("wN");
        Board.getBoard().get(7).get(6).setOccupied(true);
        whitePieces.add(wN2);

        Bishop wB1 = new Bishop(7, 2, "wB");
        Board.getBoard().get(7).get(2).setPiece("wB");
        Board.getBoard().get(7).get(2).setOccupied(true);
        whitePieces.add(wB1);

        Bishop wB2 = new Bishop(7, 5, "wB");
        Board.getBoard().get(7).get(5).setPiece("wB");
        Board.getBoard().get(7).get(5).setOccupied(true);
        whitePieces.add(wB2);

        Queen wQ = new Queen(7, 3, "wQ");
        Board.getBoard().get(7).get(3).setPiece("wQ");
        Board.getBoard().get(7).get(3).setOccupied(true);
        whitePieces.add(wQ);

        King wK = new King(7, 4, "wK");
        Board.getBoard().get(7).get(4).setPiece("wK");
        Board.getBoard().get(7).get(4).setOccupied(true);
        whitePieces.add(wK);

        return new Player(Color.WHITE, whitePieces);
    }

    /**
     * Creates the black player
     * @return Black Player
     */
    public static Player initBlackPlayer(){
        ArrayList <Piece> blackPieces = new ArrayList<>();

        for (int i = 0; i < 8; i++){
            Pawn bp = new Pawn(1, i, "bp");
            Board.getBoard().get(1).get(i).setPiece("bp");
            Board.getBoard().get(1).get(i).setOccupied(true);
            blackPieces.add(bp);
        }

        Rook bR1 = new Rook(0, 0, "bR");
        Board.getBoard().get(0).get(0).setPiece("bR");
        Board.getBoard().get(0).get(0).setOccupied(true);
        blackPieces.add(bR1);

        Rook bR2 = new Rook(0, 7, "bR");
        Board.getBoard().get(0).get(7).setPiece("bR");
        Board.getBoard().get(0).get(7).setOccupied(true);
        blackPieces.add(bR2);

        Knight bN1 = new Knight(0, 1, "bN");
        Board.getBoard().get(0).get(1).setPiece("bN");
        Board.getBoard().get(0).get(1).setOccupied(true);
        blackPieces.add(bN1);

        Knight bN2 = new Knight(0, 6, "bN");
        Board.getBoard().get(0).get(6).setPiece("bN");
        Board.getBoard().get(0).get(6).setOccupied(true);
        blackPieces.add(bN2);

        Bishop bB1 = new Bishop(0, 2, "bB");
        Board.getBoard().get(0).get(2).setPiece("bB");
        Board.getBoard().get(0).get(2).setOccupied(true);
        blackPieces.add(bB1);

        Bishop bB2 = new Bishop(0, 5, "bB");
        Board.getBoard().get(0).get(5).setPiece("bB");
        Board.getBoard().get(0).get(5).setOccupied(true);
        blackPieces.add(bB2);

        Queen bQ = new Queen(0, 3, "bQ");
        Board.getBoard().get(0).get(3).setPiece("bQ");
        Board.getBoard().get(0).get(3).setOccupied(true);
        blackPieces.add(bQ);

        King bK = new King(0, 4, "bK");
        Board.getBoard().get(0).get(4).setPiece("bK");
        Board.getBoard().get(0).get(4).setOccupied(true);
        blackPieces.add(bK);

        return new Player(Color.BLACK, blackPieces);
    }

    /**
     * Promotes
     */
    public void promote(char newType, Pawn pawn){
        Space space;

        switch (newType) {
            case 'N' -> {
                space = pawn.getSpace();
                Knight n = new Knight(pawn.getSpace().getX(), pawn.getSpace().getY(), pawn.getName().charAt(0) + "N");
                this.pieces.remove(pawn);
                this.pieces.add(n);
                Board.getBoard().get(n.getSpace().getX()).get(n.getSpace().getY()).setPiece(n.getName());
            }
            case 'B' -> {
                space = pawn.getSpace();
                Bishop b = new Bishop(pawn.getSpace().getX(), pawn.getSpace().getY(), pawn.getName().charAt(0) + "B");
                this.pieces.remove(pawn);
                this.pieces.add(b);
                Board.getBoard().get(b.getSpace().getX()).get(b.getSpace().getY()).setPiece(b.getName());
            }
            case 'R' -> {
                space = pawn.getSpace();
                Rook r = new Rook(pawn.getSpace().getX(), pawn.getSpace().getY(), pawn.getName().charAt(0) + "R");
                this.pieces.remove(pawn);
                this.pieces.add(r);
                Board.getBoard().get(r.getSpace().getX()).get(r.getSpace().getY()).setPiece(r.getName());
            }
            case 'Q' -> {
                space = pawn.getSpace();
                Queen q = new Queen(pawn.getSpace().getX(), pawn.getSpace().getY(), pawn.getName().charAt(0) + "Q");
                this.pieces.remove(pawn);
                this.pieces.add(q);
                Board.getBoard().get(q.getSpace().getX()).get(q.getSpace().getY()).setPiece(q.getName());
            }
            // if no promotion piece is indicated, or is not valid,3 default is queen
            default -> {
                space = pawn.getSpace();
                Queen q = new Queen(pawn.getSpace().getX(), pawn.getSpace().getY(), pawn.getName().charAt(0) + "Q");
                this.pieces.remove(pawn);
                this.pieces.add(q);
                Board.getBoard().get(q.getSpace().getX()).get(q.getSpace().getY()).setPiece(q.getName());
            }
        }
    }

    /**
     * Checks if the player is in check
     * @param opponentPieces Pieces of the opponent
     * @return True if in check, false if not
     */
    public boolean isInCheck(ArrayList <Piece> opponentPieces){
        Piece king = null;
        Space kingSpace = null;

        // Finds the player's king in pieces array
        for (Piece piece : pieces) {
            if (piece.getName().substring(1).equals("K")) {
                king = piece;
                kingSpace = king.getSpace();
            }
        }

        // Searches through all possible spaces opponent can move and sees if any match the king's space
        for (int i = 0; i < opponentPieces.size(); i++){
            Piece currentPiece = opponentPieces.get(i);
            for (int j = 0; j < currentPiece.getValidMoves().size(); j++){
                if (currentPiece.getValidMoves().get(j).getX() == kingSpace.getX() && currentPiece.getValidMoves().get(j).getY() == kingSpace.getY()){
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
            if (piece.getName().substring(1).equals("K")) {
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
            for (int j = 0; j < opponentPieces.size(); j++){
                Piece currentPiece = opponentPieces.get(j);
                spaceIsValid = true;
                for (int e = 0; e < currentPiece.getValidMoves().size(); e++){
                    if (currentPiece.getValidMoves().get(e).getX() == king.getValidMoves().get(i).getX() && currentPiece.getValidMoves().get(e).getY() == king.getValidMoves().get(i).getY()){
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
            if (piece.getName().substring(1).equals("K")) {
                king = piece;
                kingSpace = king.getSpace();
            }
        }
        
        if (!isInCheck(opponentPieces)){
            return true;
        }
        
        Piece checkPiece = null;

        // Finds the Piece that is checking the king
        for (int i = 0; i < opponentPieces.size(); i++){
            Piece currentPiece = opponentPieces.get(i);
            for (int j = 0; j < currentPiece.getValidMoves().size(); j++){
                if (currentPiece.getValidMoves().get(j).getX() == kingSpace.getX() && currentPiece.getValidMoves().get(j).getY() == kingSpace.getY()){
                    checkPiece = currentPiece;
                }
            }
        }

        String pieceType = checkPiece.getName().substring(1);
        ArrayList <Space> checkSpaces = new ArrayList<>();
        int y = 0;

        // Another piece can get the king out of check if it can capture the threatening piece, or can block the check
        // The switch statement fills the checkSpaces array with the spaces that the player.Player can move to to avoid check
        switch (pieceType) {
            // A check from a pawn or knight can only be blocked by another piece by caputring it
            // A king cannot check another king so that case isn't considered
            case "p" -> checkSpaces.add(checkPiece.getSpace());
            case "N" -> checkSpaces.add(checkPiece.getSpace());
            case "R" -> {
                if (checkPiece.getSpace().getX() == king.getSpace().getX()) {
                    // If the rook is checking horizontally and to the right
                    if (checkPiece.getSpace().getX() > king.getSpace().getX()) {
                        for (int x = 0; x < checkPiece.getSpace().getX() - kingSpace.getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY()));
                        }
                    }
                    // If the rook is checking horizontally and to the left
                    else {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() + x).get(checkPiece.getSpace().getY()));
                        }
                    }
                } else if (checkPiece.getSpace().getY() == king.getSpace().getY()) {
                    // If the rook is checking vertically and from above
                    if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
                        for (int x = 0; y < checkPiece.getSpace().getY() - kingSpace.getY(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() - x));
                        }
                    }
                    // If the rook is checking vertically and from below
                    else {
                        for (int x = 0; y < kingSpace.getY() - checkPiece.getSpace().getY(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() + x));
                        }
                    }
                }
            }
            case "B" -> {
                if (checkPiece.getSpace().getX() < king.getSpace().getX()) {
                    // If the bishop is checking from the top left
                    if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() + y));
                            y++;
                            if (y >= kingSpace.getY() - checkPiece.getSpace().getY()){
                                break;
                            }
                        }
                    }
                    // If the bishop is checking from the bottom left
                    else {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() - y));
                            y++;
                            if (y >= kingSpace.getY() - checkPiece.getSpace().getY()){
                                break;
                            }
                        }
                    }
                } else if (checkPiece.getSpace().getX() > king.getSpace().getX()) {
                    // If the bishop is checking from the top right
                    if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() - y));
                            y++;
                            if (y >= kingSpace.getY() - checkPiece.getSpace().getY()){
                                break;
                            }
                        }
                    }
                    // If the bishop is checking from the bottom right
                    else {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() + y));
                            y++;
                            if (y >= kingSpace.getY() - checkPiece.getSpace().getY()){
                                break;
                            }
                        }
                    }
                }
            }
            case "Q" -> {
                // If the queen is being used like a rook
                if (checkPiece.getSpace().getX() == king.getSpace().getX()) {
                    if (checkPiece.getSpace().getX() > king.getSpace().getX()) {
                        for (int x = 0; x < checkPiece.getSpace().getX() - kingSpace.getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY()));
                        }
                    } else {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() + x).get(checkPiece.getSpace().getY()));
                        }
                    }
                } else if (checkPiece.getSpace().getY() == king.getSpace().getY()) {
                    if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
                        for (int x = 0; y < checkPiece.getSpace().getY() - kingSpace.getY(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() - x));
                        }
                    } else {
                        for (int x = 0; y < kingSpace.getY() - checkPiece.getSpace().getY(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() + x));
                        }
                    }
                }

                // If the queen is being used like a bishop
                else if (checkPiece.getSpace().getX() < king.getSpace().getX()) {
                    if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() + y));
                            y++;
                            if (y >= kingSpace.getY() - checkPiece.getSpace().getY()){
                                break;
                            }
                        }
                    } else {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() - y));
                            y++;
                            if (y >= kingSpace.getY() - checkPiece.getSpace().getY()){
                                break;
                            }
                        }
                    }
                } else if (checkPiece.getSpace().getX() > king.getSpace().getX()) {
                    if (checkPiece.getSpace().getY() > king.getSpace().getY()) {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX() - x).get(checkPiece.getSpace().getY() - y));
                            y++;
                            if (y >= kingSpace.getY() - checkPiece.getSpace().getY()){
                                break;
                            }
                        }
                    } else {
                        for (int x = 0; x < kingSpace.getX() - checkPiece.getSpace().getX(); x++) {
                            checkSpaces.add(Board.getBoard().get(checkPiece.getSpace().getX()).get(checkPiece.getSpace().getY() + y));
                            y++;
                            if (y >= kingSpace.getY() - checkPiece.getSpace().getY()){
                                break;
                            }
                        }
                    }
                }
            }
        }

        // Searches all of the player.Player's possible moves to see if he can move to a space that would get him out of check
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
     * @param player the player who is making the move
     * @return the status of the game
     */

     // Need to deal with check, checkmate, invalid moves
    public Status move(String move, Player player){
        if (move.equalsIgnoreCase("resign"))
            return player.color.equals(Color.WHITE) ? Status.BLACK_WON : Status.WHITE_WON;
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
                for (int j = 0; j < Board.getBoard().size(); j++) {
                    for (int e = 0; e < Board.getBoard().size(); e++) {
                        if (Board.getBoard().get(j).get(e).getName().equals(secondSpace)) {
                            //the space the player want to move to
                            space = Board.getBoard().get(j).get(e);
                            break;
                        }
                    }
                }


            }
        }

        //if the player has no piece on firstSpace
        if (piece == null){
            return Status.MOVE_FAILED;
        }
        

        boolean pieceMoved = piece.movePiece(space, this, move);

        if(!pieceMoved){
            return Status.MOVE_FAILED;
        }



        return Status.IN_PROGRESS;
    }

    //if a move is found to be invalid after the user moves it, this takes back the move
    public void redoMove(String move, Player player){
        String firstSpace = move.substring(0, 2);
        String secondSpace = move.substring(3, 5);

        Piece piece = null;
        Space space = null;

         
        for (int i = 0; i < pieces.size(); i++){
            if (pieces.get(i).getSpace().getName().equals(secondSpace)){
                //the piece that the player has to move back
                piece = pieces.get(i);
                for(int j = 0; j < Board.getBoard().size(); j++){
                    for(int e = 0; e < Board.getBoard().size(); e++){
                        if (Board.getBoard().get(j).get(e).getName().equals(firstSpace)){
                            //the space the player want to move to
                            space = Board.getBoard().get(j).get(e);
                            break;
                        }
                    }
                }
                

            }
        }
        //if the player captured an opponent's piece before redoing a move, the opponents piece needs to be return;
        Board.getBoard().get(piece.getX()).get(piece.getY()).setPiece("");


        piece.setSpace(space);
        piece.setX(space.getX()); 
        piece.setY(space.getY());
        Board.getBoard().get(piece.getX()).get(piece.getY()).setPiece(piece.getName());

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
