package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "course_progress",
        foreignKeys = {
                @ForeignKey(entity = Course.class,
                        parentColumns = "courseID",
                        childColumns = "courseID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class,
                        parentColumns = "userID",
                        childColumns = "userID",
                        onDelete = ForeignKey.CASCADE),
        })
public class CourseProgress extends BaseObservable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int courseProgressID;
    @ColumnInfo(index = true)
    @NonNull
    private int courseID;
    @ColumnInfo(index = true)
    @NonNull
    private int userID;
    @ColumnInfo(name = "CompletedStagesCount")
    private int completedStagesCount;
    @ColumnInfo(name = "TotalStagesCount")
    private int totalStagesCount;
    @ColumnInfo(name = "ProgressPercentage")
    private int progressPercentage;

    public CourseProgress(int courseID, int userID, int completedStagesCount,
                          int totalStagesCount, int progressPercentage) {
        this.courseID = courseID;
        this.userID = userID;
        this.completedStagesCount = completedStagesCount;
        this.totalStagesCount = totalStagesCount;
        this.progressPercentage = progressPercentage;
    }

    @Bindable
    public int getCourseProgressID() { return courseProgressID; }
    public void setCourseProgressID(int courseProgressID) {
        this.courseProgressID = courseProgressID;
        notifyPropertyChanged(BR.courseProgressID);
    }
    @Bindable
    public int getCourseID() { return courseID; }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
        notifyPropertyChanged(BR.courseID);
    }
    @Bindable
    public int getUserID() { return userID; }
    public void setUserID(int userID) {
        this.userID = userID;
        notifyPropertyChanged(BR.userID);
    }
    @Bindable
    public int getCompletedStagesCount() { return completedStagesCount; }
    public void setCompletedStagesCount(int completedStagesCount) {
        this.completedStagesCount = completedStagesCount;
        notifyPropertyChanged(BR.completedStagesCount);
    }
    @Bindable
    public int getTotalStagesCount() { return totalStagesCount; }
    public void setTotalStagesCount(int totalStagesCount) {
        this.totalStagesCount = totalStagesCount;
        notifyPropertyChanged(BR.totalStagesCount);
    }
    @Bindable
    public int getProgressPercentage() { return progressPercentage; }
    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
        notifyPropertyChanged(BR.progressPercentage);
    }
}
