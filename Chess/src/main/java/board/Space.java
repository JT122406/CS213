package board;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * This class represents a space on the board
 */
public class Space {
    private boolean isOccupied;
    private int x;
    private int y;
    private String piece;
    private String name;

    /**
     * Default constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param isOccupied whether or not the space is occupied
     * @param piece the piece occupying the space
     */
    public Space(int x, int y, boolean isOccupied, String piece) {
        this.x = x;
        this.y = y;
        this.isOccupied = isOccupied;
        this.piece = piece;
        this.name = Board.nameBoard[x][y];
    }

    /**
     * Returns whether or not the space is occupied
     * @return isOccupied
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Sets whether or not the space is occupied
     * @param isOccupied sets isOccupied
     */
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * Returns the x coordinate
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the piece occupying the space
     * @return piece
     */
    public String getPiece() {
        return piece;
    }

    /**
     * Sets the piece occupying the space
     * @param piece sets piece
     */
    public void setPiece(String piece) {
        this.piece = piece;
    }

    /**
     * Returns the name of the piece
     * @return name
     */
    public String getName(){
        return name;
    }

}
