package ca.cmpt276.as3;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.navigation.ui.AppBarConfiguration;

import ca.cmpt276.as3.Model.GameLogic;
import ca.cmpt276.as3.databinding.ActivityOptionsBinding;

/**
 * Options class creates the option buttons where the user
 * can choose what size game they want to play with and
 * the amount of logos.
 */
public class Options extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityOptionsBinding binding;
    GameLogic gameLogic;
    private static final String LOGO_PREFS = "AppPrefs";
    private static final String GAME_PREFS = "AppPrefs";
    private static final String LOGO_AMOUNT = "Num of logos";
    private static final String GAME_SIZE = "Game board size";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        gameLogic = GameLogic.getInstance();
        createRadioButtons();
    }

    private void createRadioButtons() {

        RadioGroup group = findViewById(R.id.game_size);
        String[] gameSize = getResources().getStringArray(R.array.game_size);
        RadioGroup logoGroup = findViewById(R.id.logo_amount);
        int[] logoAmount = getResources().getIntArray(R.array.logo_amount);

        for (String sizeGame : gameSize) {
            RadioButton button = new RadioButton(this);
            button.setTextColor(Color.YELLOW);
            button.setText("Game Size: " + sizeGame);

            button.setOnClickListener(view -> {
                saveGameSize(sizeGame);
                gameLogic.findRowsAndCols(sizeGame);
            });
            group.addView(button);

            if (sizeGame.equals(getGameSize(this))) {
                button.setChecked(true);
            }
        }

        for (int amountOfLogos : logoAmount) {
            RadioButton button = new RadioButton(this);
            button.setTextColor(Color.YELLOW);
            button.setText(amountOfLogos + " logos");

            button.setOnClickListener(view -> {
                saveLogoAmount(amountOfLogos);
                gameLogic.findNumLogos(amountOfLogos);
            });
            logoGroup.addView(button);

            if (amountOfLogos == getLogoAmount(this)) {
                button.setChecked(true);
            }
        }
    }

    private void saveLogoAmount(int amountOfLogos) {
        SharedPreferences prefs = this.getSharedPreferences(LOGO_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(LOGO_AMOUNT, amountOfLogos);
        editor.apply();
    }

    private void saveGameSize(String sizeGame) {
        SharedPreferences prefs = this.getSharedPreferences(GAME_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(GAME_SIZE, sizeGame);
        editor.apply();
    }

    static public int getLogoAmount(Context context){
        SharedPreferences prefs = context.getSharedPreferences(LOGO_PREFS, MODE_PRIVATE);
        int defaultLogoAmount = context.getResources().getInteger(R.integer.default_logo_amount);
        return prefs.getInt(LOGO_AMOUNT, defaultLogoAmount);
    }

    static public String getGameSize(Context context){
        SharedPreferences prefs = context.getSharedPreferences(GAME_PREFS, MODE_PRIVATE);
        String defaultGameSize = context.getResources().getString(R.string.default_game_size);
        return prefs.getString(GAME_SIZE, defaultGameSize);
    }
}