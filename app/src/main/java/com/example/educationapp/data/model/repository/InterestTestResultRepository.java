package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.InterestTestResultDao;
import com.example.educationapp.data.model.entities.InterestTestResult;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InterestTestResultRepository {
    private InterestTestResultDao interestTestResultDao;
    private ExecutorService executorService;

    // Конструктор репозитория
    public InterestTestResultRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        interestTestResultDao = database.interestTestResultDao();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(InterestTestResult interestTestResult) {
        executorService.execute(() -> interestTestResultDao.insert(interestTestResult));
    }

    // Операция обновления проекта
    public void update(InterestTestResult interestTestResult) {
        executorService.execute(() -> interestTestResultDao.update(interestTestResult));
    }

    // Операция удаления проекта
    public void delete(InterestTestResult interestTestResult) {
        executorService.execute(() -> interestTestResultDao.delete(interestTestResult));
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}