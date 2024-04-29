package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.EducationMaterialDao;
import com.example.educationapp.data.model.entities.EducationMaterial;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EducationMaterialRepository {
    private EducationMaterialDao educationMaterialDao;
    private LiveData<List<EducationMaterial>> allEducationMaterials;
    private ExecutorService executorService;

    // Конструктор репозитория
    public EducationMaterialRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        educationMaterialDao = database.educationMaterialDao();
        allEducationMaterials = educationMaterialDao.getAllEducationMaterials();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }

    // Операция вставки проекта
    public void insert(EducationMaterial educationMaterial) {
        executorService.execute(() -> educationMaterialDao.insert(educationMaterial));
    }

    // Операция обновления проекта
    public void update(EducationMaterial educationMaterial) {
        executorService.execute(() -> educationMaterialDao.update(educationMaterial));
    }

    // Операция удаления проекта
    public void delete(EducationMaterial educationMaterial) {
        executorService.execute(() -> educationMaterialDao.delete(educationMaterial));
    }

    // Получение всех проектов
    public LiveData<List<EducationMaterial>> getAllEducationMaterials() {
        return allEducationMaterials;
    }


    // Закрытие ExecutorService
    public void close() {
        executorService.shutdown();
    }
}