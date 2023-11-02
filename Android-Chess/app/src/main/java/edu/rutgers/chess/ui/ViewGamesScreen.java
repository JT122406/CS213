package edu.rutgers.chess.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import edu.rutgers.chess.R;
import edu.rutgers.chess.databinding.ViewgamesScreenBinding;
import edu.rutgers.chess.storage.SaveData;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ViewGamesScreen extends Fragment {

	private ViewgamesScreenBinding binding;

	@Override
	public View onCreateView(
			@NotNull LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {

		binding = ViewgamesScreenBinding.inflate(inflater, container, false);
		return binding.getRoot();

	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (SaveData.dataExists())
			try {
				SaveData.readUserData();
			} catch (IOException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		else {
			AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
			builder.setTitle("No Games");
			builder.setMessage("You have no saved games.");
			builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
			builder.show();
			NavHostFragment.findNavController(ViewGamesScreen.this)
					.navigate(R.id.action_ViewGames_to_Home);
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}
