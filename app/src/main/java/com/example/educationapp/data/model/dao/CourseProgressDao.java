package com.example.educationapp.data.model.dao;

import androidx.lifecycle.LiveData;
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
    @Query("SELECT * FROM course_progress WHERE courseID = :courseID AND userID = :userID")
    LiveData<List<CourseProgress>> getAllCourseProgressForUser(int courseID, int userID);
    @Query("SELECT CompletedStagesCount FROM COURSE_PROGRESS WHERE courseID = :courseID AND userID = :userID")
    LiveData<Integer> getCompletedStagesCountForCourse(int courseID, int userID);
    @Query("SELECT TotalStagesCount FROM COURSE_PROGRESS WHERE courseID = :courseID AND userID = :userID")
    LiveData<Integer> getTotalStagesCountForCourse(int courseID, int userID);
}

