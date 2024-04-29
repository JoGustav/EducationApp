package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.InterestTestAnswersDao;
import com.example.educationapp.data.model.entities.InterestTestAnswers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InterestTestAnswersRepository {
    private InterestTestAnswersDao interestTestAnswersDao;
    private ExecutorService executorService;

    // Конструктор репозитория
    public InterestTestAnswersRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        interestTestAnswersDao = database.interestTestAnswersDao();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(InterestTestAnswers interestTestAnswers) {
        executorService.execute(() -> interestTestAnswersDao.insert(interestTestAnswers));
    }

    // Операция обновления проекта
    public void update(InterestTestAnswers interestTestAnswers) {
        executorService.execute(() -> interestTestAnswersDao.update(interestTestAnswers));
    }

    // Операция удаления проекта
    public void delete(InterestTestAnswers interestTestAnswers) {
        executorService.execute(() -> interestTestAnswersDao.delete(interestTestAnswers));
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}