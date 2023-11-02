package edu.rutgers.chess.game;

import android.app.AlertDialog;
import android.widget.EditText;
import androidx.navigation.fragment.NavHostFragment;
import edu.rutgers.chess.R;
import edu.rutgers.chess.databinding.GameScreenBinding;
import edu.rutgers.chess.game.board.Board;
import edu.rutgers.chess.game.player.Color;
import edu.rutgers.chess.game.player.Player;
import edu.rutgers.chess.game.player.Status;
import edu.rutgers.chess.storage.GameData;
import edu.rutgers.chess.storage.SaveData;
import edu.rutgers.chess.ui.GameScreen;
import edu.rutgers.chess.ui.board.ChessBoardView;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Game implements Runnable {

	private GameData data;
	private Board board;
	private Player white;
	private Player black;
	private Status gameStatus;
	private final ChessBoardView boardView;
	private GameScreen screen;
	private Player currentPlayer;
	private GameScreenBinding binding;
	private final Object inputLock = new Object();

	public Game(@NotNull ChessBoardView boardView, @NotNull GameScreen screen, GameScreenBinding binding) {
		binding.drawButton.setOnClickListener(view -> {
			AlertDialog.Builder builder = new AlertDialog.Builder(screen.getContext());
			builder.setTitle("Draw");
			builder.setMessage("Are you sure you want to draw?");
			builder.setPositiveButton("Yes", (dialog, which) -> {
				gameStatus = Status.DRAW;
				dialog.dismiss();
				gameOverHandler();
			});
			builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
			builder.show();
		});

		binding.ConcedeButton.setOnClickListener(view -> {
			AlertDialog.Builder builder = new AlertDialog.Builder(screen.getContext());
			builder.setTitle("Concede");
			builder.setMessage("Are you sure you want to concede?");
			builder.setPositiveButton("Yes", (dialog, which) -> {
				gameStatus = currentPlayer == white ? Status.BLACK_WON : Status.WHITE_WON;
				dialog.dismiss();
				gameOverHandler();
			});
			builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
			builder.show();
		});
		this.boardView = boardView;
		this.screen = screen;
		this.binding = binding;

		run();
	}

	@Override
	public void run() {  //Run a new game
		makeGame();
		do {
			screen.updateTurnLabel("White's Turn");
			boardView.updateBoard(board);
			currentPlayer = white;
			do {  //Whites Turn
				String Whiteinput = waitForInput() + " " + waitForInput();
				if (Whiteinput.length() < 5) {
					screen.updateTurnLabel("Invalid Input Try Again White's Turn");
					continue;
				}
			gameStatus = white.move(Whiteinput);
				if (gameStatus == Status.MOVE_FAILED) {
					screen.updateTurnLabel("Invalid Input Try Again White's Turn");
					continue;
				}
				boardView.updateBoard(board);
				if (gameOverHandler()) return;
				break;
			} while (true);
			screen.updateTurnLabel("Black's Turn");
			currentPlayer = black;
			do {  //Blacks Turn
				String Blackinput = waitForInput() + " " + waitForInput();
				if (Blackinput.length() < 5) {
					screen.updateTurnLabel("Invalid Input Try Again Black's Turn");
					continue;
				}
				gameStatus = black.move(Blackinput);
				if (gameStatus == Status.MOVE_FAILED) {
					screen.updateTurnLabel("Invalid Input Try Again Black's Turn");
					continue;
				}
				boardView.updateBoard(board);
				if (gameOverHandler()) return;
				break;
			} while (true);
		} while (!gameOverHandler());
	}

	/**
	 * Creates the board and players
	 */
	public void makeGame() {
		this.board = new Board();
		this.white = new Player(Color.WHITE, board);
		this.black = new Player(Color.BLACK, board);
		data = new GameData("New Game", board, white, black);
		gameStatus = Status.IN_PROGRESS;
	}

	/**
	 * Loads the board and players from the data
	 * @param data the data to load from
	 */
	public void loadGame(GameData data) {
		this.board = data.getBoard();
		this.white = data.getWhite();
		this.black = data.getBlack();
	}

	/**
	 * Checks if the game is over
	 * Prompts the user to save the game if it is over
	 * @return true if the game is over
	 */
	public boolean gameOverHandler() {
		//Check Game Status then prompt user to save the game or not
		AlertDialog.Builder builder = new AlertDialog.Builder(screen.getContext());
		switch (gameStatus) {
			case IN_PROGRESS:
			case MOVE_FAILED:
				return false;
			case DRAW:
				builder.setMessage("The game is a draw. Do you want to save the game?")
						.setCancelable(false)
						.setPositiveButton("Yes", (dialog, id) -> {
							saveGame(builder);
							dialog.dismiss();
						})
						.setNegativeButton("No", (dialog, id) -> dialog.dismiss());
				builder.create().show();
				break;
			case BLACK_WON:
				builder.setMessage("Black won!. Do you want to save the game?")
						.setCancelable(false)
						.setPositiveButton("Yes", (dialog, id) -> {
							saveGame(builder);
							dialog.dismiss();
						})
						.setNegativeButton("No", (dialog, id) -> dialog.dismiss());
				builder.create().show();
				break;
			case WHITE_WON:
				builder.setMessage("White won!. Do you want to save the game?")
						.setCancelable(false)
						.setPositiveButton("Yes", (dialog, id) -> {
							saveGame(builder);
							dialog.dismiss();
						})
						.setNegativeButton("No", (dialog, id) -> {
							dialog.dismiss();
							saveGame(builder);
						});
				builder.create().show();
				break;
		}
		NavHostFragment.findNavController(screen).navigate(R.id.action_SecondFragment_to_FirstFragment);
		return true;
	}

	private void saveGame(AlertDialog.Builder builder) {
		final EditText input = new EditText(screen.getContext());
		builder.setView(input);
		String name = input.getText().toString();
		if (name.isEmpty()) name = "New Game";
		data.setName(name);
		GameData.games.add(data);
		try {
			SaveData.writeUserData();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String waitForInput() {
		// Wait for user to click on the chessboard
		StringBuilder sb = new StringBuilder();

		boardView.setOnSquareTouchListener((row, col) -> {
			sb.append(rowToChar(row)).append(col);
			screen.updateTurnLabel("Selected: " + rowToChar(row) + col);
		});
		try {
			inputLock.wait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		// Reset the touch listener
		boardView.setOnSquareTouchListener(null);
		return sb.toString();
	}

	private char rowToChar(int row) {
		return (char) (row + 'a');
	}

}
