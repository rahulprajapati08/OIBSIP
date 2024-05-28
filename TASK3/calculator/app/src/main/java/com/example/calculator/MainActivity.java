package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder input;
    private double operand1 = Double.NaN;
    private double operand2;
    private char currentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        input = new StringBuilder();

        // Set up button listeners
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        int[] buttonIds = new int[] {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnDot, R.id.btnEquals, R.id.btnClear, R.id.btnBackspace
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(buttonClickListener);
        }
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            String buttonText = button.getText().toString();

            switch (buttonText) {
                case "C":
                    clear();
                    break;
                case "=":
                    calculate();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    setOperation(buttonText.charAt(0));
                    break;
                case "back":
                    backspace();
                    break;
                default:
                    appendInput(buttonText);
                    break;
            }
        }
    };

    private void appendInput(String text) {
        input.append(text);
        display.setText(input.toString());
    }

    private void clear() {
        input.setLength(0);
        operand1 = Double.NaN;
        operand2 = Double.NaN;
        currentOperation = '\0';
        display.setText("");
    }

    private void setOperation(char operation) {
        if (!Double.isNaN(operand1)) {
            calculate();
        } else {
            operand1 = Double.parseDouble(input.toString());
        }
        currentOperation = operation;
        input.setLength(0);
    }

    private void calculate() {
        if (currentOperation != '\0') {
            operand2 = Double.parseDouble(input.toString());
            switch (currentOperation) {
                case '+':
                    operand1 += operand2;
                    break;
                case '-':
                    operand1 -= operand2;
                    break;
                case '*':
                    operand1 *= operand2;
                    break;
                case '/':
                    if (operand2 != 0) {
                        operand1 /= operand2;
                    } else {
                        display.setText("Error");
                        clear();
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(operand1));
            input.setLength(0);
            input.append(operand1);
            currentOperation = '\0';
        }
    }

    private void backspace() {
        if (input.length() > 0) {
            input.deleteCharAt(input.length() - 1);
            display.setText(input.toString());
        }
    }
}
