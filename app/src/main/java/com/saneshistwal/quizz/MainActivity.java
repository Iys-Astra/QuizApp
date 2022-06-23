package com.saneshistwal.quizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.saneshistwal.quizz.databinding.ActivityMainBinding;
import com.saneshistwal.quizz.model.Question;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    private final Question[] questionsBank = new Question[]
            {
                    new Question(R.string.question_amendments,false),
                    new Question(R.string.question_constitution,true),
                    new Question(R.string.question_declaration,true),
                    new Question(R.string.question_independence_rights,true),
                    new Question(R.string.question_religion,true),
                    new Question(R.string.question_government,false),
                    new Question(R.string.question_government_feds,false),
                    new Question(R.string.question_government_senators,true),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.tvQuestions.setText((questionsBank[currentQuestionIndex].getAnswerResId()));
        binding.btTrue.setOnClickListener(view -> checkAnswer(true));
        binding.btFalse.setOnClickListener(view -> checkAnswer(false));
        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionIndex = (currentQuestionIndex + 1) % questionsBank.length;
                updateQuestion();
            }
        });
        binding.btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestionIndex > 0)
                {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionsBank.length;
                    updateQuestion();
                }
            }
        });

    }
    private void checkAnswer(Boolean selectedAnswer)
    {
        Boolean correctAnswer = questionsBank[currentQuestionIndex].isAnswerTrue();
        int messageId ;

        if(correctAnswer == selectedAnswer)
        {
            Toast.makeText(this, "The answer is correct", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "The answer is incorrect", Toast.LENGTH_SHORT).show();
        }

    }
    private void updateQuestion() {
        binding.tvQuestions.setText(questionsBank[currentQuestionIndex].getAnswerResId());
    }
}