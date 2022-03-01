package ca.cmpt276.as3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;

import ca.cmpt276.as3.databinding.ActivityMainBinding;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * MainActivity class welcomes to user to the game
 * and allows them to click a button which leads
 * to the main menu.
 */
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    Button skipMenu;
    TextView author;
    Animation animateBtn,animateTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        skipMenu = findViewById(R.id.mainMenuBtn);
        animateBtn = AnimationUtils.loadAnimation(MainActivity.this,R.anim.blink_anim);
        skipMenu.startAnimation(animateBtn);

        author = findViewById(R.id.name);
        animateTxt = AnimationUtils.loadAnimation(MainActivity.this,R.anim.sample_anim);
        author.startAnimation(animateTxt);

        //https://www.youtube.com/watch?v=fqU4zc_XeX0 including animations used
        skipMenu.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainMenu.class);
            startActivity(intent);
            finish();
        });
    }
}


