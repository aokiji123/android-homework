package com.example.a08_homework;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a08_homework.R;

public class MainActivity extends AppCompatActivity {

    private Button btnStartStop, btnCancel;
    private ProgressBar progressBar;
    private TextView txtStatus;
    private EditText edtIterations, edtDelay;
    private MyAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartStop = findViewById(R.id.btnStartStop);
        btnCancel = findViewById(R.id.btnCancel);
        progressBar = findViewById(R.id.progressBar);
        txtStatus = findViewById(R.id.txtStatus);
        edtIterations = findViewById(R.id.edtIterations);
        edtDelay = findViewById(R.id.edtDelay);

        btnCancel.setEnabled(false);

        btnStartStop.setOnClickListener(v -> {
            if (asyncTask == null || asyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                startTask();
            } else {
                stopTask();
            }
        });

        btnCancel.setOnClickListener(v -> {
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
        });
    }

    private void startTask() {
        int iterations = 100;
        int delay = 300;

        try {
            iterations = Integer.parseInt(edtIterations.getText().toString());
            delay = Integer.parseInt(edtDelay.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Using default values.", Toast.LENGTH_SHORT).show();
        }

        asyncTask = new MyAsyncTask(iterations, delay);
        asyncTask.execute();
        btnStartStop.setText("Stop");
        btnCancel.setEnabled(true);
    }

    private void stopTask() {
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        btnStartStop.setText("Start");
        btnCancel.setEnabled(false);
        txtStatus.setText("Stopped");
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        private int iterations;
        private int delay;

        public MyAsyncTask(int iterations, int delay) {
            this.iterations = iterations;
            this.delay = delay;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtStatus.setText("Running");
            progressBar.setProgress(0);
            progressBar.setMax(iterations);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 1; i <= iterations; i++) {
                if (isCancelled()) {
                    break;
                }

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int progress = values[0];
            progressBar.setProgress(progress);
            txtStatus.setText("Progress: " + (progress * 100 / iterations) + "%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txtStatus.setText("Finished");
            btnStartStop.setText("Start");
            btnCancel.setEnabled(false);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            txtStatus.setText("Cancelled");
            btnStartStop.setText("Start");
            btnCancel.setEnabled(false);
        }
    }
}
