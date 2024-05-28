package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvQuestion;
    RadioGroup rgOptions;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    Button btnNext;

    int currentQuestionIndex = 0;
    int score = 0;
    ArrayList<String> userAnswers = new ArrayList<>();

    String[] questions = {
            "What is the capital of France?",
            "What is 2 + 2?",
            "What is the capital of Japan?"
    };

    String[][] options = {
            {"Berlin", "Madrid", "Paris", "Lisbon"},
            {"3", "4", "5", "6"},
            {"Beijing", "Seoul", "Tokyo", "Bangkok"}
    };

    String[] answers = {"Paris", "4", "Tokyo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);
        btnNext = findViewById(R.id.btnNext);

        loadQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    loadQuestion();
                } else {
                    Intent intent = new Intent(MainActivity.this, result.class);
                    intent.putExtra("score", score);
                    intent.putStringArrayListExtra("userAnswers", userAnswers);
                    intent.putExtra("questions", questions);
                    intent.putExtra("answers", answers);
                    startActivity(intent);
                }
            }
        });
    }

    private void loadQuestion() {
        tvQuestion.setText(questions[currentQuestionIndex]);
        rbOption1.setText(options[currentQuestionIndex][0]);
        rbOption2.setText(options[currentQuestionIndex][1]);
        rbOption3.setText(options[currentQuestionIndex][2]);
        rbOption4.setText(options[currentQuestionIndex][3]);
        rgOptions.clearCheck();
    }

    private void checkAnswer() {
        int selectedOptionId = rgOptions.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedOptionId);
        if (selectedRadioButton != null) {
            String selectedAnswer = selectedRadioButton.getText().toString();
            userAnswers.add(selectedAnswer);
            if (selectedAnswer.equals(answers[currentQuestionIndex])) {
                score++;
            }
        } else {
            userAnswers.add("");  // No answer selected
        }
    }
}
