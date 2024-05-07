package com.example.educationapp.data.model.viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.educationapp.data.model.entities.InterestTestAnswers;
import com.example.educationapp.data.model.entities.InterestTestQuestions;
import com.example.educationapp.data.model.repository.InterestTestRepository;

import java.util.List;

public class InterestTestViewModel extends ViewModel {
    private InterestTestRepository repository;
    private LiveData<List<InterestTestQuestions>> allQuestions;
    private MutableLiveData<InterestTestQuestions> currentQuestion = new MutableLiveData<>();
    private int currentQuestionIndex = 0;

    public InterestTestViewModel(Application application) {
        repository = new InterestTestRepository(application);
        allQuestions = repository.getAllQuestions();

        allQuestions.observeForever(new Observer<List<InterestTestQuestions>>() {
            @Override
            public void onChanged(List<InterestTestQuestions> questions) {
                if (questions != null && !questions.isEmpty()) {
                    loadNextQuestion();
                }
            }
        });
    }

    public LiveData<List<InterestTestQuestions>> getAllQuestions() {
        return allQuestions;
    }

    public void saveAnswer(InterestTestAnswers answer) {
        repository.insertAnswer(answer);
    }

    public LiveData<InterestTestQuestions> getCurrentQuestion() {

        return currentQuestion;
    }



    public void loadNextQuestion() {
        List<InterestTestQuestions> questions = allQuestions.getValue();
        if (questions != null && currentQuestionIndex < questions.size()) {
            currentQuestion.setValue(questions.get(currentQuestionIndex));
            currentQuestionIndex++;
        } else {
            // Завершение теста или обработка отсутствия данных
            currentQuestion.setValue(null);
        }
    }
}
