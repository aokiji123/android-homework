package com.example.a02_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class GuessByAppActivity extends AppCompatActivity {

    private static final String TAG = "GuessByAppActivity";
    private int min = 1;
    private int max = 100;
    private int guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_by_app);

        TextView guessText = findViewById(R.id.guessText);
        Button higherButton = findViewById(R.id.higherButton);
        Button lowerButton = findViewById(R.id.lowerButton);
        Button correctButton = findViewById(R.id.correctButton);

        makeGuess(guessText);

        higherButton.setOnClickListener(v -> {
            min = guess + 1;
            makeGuess(guessText);
        });

        lowerButton.setOnClickListener(v -> {
            max = guess - 1;
            makeGuess(guessText);
        });

        correctButton.setOnClickListener(v -> {
            guessText.setText("Guessed correctly: " + guess);
            Log.d(TAG, "Correct guess: " + guess);
        });
    }

    private void makeGuess(TextView guessText) {
        guess = (min + max) / 2;
        guessText.setText("Is it " + guess + "?");
        Log.d(TAG, "Guess: " + guess);
    }
}
