package com.example.educationapp.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import com.example.educationapp.data.model.entities.CourseProgress;
@Dao
public interface CourseProgressDao {
    @Insert
    void insert(CourseProgress courseProgress);

    @Update
    void update(CourseProgress courseProgress);

    @Delete
    void delete(CourseProgress courseProgress);
}

