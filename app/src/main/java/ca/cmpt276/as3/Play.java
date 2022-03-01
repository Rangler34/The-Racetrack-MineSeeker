package ca.cmpt276.as3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Vibrator;


import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import androidx.fragment.app.FragmentManager;

import androidx.navigation.ui.AppBarConfiguration;


import java.util.Random;


import ca.cmpt276.as3.Model.GameLogic;
import ca.cmpt276.as3.databinding.ActivityPlayBinding;

/**
 * Play class dynamically builds the game. Creates buttons,
 * allows user to scan and play the game. Once the game is won
 * a message pops up congratulating the user and they can click it
 * to go to the main menu.
 */
public class Play extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPlayBinding binding;
    private GameLogic gameLogic;
    private static int numRows;
    private static int numCols;
    private int numberLogos;
    private int numFoundLogos;
    private int numScans = 0;
    Button button;
    Button[][] buttons;
    String[][] temp;
    Bitmap originalBitmap;
    Bitmap scaledBitmap;
    TextView foundText, scanText;
    Vibrator vibrator;
    MediaPlayer foundSound,scanSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        gameLogic = GameLogic.getInstance();
        numRows = gameLogic.getRows();
        numCols = gameLogic.getColumns();
        buttons = new Button[numRows][numCols];
        temp = new String[numRows][numCols];
        numberLogos = gameLogic.getNumLogos();
        numFoundLogos = gameLogic.getLogosFound();
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        foundSound = MediaPlayer.create(this,R.raw.right);
        scanSound = MediaPlayer.create(this,R.raw.wrong);


        populateButtons(buttons);
        randomizedLogos();
        updateText();


    }

    private void populateButtons(Button[][] buttons) {
        TableLayout table = findViewById(R.id.tableButtons);


        for (int row = 0; row < numRows; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < numCols; col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));


                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(v -> gridButtonClicked(FINAL_COL, FINAL_ROW));

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int col, int row) {

        Button button = buttons[row][col];
        lockButtonSizes();
        scanForLogos();
        if(button.getText().toString().equals(" ")){
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            lockButtonSizes();
            originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.winnericon);
            scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));


            //https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate-with-different-frequency
            //vibration help
            vibrateOne();

            //https://www.youtube.com/watch?v=9oj4f8721LM
            //sound help
            foundSound.start();

            if(button.getText().toString().equals(" ")){
                numFoundLogos++;
                button.setText("  ");
            }
        }

        else{
            buttons[row][col].setText(temp[row][col]);
            numScans++;
            vibrateTwo();
            scanSound.start();
        }

        displayWinText();
        updateText();
        updateButtonNumber();

    }

    private void vibrateOne(){
        long[] pattern = {0, 300, 50, 300, 1000};
        vibrator.vibrate(pattern,-1);
    }

    private void vibrateTwo(){
        long[] pattern = {0, 400, 1000};
        vibrator.vibrate(pattern,-1);
    }

    private void lockButtonSizes() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void randomizedLogos(){
        Random random = new Random();

        int logoCount = 0;
        while (logoCount < numberLogos) {
            button = buttons[random.nextInt(numRows)][random.nextInt(numCols)];
            if (button.getText() != " ") {
                button.setText(" ");

            }
            else{
                logoCount--;
            }
            logoCount++;
        }
    }

    private void scanForLogos(){

        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols; col++){
                if(!buttons[row][col].getText().toString().equals(" ")){
                    addUpLogos(row, col);
                }
            }
        }
    }

    private void addUpLogos(int row, int col){

        int number = 0;
        int currCol = 0;
        int currRow = 0;

        while(currCol < numCols){
            if(buttons[row][currCol].getText().toString().equals(" ")){
                number++;
            }
            currCol++;
        }

        while(currRow < numRows){
            if(buttons[currRow][col].getText().toString().equals(" ")){
                number++;
            }

            currRow++;
        }

        String numberOfButton = String.valueOf(number);
        temp[row][col] = numberOfButton;
    }

    private void displayWinText(){
        if(numFoundLogos == numberLogos){
            FragmentManager manager =  getSupportFragmentManager();
            MessageFragment dialog = new MessageFragment();
            dialog.show(manager, "MessageDialog");
        }
    }

    private void updateText(){
        foundText = findViewById(R.id.logosFound);
        scanText = findViewById(R.id.numOfScansUsed);
        foundText.setTextColor(Color.BLACK);
        scanText.setTextColor(Color.BLACK);
        foundText.setText("Found : " + numFoundLogos + " of " + numberLogos + " logos");
        scanText.setText("# Scans Used: " + numScans);
    }

    private void updateButtonNumber(){
        scanForLogos();
        int row = 0;
        while(row < numRows){
            int col = 0;
            while(col < numCols){
                // regex found from https://www.regextutorial.org/regex-for-numbers-and-ranges.php
                if( buttons[row][col].getText().toString().matches("^[0-9]$|^[1-9][0-9]$|^(100)$")){
                    String value = temp[row][col];
                    buttons[row][col].setText(value);
                }
                col++;
            }
            row++;
        }
    }
}






