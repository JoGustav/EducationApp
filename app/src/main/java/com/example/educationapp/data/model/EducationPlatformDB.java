package com.example.educationapp.data.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.educationapp.data.model.dao.*;
import com.example.educationapp.data.model.entities.*;

@Database(entities = {Course.class, CourseProgress.class, Direction.class, EducationMaterial.class,
                    InterestTestAnswers.class, InterestTestQuestions.class, InterestTestResult.class,
                    Stage.class, Task.class, TaskOptions.class, User.class, UsersCourses.class, UserTaskResult.class},
                    version = 1)
public abstract class EducationPlatformDB extends RoomDatabase {
    // Объявление DAO для каждой сущности.
    public abstract DirectionDao directionDao();

    public abstract CourseDao courseDao();
    public abstract CourseProgressDao courseProgressDao();

    public abstract UserDao userDao();

    public abstract StageDao stageDao();

    public abstract EducationMaterialDao educationMaterialDao();

    public abstract TaskDao taskDao();

    public abstract TaskOptionsDao taskOptionsDao();

    public abstract UserTaskResultDao userTaskResultDao();

    public abstract InterestTestQuestionsDao interestTestQuestionsDao();

    public abstract InterestTestAnswersDao interestTestAnswersDao();

    public abstract InterestTestResultDao interestTestResultDao();

    public abstract UsersCoursesDao usersCoursesDao();

    // Создание экземпляра базы данных.
    private static volatile EducationPlatformDB INSTANCE;
    public static EducationPlatformDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EducationPlatformDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EducationPlatformDB.class, "education_platform_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Thread(() -> InitializeData(INSTANCE)).start();
        }
    };

    private static void InitializeData(EducationPlatformDB db) {
        Log.d("init data", "иницировано");
        CourseDao courseDao = db.courseDao();
        CourseProgressDao courseProgressDao = db.courseProgressDao();
        DirectionDao directionDao = db.directionDao();
        EducationMaterialDao educationMaterialDao = db.educationMaterialDao();
        InterestTestAnswersDao interestTestAnswersDao = db.interestTestAnswersDao();
        InterestTestQuestionsDao interestTestQuestionsDao = db.interestTestQuestionsDao();
        InterestTestResultDao interestTestResultDao = db.interestTestResultDao();
        StageDao stageDao = db.stageDao();
        TaskDao taskDao = db.taskDao();
        TaskOptionsDao taskOptionsDao = db.taskOptionsDao();
        UserDao userDao = db.userDao();
        UsersCoursesDao usersCoursesDao = db.usersCoursesDao();
        UserTaskResultDao userTaskResultDao = db.userTaskResultDao();
    }
}