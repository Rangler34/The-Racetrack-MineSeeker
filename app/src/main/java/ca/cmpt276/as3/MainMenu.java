package ca.cmpt276.as3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

import androidx.navigation.ui.AppBarConfiguration;

import ca.cmpt276.as3.Model.GameLogic;
import ca.cmpt276.as3.databinding.ActivityMainMenuBinding;

/**
 * Main Menu class allows the user to select
 * between 3 buttons Play, Options, and Help
 * each of these buttons lead to other activities
 */
public class MainMenu extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainMenuBinding binding;
    GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gameLogic = GameLogic.getInstance();

        setSupportActionBar(binding.toolbar);

        Button playGame = findViewById(R.id.playBtn);
        playGame.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenu.this, Play.class);
            startActivity(intent);
        });

        Button options = findViewById(R.id.optionsBtn);
        options.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenu.this, Options.class);
            startActivity(intent);
        });

        Button help = findViewById(R.id.helpBtn);
        help.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenu.this, Help.class);
            startActivity(intent);
        });

        refreshScreen();

    }

    private void refreshScreen() {
        String gameSize = Options.getGameSize(this);
        int logos = Options.getLogoAmount(this);
        gameLogic.findNumLogos(logos);
        gameLogic.findRowsAndCols(gameSize);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshScreen();
    }

}