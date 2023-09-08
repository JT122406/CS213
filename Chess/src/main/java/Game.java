import board.Board;
import player.Player;
import player.Status;

import java.util.Scanner;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Runs the Game
 */
public class Game implements Runnable {
    private Board board;
    private Player white;
    private Player black;
    private Status gameStatus;

    /**
     * Runs the game
     */
    @Override
    public void run() {
        makeGame();
        Scanner in = new Scanner(System.in);
        do {
            board.printBoard();
            do {
                System.out.print("White's move: ");
                while (true){
                    if(in.hasNextLine()){break;}
                }
                String line = in.nextLine();
                if ((line.length() > 6) || (line.length() < 4) || (line.charAt(2) != ' ')) {
                    System.out.println("Illegal Input, try again");
                    continue;
                }
                if (!line.isBlank()) {
                    gameStatus = white.move(line, white);  //Move Handler

                    //if the player is in check or checkmated after their move, then their move was invalid
                    if(white.isInCheck(black.pieces) || white.isMated(black.pieces)){
                        white.redoMove(line, white);
                        System.out.println("Illegal move, try again ");
                        continue;
                    }
                    if (gameStatus.equals(Status.MOVE_FAILED)){
                        System.out.println("Illegal move, try again ");
                        continue;
                    }
                    //if opponent is checkmated after player moves, they win
                    if (black.isMated(white.pieces)){
                        gameStatus = Status.WHITE_WON;
                        break;
                    }
                    break;
                }
            } while (true);
            
            if (gameOverHandler()){
                break;
            }  //Finishes White's turn

            board.printBoard();
            do {
                System.out.print("Black's move: ");
                while (true){
                    if(in.hasNextLine()){break;}
                }
                String line = in.nextLine();
                if (!line.isEmpty() && !line.isBlank()) {
                    gameStatus = black.move(line, white);
                    
                    //if the player is in check or checkmated after their move, then their move was invalid
                    if (gameStatus.equals(Status.MOVE_FAILED)){
                        System.out.println("Illegal move, try again ");
                        continue;
                    }
                    if(black.isInCheck(white.pieces) || black.isMated(white.pieces)){
                        black.redoMove(line, black);
                        System.out.println("Illegal move, try again ");
                        continue;
                    }
                    
                    //if opponent is checkmated after player moves, they win
                    if (white.isMated(black.pieces)){
                        gameStatus = Status.BLACK_WON;
                        break;
                    }
                    break;
                }
            } while (true);
            
        } while (!gameOverHandler());
        in.close();
    }

    /**
     * Creates the board and players
     */
    public void makeGame() {
        this.board = new Board();
        this.white = Player.initWhitePlayer();
        this.black = Player.initBlackPlayer();
    }

    /**
     * Checks if the game is over
     * @return true if the game is over
     */
    public boolean gameOverHandler() {
        switch (gameStatus) {
            case IN_PROGRESS, MOVE_FAILED -> {return false;}
            case DRAW -> System.out.println("draw");
            case BLACK_WON -> System.out.println("Black wins");
            case WHITE_WON -> System.out.println("White wins");
        }
        return true;
    }
}
