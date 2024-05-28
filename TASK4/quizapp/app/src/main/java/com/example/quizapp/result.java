package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class result extends AppCompatActivity {

    TextView tvScore;
    LinearLayout resultLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore = findViewById(R.id.tvScore);
        resultLayout = findViewById(R.id.resultLayout);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        ArrayList<String> userAnswers = intent.getStringArrayListExtra("userAnswers");
        String[] questions = intent.getStringArrayExtra("questions");
        String[] answers = intent.getStringArrayExtra("answers");

        tvScore.setText("Your score is: " + score);

        for (int i = 0; i < questions.length; i++) {
            // Create TextView for the question
            TextView tvQuestion = new TextView(this);
            tvQuestion.setText((i + 1) + ". " + questions[i]);
            // Set layout parameters for the question TextView
            RelativeLayout.LayoutParams questionParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            questionParams.setMargins(0, 0, 0, 20); // Bottom margin to separate from the next TextView
            tvQuestion.setLayoutParams(questionParams);
            resultLayout.addView(tvQuestion);

            // Create TextView for the user's answer
            TextView tvUserAnswer = new TextView(this);
            String userAnswer = userAnswers.get(i);
            if (userAnswer.equals("")) {
                userAnswer = "No answer selected";
            }
            tvUserAnswer.setText("Your answer: " + userAnswer);
            // Set layout parameters for the user's answer TextView
            RelativeLayout.LayoutParams userAnswerParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            userAnswerParams.setMargins(0, 0, 0, 10); // Bottom margin to separate from the next TextView
            tvUserAnswer.setLayoutParams(userAnswerParams);
            resultLayout.addView(tvUserAnswer);

            // Create TextView for the correct answer
            TextView tvCorrectAnswer = new TextView(this);
            tvCorrectAnswer.setText("Correct answer: " + answers[i]);
            // Set layout parameters for the correct answer TextView
            RelativeLayout.LayoutParams correctAnswerParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            correctAnswerParams.setMargins(0, 0, 0, 30);// Bottom margin to separate from the next question
            tvQuestion.setTextColor(Color.parseColor("#FFFFFF"));
            tvUserAnswer.setTextColor(Color.parseColor("#FFFFFF"));
            tvCorrectAnswer.setTextColor(Color.parseColor("#FFFFFF"));
            tvCorrectAnswer.setLayoutParams(correctAnswerParams);
            resultLayout.addView(tvCorrectAnswer);

            // Indicate correct or wrong answer
            if (userAnswer.equals(answers[i])) {
                tvUserAnswer.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                tvUserAnswer.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        }

    }
}
