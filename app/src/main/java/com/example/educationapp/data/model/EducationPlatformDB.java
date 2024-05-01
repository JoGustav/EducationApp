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
                    version = 2)
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


        directionDao.insert(new Direction("Интеллектуальная","Ваш ребенок обладает ярко выраженной интеллектуальной одаренностью. Он способен к глубокому анализу, логическому мышлению и быстрому усвоению новой информации. Это дар, который открывает перед ним мир научных открытий и интеллектуальных достижений"));
        directionDao.insert(new Direction("Творческая","Творческий потенциал вашего ребенка не знает границ. Его воображение и способность к нестандартному мышлению позволяют создавать уникальные идеи и проекты. Поддержка его творчества может привести к великим изобретениям и произведениям искусства."));
        directionDao.insert(new Direction("Академическая","Ваш ребенок демонстрирует выдающиеся академические способности. Его интерес к учебе и способность погружаться в сложные темы обещают большие успехи в школьной программе и за ее пределами"));
        directionDao.insert(new Direction("Художественно-изобразительная","Художественная одаренность вашего ребенка позволяет ему видеть мир в ярких красках и передавать свои впечатления через рисунок и живопись. Его работы могут рассказать о многом без слов"));
        directionDao.insert(new Direction("Музыкальная","Музыкальный талант вашего ребенка открывает ему двери в мир гармонии и ритма. Его способность воспринимать и создавать музыку может стать источником большой радости и вдохновения."));
        directionDao.insert(new Direction("Литературная","Литературная одаренность вашего ребенка проявляется в его любви к чтению и письму. Он способен создавать захватывающие истории и выразительные стихи, которые могут тронуть сердца."));
        directionDao.insert(new Direction("Артистическая","Ваш ребенок обладает артистическим даром, который позволяет ему выражать себя через танец, театр и выступления. Его способность передавать эмоции и истории через движение и игру заслуживает восхищения"));
        directionDao.insert(new Direction("Техническая","Техническая одаренность вашего ребенка проявляется в его интересе к устройствам и механизмам. Он способен понимать и создавать сложные конструкции, что может привести его к будущим изобретениям."));
        directionDao.insert(new Direction("Информационные технологии","Ваш ребенок показывает большой интерес к информационным технологиям. Его способность быстро осваивать новые программы и устройства говорит о большом потенциале в этой быстро развивающейся области."));
        directionDao.insert(new Direction("Cоциальный интеллект","Социальная одаренность вашего ребенка помогает ему легко находить общий язык с окружающими и быть лидером. Его умение понимать и управлять социальными взаимодействиями является ценным качеством."));
        directionDao.insert(new Direction("Психомоторная","Спортивные достижения вашего ребенка впечатляют. Его физическая сила, ловкость и выносливость позволяют ему преуспевать в различных видах спорта и добиваться высоких результатов."));

    }
}