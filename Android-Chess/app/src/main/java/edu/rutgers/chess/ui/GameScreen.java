package edu.rutgers.chess.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import edu.rutgers.chess.databinding.GameScreenBinding;
import edu.rutgers.chess.game.Game;
import edu.rutgers.chess.ui.board.ChessBoardView;
import org.jetbrains.annotations.NotNull;

public class GameScreen extends Fragment {

	private GameScreenBinding binding;

	@Override
	public View onCreateView(
			@NotNull LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {

		binding = GameScreenBinding.inflate(inflater, container, false);
		return binding.getRoot();

	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		updateTurnLabel("");
		ChessBoardView chessBoardView = binding.board;
		new Thread(() -> new Game(chessBoardView, this, binding)).start();
	}

	public void updateTurnLabel(String turn) {
		binding.turnLabel.setText(turn);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

}