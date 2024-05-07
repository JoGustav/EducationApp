package com.example.educationapp.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.InterestTestAnswers;

import java.util.List;

@Dao
public interface InterestTestAnswersDao {

    @Insert
    void insert(InterestTestAnswers interestTestAnswers);

    @Update
    void update(InterestTestAnswers interestTestAnswers);

    @Delete
    void delete(InterestTestAnswers interestTestAnswers);

    @Query("SELECT * FROM interest_test_answers WHERE userID = :userID")
    LiveData<List<InterestTestAnswers>> getAnswersByUserId(int userID);
}