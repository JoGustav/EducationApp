package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.DirectionDao;
import com.example.educationapp.data.model.entities.Direction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirectionRepository {
    private DirectionDao directionDao;
    private LiveData<List<Direction>> allDirections;
    private ExecutorService executorService;

    // Конструктор репозитория
    public DirectionRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        directionDao = database.directionDao();
        allDirections = directionDao.getAllDirections();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(Direction direction) {
        executorService.execute(() -> directionDao.insert(direction));
    }

    // Операция обновления проекта
    public void update(Direction direction) {
        executorService.execute(() -> directionDao.update(direction));
    }

    // Операция удаления проекта
    public void delete(Direction direction) {
        executorService.execute(() -> directionDao.delete(direction));
    }

    // Получение всех проектов
    public LiveData<List<Direction>> getAllDirections() {
        return allDirections;
    }


    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}