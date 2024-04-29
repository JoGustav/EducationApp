package com.example.educationapp.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.UsersCourses;

import java.util.List;
@Dao
public interface UsersCoursesDao {
    @Query("SELECT * FROM users_courses")
    List<UsersCourses> getAllUsersCourses();

    @Insert
    void insertUsersCourse(UsersCourses usersCourses);

    @Update
    void updateUsersCourse(UsersCourses usersCourses);

    @Delete
    void deleteUsersCourse(UsersCourses usersCourses);
}