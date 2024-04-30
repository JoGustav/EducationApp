package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "user_task_result",
        foreignKeys = {
            @ForeignKey(entity = User.class,
                            parentColumns = "userID",
                            childColumns = "userID",
                            onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = Task.class,
                    parentColumns = "taskID",
                    childColumns = "taskID",
                    onDelete = ForeignKey.CASCADE)})
public class UserTaskResult extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int userTaskResultID;
    @ColumnInfo(index = true)
    @NonNull
    private int userID;
    @ColumnInfo(index = true)
    @NonNull
    private int taskID;
    @ColumnInfo(name = "Result")
    private boolean result;

    public UserTaskResult(int userID, int taskID, boolean result) {
        this.userID = userID;
        this.taskID = taskID;
        this.result = result;
    }

    @Bindable
    public int getUserTaskResultID() { return userTaskResultID; }
    public void setUserTaskResultID(int userTaskResultID) {
        this.userTaskResultID = userTaskResultID;
        notifyPropertyChanged(BR.userTaskResultID);
    }

    @Bindable
    public int getUserID() { return userID; }
    public void setUserID(int userID) {
        this.userID = userID;
        notifyPropertyChanged(BR.userID);
    }

    @Bindable
    public int getTaskID() { return taskID; }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
        notifyPropertyChanged(BR.taskID);
    }

    @Bindable
    public boolean getResult() { return result; }
    public void setResult(boolean result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }
}