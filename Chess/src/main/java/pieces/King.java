package pieces;

import board.Board;
import board.Space;
import player.Player;

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
     * @param x the x coordinate of the king
     * @param y the y coordinate of the king
     * @param name the name of the king
     */
    public King(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.space = Board.getBoard().get(x).get(y);
        this.name = name;    
        this.validMoves = new ArrayList<>();
        this.hasMoved = false;
        

        
    }

    /**
     * Checks if the move is valid
     * @param space the space the king wants to move to
     * @return true if the move is valid, false if it is not
     */
    public boolean isMoveValid(Space space){
        return validMoves.contains(space);
    }
    /*
    *checks if player can castle
    *@param player the player making the move
    */    
    public void canCastle(Player player){
        if (this.hasMoved){
            return;
        }
        
        for(int i = 0; i < player.pieces.size(); i++){
            if (player.pieces.get(i).getName().charAt(1) == 'R'){
                boolean castle = true;
                Piece rook = player.pieces.get(i);
                if (!rook.hasMoved){
                    Space rookSpace = rook.getSpace();
                    Space kingSpace = this.getSpace();
                    
                    //king cannot casting if any squares on his way to castle are being attacked by opponent or 
                    //if pieces are in the way
                    if (rookSpace.getY() < kingSpace.getY()){
                        Space cSpace1 = Board.getBoard().get(this.x).get(this.y - 1);
                        Space cSpace2 = Board.getBoard().get(this.x).get(this.y - 2);

                        if(cSpace1.isOccupied() || cSpace2.isOccupied()){
                            continue;
                        }
                        
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
                        Space cSpace1 = Board.getBoard().get(this.x).get(this.y + 1);
                        Space cSpace2 = Board.getBoard().get(this.x).get(this.y + 2);

                        if(cSpace1.isOccupied() || cSpace2.isOccupied()){
                            continue;
                        }
                        
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
                case 0 -> {
                    newX = this.x - 1;
                    newY = this.y;
                    if (newX < 0) {
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
                    newX = this.x + 1;
                    newY = this.y;
                    if (newX > 7) {
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
                    newX = this.x;
                    newY = this.y + 1;
                    if (newY > 7) {
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
                    newX = this.x;
                    newY = this.y - 1;
                    if (newY < 0) {
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
                    newX = this.x - 1;
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
                case 5 -> {
                    newX = this.x + 1;
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
                case 6 -> {
                    newX = this.x + 1;
                    newY = this.y - 1;
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
                case 7 -> {
                    newX = this.x - 1;
                    newY = this.y - 1;
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
        canCastle(player);
    }

    /**
     * Moves the king to the space
     * @param space the space the king wants to move to
     * @param player the player that is moving the king
     * @param move the move the player wants to make
     * @return true if the move was successful, false if it was not
     */
    public boolean movePiece(Space space, Player player, String move){
        setValidMoves(player);
        if (!validMoves.contains(space)){
            //System.out.print("Invalid move, try again");
            return false;
        }

         Board.getBoard().get(this.x).get(this.y).setOccupied(false);

        //if player is castling
        boolean castling = false;
        if (this.space.getY() - space.getX() == 2 || this.space.getY() - space.getY() == -2){
            castling = true;
        }
        
        this.space = space;
        this.x = space.getX();
        this.y = space.getY();
        setValidMoves(player);
        Board.getBoard().get(this.x).get(this.y).setPiece(this.name);
        Board.getBoard().get(this.x).get(this.y).setOccupied(true);

        if (castling){
            //finds castling rook
            for (int i = 0; i < player.pieces.size(); i++){
                if (player.pieces.get(i).name.charAt(1) == 'R'){
                    Rook rook = (Rook)player.pieces.get(i);
                    if (rook.getY() - space.getY() < 0){
                        rook.space.setOccupied(false);
                        rook.space = Board.getBoard().get(rook.getX()).get(3);
                        rook.setY(rook.space.getY());
                        Board.getBoard().get(rook.getX()).get(rook.getY()).setPiece(rook.name);
                        Board.getBoard().get(rook.getX()).get(rook.getY()).setOccupied(true);
                    }
                    else {
                        rook.space.setOccupied(false);
                        rook.space = Board.getBoard().get(rook.getX()).get(5);
                        rook.setY(rook.space.getY());
                        Board.getBoard().get(rook.getX()).get(rook.getY()).setPiece(rook.name);
                        Board.getBoard().get(rook.getX()).get(rook.getY()).setOccupied(true);
                    }
                }
            }
        }

        return true;
    }

    /**
     * Gets the name of the piece
     * @return the name of the piece
     */
    public String getName(){
        return this.name;
    }

    /**
     * gets valid moves
     * @return the array of valid moves
     */
    public ArrayList<Space> getValidMoves(){
        return validMoves;
    }
}
