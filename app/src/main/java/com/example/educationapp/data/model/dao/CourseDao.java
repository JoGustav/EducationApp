package com.example.educationapp.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM course WHERE directionID = :directionID")
    LiveData<List<Course>> getAllCoursesForDirection(int directionID);

    @Query("SELECT * FROM course WHERE courseID IN (:courseIDs)")
    LiveData<List<Course>> getCoursesByIds(List<Integer> courseIDs);

    @Insert
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);
}
