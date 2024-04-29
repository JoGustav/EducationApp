package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "users_courses",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "userID",
                        childColumns = "userID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Course.class,
                        parentColumns = "courseID",
                        childColumns = "courseID",
                        onDelete = ForeignKey.CASCADE)
        })
public class UsersCourses extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int usersCoursesID;
    @ColumnInfo(name = "UserID", index = true)
    private int userID;
    @ColumnInfo(name = "CourseID", index = true)
    private int courseID;

    public UsersCourses(int usersCoursesID, int userID, int courseID, float progress) {
        this.usersCoursesID = usersCoursesID;
        this.userID = userID;
        this.courseID = courseID;
    }

    @Bindable
    public int getUsersCoursesID() { return usersCoursesID; }
    public void setUsersCoursesID(int usersCoursesID) {
        this.usersCoursesID = usersCoursesID;
        notifyPropertyChanged(BR.usersCoursesID);
    }

    @Bindable
    public int getUserID() { return userID; }
    public void setUserID(int userID) {
        this.userID = userID;
        notifyPropertyChanged(BR.userID);
    }

    @Bindable
    public int getCourseID() { return courseID; }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
        notifyPropertyChanged(BR.courseID);
    }
}