package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner unitFrom;
    private Spinner unitTo;
    private Button convertButton;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        unitFrom = findViewById(R.id.unitFrom);
        unitTo = findViewById(R.id.unitTo);
        convertButton = findViewById(R.id.convertButton);
        resultView = findViewById(R.id.resultView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitFrom.setAdapter(adapter);
        unitTo.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String inputString = inputValue.getText().toString();
        if (inputString.isEmpty()) {
            resultView.setText("Please enter a value");
            return;
        }

        double input = Double.parseDouble(inputString);
        String fromUnit = unitFrom.getSelectedItem().toString();
        String toUnit = unitTo.getSelectedItem().toString();

        double result = 0.0;
        if (fromUnit.equals("Meters") && toUnit.equals("Kilometers")) {
            result = input / 1000;
        } else if (fromUnit.equals("Kilometers") && toUnit.equals("Meters")) {
            result = input * 1000;
        } else if (fromUnit.equals("Meters") && toUnit.equals("Centimeters")) {
            result = input * 100;
        } else if (fromUnit.equals("Centimeters") && toUnit.equals("Meters")) {
            result = input / 100;
        } else if (fromUnit.equals("Inches") && toUnit.equals("Centimeters")) {
            result = input * 2.54;
        } else if (fromUnit.equals("Centimeters") && toUnit.equals("Inches")) {
            result = input / 2.54;
        } else if (fromUnit.equals("Inches") && toUnit.equals("Meters")) {
            result = input * 0.0254;
        } else if (fromUnit.equals("Meters") && toUnit.equals("Inches")) {
            result = input / 0.0254;
        } else if (fromUnit.equals("Kilometers") && toUnit.equals("Centimeters")) {
            result = input * 100000;
        } else if (fromUnit.equals("Centimeters") && toUnit.equals("Kilometers")) {
            result = input / 100000;

            // Temperature conversions
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            result = (input * 9/5) + 32;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            result = (input - 32) * 5/9;

            // Weight conversions
        } else if (fromUnit.equals("Kilograms") && toUnit.equals("Grams")) {
            result = input * 1000;
        } else if (fromUnit.equals("Grams") && toUnit.equals("Kilograms")) {
            result = input / 1000;

            // When fromUnit and toUnit are the same
        } else if (fromUnit.equals(toUnit)) {
            result = input;
        } else {
            resultView.setText("Conversion not supported");
            return;
        }

        resultView.setText(String.format("%.2f", result));
    }
}
