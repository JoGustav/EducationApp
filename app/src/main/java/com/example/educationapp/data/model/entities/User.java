package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "user")
public class User extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int userID;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Age")
    private int age;
    @ColumnInfo(name = "Gender")
    private String gender;

    public User(int userID, String name, int age, String gender) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Bindable
    public int getUserID() { return userID; }
    public void setUserID(int userID) {
        this.userID = userID;
        notifyPropertyChanged(BR.userID);
    }

    @Bindable
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() { return age; }
    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getGender() { return gender; }
    public void setGender(String gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }
}
