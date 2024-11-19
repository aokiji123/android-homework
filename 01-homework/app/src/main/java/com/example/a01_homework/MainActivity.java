package com.example.a01_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private long timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timestamp = System.currentTimeMillis();
        Log.d(TAG, "onCreate called. Timestamp: " + timestamp);

        Button button = findViewById(R.id.myButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button clicked!");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        long currentTime = System.currentTimeMillis();
        Log.d(TAG, "onStart called. Time since onCreate: " + (currentTime - timestamp) + " ms");
        timestamp = currentTime;
    }

    @Override
    protected void onResume() {
        super.onResume();
        long currentTime = System.currentTimeMillis();
        Log.d(TAG, "onResume called. Time since onStart: " + (currentTime - timestamp) + " ms");
        timestamp = currentTime;
    }

    @Override
    protected void onPause() {
        super.onPause();
        long currentTime = System.currentTimeMillis();
        Log.d(TAG, "onPause called. Time since onResume: " + (currentTime - timestamp) + " ms");
        timestamp = currentTime;
    }

    @Override
    protected void onStop() {
        super.onStop();
        long currentTime = System.currentTimeMillis();
        Log.d(TAG, "onStop called. Time since onPause: " + (currentTime - timestamp) + " ms");
        timestamp = currentTime;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        long currentTime = System.currentTimeMillis();
        Log.d(TAG, "onDestroy called. Time since onStop: " + (currentTime - timestamp) + " ms");
    }
}
