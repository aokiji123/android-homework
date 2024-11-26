package com.example.a03_homework;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultDisplay;
    private String currentNumber = "";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultDisplay = findViewById(R.id.result_display);

        View.OnClickListener listener = view -> {
            Button button = (Button) view;
            String buttonText = button.getText().toString();

            switch (buttonText) {
                case "C":
                    clear();
                    break;
                case "+":
                case "−":
                case "×":
                case "÷":
                    setOperator(buttonText);
                    break;
                case "=":
                    calculate();
                    break;
                default:
                    appendNumber(buttonText);
                    break;
            }
        };

        int[] buttonIds = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
                R.id.button_8, R.id.button_9, R.id.button_add, R.id.button_subtract,
                R.id.button_multiply, R.id.button_divide, R.id.button_clear,
                R.id.button_equals, R.id.button_dot};

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void appendNumber(String number) {
        currentNumber += number;
        resultDisplay.setText(currentNumber);
    }

    private void setOperator(String op) {
        if (!currentNumber.isEmpty()) {
            firstOperand = Double.parseDouble(currentNumber);
            currentNumber = "";
        }
        operator = op;
    }

    private void calculate() {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentNumber);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "−":
                    result = firstOperand - secondOperand;
                    break;
                case "×":
                    result = firstOperand * secondOperand;
                    break;
                case "÷":
                    result = firstOperand / secondOperand;
                    break;
            }

            resultDisplay.setText(String.valueOf(result));
            currentNumber = "";
            operator = "";
        }
    }

    private void clear() {
        currentNumber = "";
        operator = "";
        firstOperand = 0;
        resultDisplay.setText("0");
    }
}