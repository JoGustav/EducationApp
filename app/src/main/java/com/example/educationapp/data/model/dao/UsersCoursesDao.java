package com.example.educationapp.data.model.dao;

import androidx.lifecycle.LiveData;
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
    LiveData<List<UsersCourses>> getAllUsersCourses();

    @Query("SELECT * FROM users_courses WHERE userID = :userID")
    LiveData<List<UsersCourses>> getAllCoursesForUser(int userID);

    @Insert
    void insert(UsersCourses usersCourses);

    @Update
    void update(UsersCourses usersCourses);

    @Delete
    void delete(UsersCourses usersCourses);
}