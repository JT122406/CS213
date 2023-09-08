package pieces;

import java.util.ArrayList;
import board.Space;
import player.Player;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Class for the piece Object
 */
public class Piece {
    public static ArrayList<Piece> allPieces;
    
    int x;
    int y;
    Space space;
    String name;
    boolean hasMoved;
    /**
     * Returns the name of the piece
     * @return name
     */
    public String getName(){
        return name;
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
}
