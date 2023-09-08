package pieces;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthStyle;

import board.Board;
import board.Space;
import player.Player;

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
     * @param x the x coordinate of the pawn
     * @param y the y coordinate of the pawn
     * @param name the name of the pawn
     */
    public Pawn(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
        this.space = Board.getBoard().get(x).get(y);
        this.validMoves = new ArrayList<>();
        this.firstMove = true;
        setValidMoves();
    }

    /**
     * Checks if the move is valid
     * @param space the space the pawn is moving to
     * @return true if the move is valid, false otherwise
     */
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
    public void canCapture(String player){
        int newX;
        int newY;
        int newY2 = 0;
        if (player.equals("wp")){
            newX = this.x - 1;
        }
        else {
            newX = this.x + 1;
        }
        

        if(this.y > 0){
            newY = this.y - 1;
            Space space = Board.getBoard().get(newX).get(newY);
            if (space.isOccupied()){
                if (space.getPiece().charAt(0) != this.name.charAt(0)){
                    validMoves.add(space);
                }
            }
        }

        if(this.y < 7){
            newY2 = this.y + 1;
            Space space = Board.getBoard().get(newX).get(newY2);
            if (space.isOccupied()){
                if (space.getPiece().charAt(0) != this.name.charAt(0)){
                    validMoves.add(space);
                }
            }
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
        if (this.x == 7 || this.x == 0){
            return;
        }

        int newX;
        int newY;

        String player = name;
        // White player
        if (player.equals("wp")) {
            //System.out.println("player.equals(wp)");
            newX = this.x - 1;
            newY = this.y;

            Space space = Board.getBoard().get(newX).get(newY);
            if (!space.isOccupied()){
                validMoves.add(space);
            }

            if (firstMove){
                newX = this.x - 2;
                Space space2 = Board.getBoard().get(newX).get(newY);
                if(!space.isOccupied()){
                    validMoves.add(space2);
                }
            }
        }
        // Black player
        if (player.equals("bp")) {
            newX = this.x + 1;
            newY = this.y;

            Space space = Board.getBoard().get(newX).get(newY);
            if (!space.isOccupied()){
                validMoves.add(space);
            }

            if (firstMove){
                newX = this.x + 2;
                Space space2 = Board.getBoard().get(newX).get(newY);
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
    public boolean movePiece(Space space, Player player, String move){
        setValidMoves();
        /*for (int i = 0; i < validMoves.size(); i++){
            System.out.println("validMoves.get(" + i + "): " + validMoves.get(i).getName());
        }*/
        /*if (validMoves.size() == 0){
            System.out.println("valid moves is empty");
        }*/
        if (!validMoves.contains(space)){
            //System.out.print("validMove doesn't contain space");
            return false;
        }
        Board.getBoard().get(this.x).get(this.y).setOccupied(false);

        this.space = space;
        this.x = space.getX();
        this.y = space.getY();
        setValidMoves();
        this.firstMove = false;
        Board.getBoard().get(this.x).get(this.y).setPiece(this.name);
        Board.getBoard().get(this.x).get(this.y).setOccupied(true);

        if (this.x == 0 || this.x == 7){
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
    public ArrayList<Space> getValidMoves(){
        return validMoves;
    }
}