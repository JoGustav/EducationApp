package com.example.educationapp.data.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.educationapp.data.model.EducationPlatformDB;
import com.example.educationapp.data.model.dao.InterestTestAnswersDao;
import com.example.educationapp.data.model.dao.InterestTestQuestionsDao;
import com.example.educationapp.data.model.entities.InterestTestAnswers;
import com.example.educationapp.data.model.entities.InterestTestQuestions;

import java.util.List;

// Получение данных из базы данных с использованием асинхронного запроса
public class InterestTestRepository {
    private InterestTestQuestionsDao questionsDao;
    private InterestTestAnswersDao answersDao;

    // Инициализация DAO
    public InterestTestRepository(Application application) {
        EducationPlatformDB db = EducationPlatformDB.getDatabase(application);
        questionsDao = db.interestTestQuestionsDao();
        answersDao = db.interestTestAnswersDao();
    }

    // Получение списка вопросов
    public LiveData<List<InterestTestQuestions>> getAllQuestions() {
        return questionsDao.getAllQuestions();
    }

    public void insertAnswer(InterestTestAnswers answer) {
        answersDao.insert(answer);
    }

    // Получение списка ответов пользователя
    public LiveData<List<InterestTestAnswers>> getUserAnswers(int userId) {
        return answersDao.getAnswersByUserId(userId);
    }

    }
