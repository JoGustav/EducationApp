package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.StageDao;
import com.example.educationapp.data.model.entities.Stage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StageRepository {
    private StageDao stageDao;
    private LiveData<List<Stage>> stages;
    private ExecutorService executorService;

    // Конструктор репозитория
    public StageRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        stageDao = database.stageDao();
        stages = stageDao.getAllStages();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(Stage stage) {
        executorService.execute(() -> stageDao.insert(stage));
    }

    // Операция обновления проекта
    public void update(Stage stage) {
        executorService.execute(() -> stageDao.update(stage));
    }

    // Операция удаления проекта
    public void delete(Stage stage) {
        executorService.execute(() -> stageDao.delete(stage));
    }

    public LiveData<Integer> getCountStagesForCourse(int courseID) {
        return stageDao.getCountStagesForCourse(courseID);
    }

    public LiveData<List<Stage>> getAllStagesForCourse(int courseID) {
        return stageDao.getAllStagesForCourse(courseID);
    }

    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}