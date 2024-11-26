package com.example.a04_homework;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge;
    private SeekBar seekBarSalary;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    private CheckBox checkBoxExperience, checkBoxTeamSkills, checkBoxTravel;
    private Button buttonSubmit;
    private TextView textResult, textSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.edit_text_name);
        editTextAge = findViewById(R.id.edit_text_age);
        seekBarSalary = findViewById(R.id.seek_bar_salary);
        radioGroup1 = findViewById(R.id.radio_group_1);
        radioGroup2 = findViewById(R.id.radio_group_2);
        radioGroup3 = findViewById(R.id.radio_group_3);
        radioGroup4 = findViewById(R.id.radio_group_4);
        radioGroup5 = findViewById(R.id.radio_group_5);
        checkBoxExperience = findViewById(R.id.check_box_experience);
        checkBoxTeamSkills = findViewById(R.id.check_box_team_skills);
        checkBoxTravel = findViewById(R.id.check_box_travel);
        buttonSubmit = findViewById(R.id.button_submit);
        textResult = findViewById(R.id.text_result);
        textSalary = findViewById(R.id.salary_prefix);

        buttonSubmit.setEnabled(false);

        seekBarSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSalary.setText("Зарплата: " + progress + " USD");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        editTextName.addTextChangedListener(textWatcher);
        editTextAge.addTextChangedListener(textWatcher);

        buttonSubmit.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String ageStr = editTextAge.getText().toString();
            if (ageStr.isEmpty()) {
                Toast.makeText(this, "Введіть вік", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            int salary = seekBarSalary.getProgress();

            if (!isValidAge(age)) {
                Toast.makeText(this, "Вік повинен бути від 21 до 40 років", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidSalary(salary)) {
                Toast.makeText(this, "Зарплата повинна бути від 1000 до 5000 USD", Toast.LENGTH_SHORT).show();
                return;
            }

            int score = 0;

            score += getScoreFromRadioGroup(radioGroup1);
            score += getScoreFromRadioGroup(radioGroup2);
            score += getScoreFromRadioGroup(radioGroup3);
            score += getScoreFromRadioGroup(radioGroup4);
            score += getScoreFromRadioGroup(radioGroup5);

            if (checkBoxExperience.isChecked()) score += 2;
            if (checkBoxTeamSkills.isChecked()) score += 1;
            if (checkBoxTravel.isChecked()) score += 1;

            if (score >= 10) {
                textResult.setText("Ви пройшли тест!");
            } else {
                textResult.setText("Нажаль, ви не пройшли тест.");
            }
            textResult.setVisibility(View.VISIBLE);
        });
    }

    private boolean isValidAge(int age) {
        return age >= 21 && age <= 40;
    }

    private boolean isValidSalary(int salary) {
        return salary >= 1000 && salary <= 5000;
    }

    private int getScoreFromRadioGroup(RadioGroup group) {
        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedAnswer = selectedRadioButton.getText().toString().trim();

            if (group == radioGroup1 && selectedAnswer.equals(getString(R.string.answer_high))) {
                return 2;
            } else if (group == radioGroup2 && selectedAnswer.equals(getString(R.string.answer_yes))) {
                return 2;
            } else if (group == radioGroup3 && selectedAnswer.equals(getString(R.string.answer_yes))) {
                return 2;
            } else if (group == radioGroup4 && selectedAnswer.equals(getString(R.string.answer_confident))) {
                return 2;
            } else if (group == radioGroup5 && selectedAnswer.equals(getString(R.string.answer_yes))) {
                return 2;
            }
        }
        return 0;
    }

    private void validateInputs() {
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        buttonSubmit.setEnabled(!name.isEmpty() && !age.isEmpty());
    }
}
