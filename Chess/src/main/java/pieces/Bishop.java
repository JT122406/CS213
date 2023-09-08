package pieces;

import java.util.ArrayList;
import board.Board;
import board.Space;
import player.Player;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Class for the bishop piece
 */
public class Bishop extends Piece {
    private ArrayList<Space> validMoves;

    /**
     * Constructor for the bishop piece
     * @param x x coordinate of the bishop
     * @param y y coordinate of the bishop
     * @param name name of the piece
     */
    public Bishop(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.space = Board.getBoard().get(x).get(y);
        this.name = name;
        this.validMoves = new ArrayList<>();
        setValidMoves();
    }

    /**
     * Checks if the move is valid
     * @param space space to move to
     * @return true if the move is valid, false otherwise
     */
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
                case 0 -> {
                    for (int j = 1; ; j++) {
                        newX = this.x - j;
                        newY = this.y + j;
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
                }
                case 1 -> {
                    for (int j = 1; ; j++) {
                        newX = this.x + j;
                        newY = this.y + j;
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
                }
                case 2 -> {
                    for (int j = 1; ; j++) {
                        newX = this.x + j;
                        newY = this.y - j;
                        if (newY < 0 || newX > 7) {
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
                case 3 -> {
                    for (int j = 1; ; j++) {
                        newX = this.x - j;
                        newY = this.y - j;
                        if (newY < 0 || newX < 0) {
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
    }

    /**
     * Moves the bishop to the space
     * @param space space to move to
     * @param player player that is moving the piece
     * @param move move that is being made
     * @return true if the move was successful, false otherwise
     */
    public boolean movePiece(Space space, Player player, String move){
        setValidMoves();

        if (space == null){
            return false;
        }

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
     * Gets the valid moves for the bishop
     * @return the valid moves for the bishop
     */
    public ArrayList<Space> getValidMoves(){
        return validMoves;
    }
}
