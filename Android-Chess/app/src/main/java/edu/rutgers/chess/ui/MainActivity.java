package edu.rutgers.chess.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.rutgers.chess.R;
import edu.rutgers.chess.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

	private AppBarConfiguration appBarConfiguration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		edu.rutgers.chess.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		setSupportActionBar(binding.toolbar);

		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
		appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
		NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
		return NavigationUI.navigateUp(navController, appBarConfiguration)
				|| super.onSupportNavigateUp();
	}
}