package com.example.educationapp.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.InterestTestQuestions;

import java.util.List;

@Dao
public interface InterestTestQuestionsDao {
    @Insert
    void insert(InterestTestQuestions interestTestQuestions);

    @Update
    void update(InterestTestQuestions interestTestQuestions);

    @Delete
    void delete(InterestTestQuestions interestTestQuestions);
}