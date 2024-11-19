package com.example.a02_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class GuessByUserActivity extends AppCompatActivity {

    private static final String TAG = "GuessByUserActivity";
    private int targetNumber;
    private int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_by_user);

        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;

        EditText userInput = findViewById(R.id.userInput);
        TextView feedback = findViewById(R.id.feedbackText);
        Button checkButton = findViewById(R.id.checkButton);

        checkButton.setOnClickListener(v -> {
            int userGuess = Integer.parseInt(userInput.getText().toString());
            attempts++;
            if (userGuess < targetNumber) {
                feedback.setText("Too low!");
            } else if (userGuess > targetNumber) {
                feedback.setText("Too high!");
            } else {
                feedback.setText("Correct! Attempts: " + attempts);
                Log.d(TAG, "Guessed correctly in " + attempts + " attempts.");
            }
        });
    }
}
