package edu.rutgers.chess.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import edu.rutgers.chess.R;
import edu.rutgers.chess.databinding.HomeScreenBinding;
import org.jetbrains.annotations.NotNull;

public class HomeScreen extends Fragment {

	private HomeScreenBinding binding;

	@Override
	public View onCreateView(
			@NotNull LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {

		binding = HomeScreenBinding.inflate(inflater, container, false);
		return binding.getRoot();

	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		binding.newGame.setOnClickListener(view1 -> NavHostFragment.findNavController(HomeScreen.this)
				.navigate(R.id.action_FirstFragment_to_SecondFragment));

		binding.viewGames.setOnClickListener(view1 -> NavHostFragment.findNavController(HomeScreen.this)
				.navigate(R.id.action_Home_to_ViewGames));
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

}