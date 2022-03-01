package ca.cmpt276.as3;

import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.text.method.LinkMovementMethod;

import android.widget.Button;
import android.widget.TextView;


import androidx.navigation.ui.AppBarConfiguration;

import ca.cmpt276.as3.databinding.ActivityHelpBinding;

/**
 * Help class includes information about the game, author, and
 * resources. Code consists of one button which leads to the
 * resource page and a hyperlink to the course website
 */
public class Help extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHelpBinding binding;
    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        Button resources = findViewById(R.id.resourceBtn);
        resources.setOnClickListener(view -> {
            Intent intent = new Intent(Help.this, Resources.class);
            startActivity(intent);
        });

        link = findViewById(R.id.authorText);
        link.setMovementMethod(LinkMovementMethod.getInstance());

    }

}