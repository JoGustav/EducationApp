package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.InterestTestQuestionsDao;
import com.example.educationapp.data.model.entities.InterestTestQuestions;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InterestTestQuestionsRepository {
    private InterestTestQuestionsDao interestTestQuestionsDao;
    private ExecutorService executorService;

    // Конструктор репозитория
    public InterestTestQuestionsRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        interestTestQuestionsDao = database.interestTestQuestionsDao();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(InterestTestQuestions interestTestQuestions) {
        executorService.execute(() -> interestTestQuestionsDao.insert(interestTestQuestions));
    }

    // Операция обновления проекта
    public void update(InterestTestQuestions interestTestQuestions) {
        executorService.execute(() -> interestTestQuestionsDao.update(interestTestQuestions));
    }

    // Операция удаления проекта
    public void delete(InterestTestQuestions interestTestQuestions) {
        executorService.execute(() -> interestTestQuestionsDao.delete(interestTestQuestions));
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}