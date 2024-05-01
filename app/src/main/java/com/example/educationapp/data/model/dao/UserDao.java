package com.example.educationapp.data.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.educationapp.data.model.entities.User;

import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user WHERE Name = :name AND Gender = :gender AND Age = :age")
    LiveData<User> getUserByNameGenderAndAge(String name, String gender, int age);
}