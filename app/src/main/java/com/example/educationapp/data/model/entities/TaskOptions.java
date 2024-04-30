package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "task_options",
        foreignKeys = {
                @ForeignKey(entity = Task.class,
                        parentColumns = "taskID",
                        childColumns = "taskID",
                        onDelete = ForeignKey.CASCADE),
        })
public class TaskOptions extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int optionID;
    @ColumnInfo(index = true)
    @NonNull
    private int taskID;
    @ColumnInfo(name = "OptionText")
    private String optionText;

    public TaskOptions(int taskID, String optionText) {
        this.taskID = taskID;
        this.optionText = optionText;
    }

    @Bindable
    public int getOptionID() { return optionID; }
    public void setOptionID(int optionID) {
        this.optionID = optionID;
        notifyPropertyChanged(BR.optionID);
    }

    @Bindable
    public int getTaskID() { return taskID; }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
        notifyPropertyChanged(BR.taskID);
    }

    @Bindable
    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) {
        this.optionText = optionText;
        notifyPropertyChanged(BR.optionText);
    }
}

