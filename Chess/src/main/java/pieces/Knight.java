package pieces;

import java.util.ArrayList;
import board.Board;
import board.Space;
import player.Player;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Class for the knight piece
 */
public class Knight extends Piece {
    private ArrayList<Space> validMoves;

    /**
     * Constructor for the knight piece
     * @param x the x coordinate of the knight
     * @param y the y coordinate of the knight
     * @param name the name of the knight
     */
    public Knight(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.space = Board.getBoard().get(x).get(y);
        this.name = name;
        this.validMoves = new ArrayList<>();
        setValidMoves();
    }

    /**
     * Checks if the move is valid
     * @param space the space the knight is moving to
     * @return true if the move is valid, false otherwise
     */
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
                case 0 -> {
                    newX = this.x - 2;
                    newY = this.y + 1;
                    if (newX < 0 || newY > 7) {
                        break;
                    }
                    space = Board.getBoard().get(newX).get(newY);
                    if (space.isOccupied()) {
                        if (space.getPiece().charAt(0) == this.name.charAt(0)) {
                            break;
                        }
                        else{
                            validMoves.add(space);
                            break;
                        }
                    }
                    validMoves.add(space);
                }
                case 1 -> {
                    newX = this.x - 1;
                    newY = this.y + 2;
                    if (newX < 0 || newY > 7) {
                        break;
                    }
                    space = Board.getBoard().get(newX).get(newY);
                    if (space.isOccupied()) {
                        if (space.getPiece().charAt(0) == this.name.charAt(0)) {
                            break;
                        }
                        else{
                            validMoves.add(space);
                            break;
                        }
                    }
                    validMoves.add(space);
                }
                case 2 -> {
                    newX = this.x + 1;
                    newY = this.y + 2;
                    if (newX > 7 || newY > 7) {
                        break;
                    }
                    space = Board.getBoard().get(newX).get(newY);
                    if (space.isOccupied()) {
                        if (space.getPiece().charAt(0) == this.name.charAt(0)) {
                            break;
                        }
                        else{
                            validMoves.add(space);
                            break;
                        }
                    }
                    validMoves.add(space);
                }
                case 3 -> {
                    newX = this.x + 2;
                    newY = this.y + 1;
                    if (newX > 7 || newY > 7) {
                        break;
                    }
                    space = Board.getBoard().get(newX).get(newY);
                    if (space.isOccupied()) {
                        if (space.getPiece().charAt(0) == this.name.charAt(0)) {
                            break;
                        }
                        else{
                            validMoves.add(space);
                            break;
                        }
                    }
                    validMoves.add(space);
                }
                case 4 -> {
                    newX = this.x + 2;
                    newY = this.y - 1;
                    if (newX > 7 || newY < 0) {
                        break;
                    }
                    space = Board.getBoard().get(newX).get(newY);
                    if (space.isOccupied()) {
                        if (space.getPiece().charAt(0) == this.name.charAt(0)) {
                            break;
                        }
                        else{
                            validMoves.add(space);
                            break;
                        }
                    }
                    validMoves.add(space);
                }
                case 5 -> {
                    newX = this.x + 1;
                    newY = this.y - 2;
                    if (newX > 7 || newY < 0) {
                        break;
                    }
                    space = Board.getBoard().get(newX).get(newY);
                    if (space.isOccupied()) {
                        if (space.getPiece().charAt(0) == this.name.charAt(0)) {
                            break;
                        }
                        else{
                            validMoves.add(space);
                            break;
                        }
                    }
                    validMoves.add(space);
                }
                case 6 -> {
                    newX = this.x - 1;
                    newY = this.y - 2;
                    if (newX < 0 || newY < 0) {
                        break;
                    }
                    space = Board.getBoard().get(newX).get(newY);
                    if (space.isOccupied()) {
                        if (space.getPiece().charAt(0) == this.name.charAt(0)) {
                            break;
                        }
                        else{
                            validMoves.add(space);
                            break;
                        }
                    }
                    validMoves.add(space);
                }
                case 7 -> {
                    newX = this.x - 2;
                    newY = this.y - 1;
                    if (newX < 0 || newY < 0) {
                        break;
                    }
                    space = Board.getBoard().get(newX).get(newY);
                    if (space.isOccupied()) {
                        if (space.getPiece().charAt(0) == this.name.charAt(0)) {
                            break;
                        }
                        else{
                            validMoves.add(space);
                            break;
                        }
                    }
                    validMoves.add(space);
                }
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
    public boolean movePiece(Space space, Player player, String move){
        setValidMoves();
        if (!validMoves.contains(space)){
            //System.out.print("Invalid move, try again");
            return false;
        }

        Board.getBoard().get(this.x).get(this.y).setOccupied(false);

        this.space = space;
        this.x = space.getX();
        this.y = space.getY();
        setValidMoves();
        Board.getBoard().get(this.x).get(this.y).setPiece(this.name);
        Board.getBoard().get(this.x).get(this.y).setOccupied(true);


        return true;
    }

    /**
     * Returns the valid moves for the knight
     * @return valid moves for the knight
     */
    public ArrayList<Space> getValidMoves(){
        return validMoves;
    }
}
