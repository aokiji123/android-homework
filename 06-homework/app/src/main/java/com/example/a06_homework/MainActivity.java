package com.example.a06_homework;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = findViewById(R.id.editText);
        calendar = Calendar.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_edit_text) {
            showEditTextDialog();
            return true;
        } else if (id == R.id.menu_select_text) {
            showSelectTextDialog();
            return true;
        } else if (id == R.id.menu_get_time) {
            showCurrentTimeDialog();
            return true;
        } else if (id == R.id.menu_change_date) {
            changeDate();
            return true;
        } else if (id == R.id.menu_trigger_notification) {
            triggerNotification();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showEditTextDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_text, null);
        final EditText dialogEditText = dialogView.findViewById(R.id.dialogEditText);

        new AlertDialog.Builder(this)
                .setTitle("Edit Text")
                .setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> {
                    String text = dialogEditText.getText().toString();
                    if (!text.isEmpty()) {
                        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                                .format(calendar.getTime());
                        editText.setText(text + "\nUpdated: " + timestamp);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showSelectTextDialog() {
        String[] options = {"Option 1", "Option 2", "Option 3"};
        new AlertDialog.Builder(this)
                .setTitle("Select Text")
                .setItems(options, (dialog, which) -> editText.setText("Selected: " + options[which]))
                .show();
    }

    private void showCurrentTimeDialog() {
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
        Toast.makeText(this, "Current Time: " + currentTime, Toast.LENGTH_SHORT).show();
    }

    private void changeDate() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            calendar.set(year1, month1, dayOfMonth);
            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.getTime());
            editText.setText("Date Changed: " + date);
            Toast.makeText(this, "Date Changed: " + date, Toast.LENGTH_SHORT).show();
        }, year, month, day).show();
    }

    private void triggerNotification() {
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
            Toast.makeText(this, "Notification: " + text + " at " + currentTime, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Text field is empty!", Toast.LENGTH_SHORT).show();
        }
    }
}
