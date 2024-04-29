package com.example.educationapp.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.InterestTestResult;

import java.util.List;
@Dao
public interface InterestTestResultDao {
    @Insert
    void insert(InterestTestResult interestTestResult);

    @Update
    void update(InterestTestResult interestTestResult);

    @Delete
    void delete(InterestTestResult interestTestResult);
}
