package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.AchievementsDao;
import com.example.educationapp.data.model.dao.CourseProgressDao;
import com.example.educationapp.data.model.dao.UserAchievementsDao;
import com.example.educationapp.data.model.entities.Achievements;
import com.example.educationapp.data.model.entities.CourseProgress;
import com.example.educationapp.data.model.entities.UserAchievements;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserAchievementsRepository {
    private UserAchievementsDao userAchievementsDao;
    private AchievementsDao achievementsDao;
    private ExecutorService executorService;

    public UserAchievementsRepository(Application application) {
        EducationPlatformDB database = EducationPlatformDB.getDatabase(application);
        userAchievementsDao = database.userAchievementsDao();
        achievementsDao = database.achievementsDao();
        executorService = Executors.newFixedThreadPool(3); // Создаем пул из 3 потоков
    }
    public LiveData<List<Achievements>> getAchievementsForUser(int userID) {
        return achievementsDao.getAchievementsForUser(userID);
    }
    public void insert(UserAchievements userAchievements) {
        executorService.execute(() -> userAchievementsDao.insert(userAchievements));
    }
    public void update(UserAchievements userAchievements) {
        executorService.execute(() -> userAchievementsDao.update(userAchievements));
    }
    public void delete(UserAchievements userAchievements) {
        executorService.execute(() -> userAchievementsDao.delete(userAchievements));
    }
}
