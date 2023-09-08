package player;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Enum for the status of the game
 */
public enum Status {
    /**
     * White won
     */
    WHITE_WON,
    /**
     * Black won
     */
    BLACK_WON,
    /**
     * Game is a draw
     */
    DRAW,
    /**
     * Game is in progress
     */
    IN_PROGRESS,
    /**
     * Move was successful
     */
    MOVE_FAILED
}
