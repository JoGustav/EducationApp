package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.educationapp.data.model.entities.InterestTestAnswers;
import com.example.educationapp.data.model.entities.InterestTestQuestions;
import com.example.educationapp.data.model.viewmodels.InterestTestViewModel;
import com.example.educationapp.data.model.viewmodels.InterestTestViewModelFactory;
import java.util.List;

public class InterestTestActivity extends AppCompatActivity {
    private InterestTestViewModel testViewModel;
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton;
    private InterestTestQuestions currentQuestion;
    private int userId;

    private static final String[] OPTIONS = new String[] {
            "Оцениваемое свойство личности развито хорошо, четко выражено, проявляется часто",
            "Свойство заметно выражено, но проявляется непостоянно",
            "Оцениваемое и противоположное свойство личности выражены не четко, в проявлениях редки, в поведении и деятельности уравновешивают друг друга",
            "Более ярко выражено и чаще проявляется свойство личности противоположное оцениваемому"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_test);
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            userId = sessionManager.getUserId();
        }

        InterestTestViewModelFactory factory = new InterestTestViewModelFactory(getApplication());
        testViewModel = new ViewModelProvider(this, factory).get(InterestTestViewModel.class);


        // Находим view по id
        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        nextButton = findViewById(R.id.nextButton);


        // Подписываемся на LiveData для получения текущего вопроса
        testViewModel.getCurrentQuestion().observe(this, new Observer<InterestTestQuestions>() {
            @Override
            public void onChanged(@Nullable final InterestTestQuestions question) {
                if (question != null) {
                    updateUIWithQuestion(question);
                } else {
                    // Завершение теста
                    finishTest();
                }
            }
        });

        // Загрузка первого вопроса
//        testViewModel.loadNextQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Сохранение выбранного ответа
                saveSelectedAnswer();

                // Загрузка следующего вопроса
                testViewModel.loadNextQuestion();
            }
        });
    }

    private void updateUIWithQuestion(InterestTestQuestions question) {
        // Обновление текста вопроса
        questionTextView.setText(question.getQuestionText());
        currentQuestion = question;

        // Очистка предыдущих вариантов ответов
        optionsRadioGroup.removeAllViews();

        // Получение вариантов ответов для текущего вопроса

        for (int i = 0; i < OPTIONS.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(OPTIONS[i]);
            // Присваиваем тег, соответствующий количеству баллов
            radioButton.setTag(i + 1); // Например, если баллы начинаются с 1
            optionsRadioGroup.addView(radioButton);
        }

    }

    private void saveSelectedAnswer() {
        // Получение выбранного ответа
        int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
//        RadioButton selectedRadioButton = findViewById(selectedOptionId);

        int score = calculateScoreForOption(selectedOptionId);

        if (currentQuestion != null) {
            InterestTestAnswers userAnswer = new InterestTestAnswers(userId, currentQuestion.getQuestionID(), score);
            testViewModel.saveAnswer(userAnswer);
        }
    }

    // Метод для расчета баллов на основе id выбранного RadioButton
    private int calculateScoreForOption(int selectedOptionId) {
        RadioButton selectedRadioButton = findViewById(selectedOptionId);
        if (selectedRadioButton != null) {
            // Получаем тег, который представляет баллы
            int tag = (int) selectedRadioButton.getTag();
            switch (tag) {
                case 1:
                    return 2; // За первый вариант
                case 2:
                    return 1; // За второй вариант
                case 3:
                    return 0; // За третий вариант
                case 4:
                    return -1; // За четвертый вариант
                default:
                    return 0; // Если вариант неизвестен
            }
        }
        return 0; // Если вариант не выбран
    }


    private void finishTest() {
        Intent intent = new Intent(InterestTestActivity.this, ResultInterestTestActivity.class);
        startActivity(intent);
        finish();
    }
}
