package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "interest_test_answers")
public class InterestTestAnswers extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int answerID;
    @ColumnInfo(name = "UserID")
    private int userID;
    @ColumnInfo(name = "QuestionID")
    private int questionID;
    @ColumnInfo(name = "Answer")
    private String answer;

    public InterestTestAnswers(int answerID, int userID, int questionID, String answer) {
        this.answerID = answerID;
        this.userID = userID;
        this.questionID = questionID;
        this.answer = answer;
    }

    @Bindable
    public int getAnswerID() { return answerID; }
    public void setAnswerID(int answerID) {
        this.answerID = answerID;
        notifyPropertyChanged(BR.answerID);
    }

    @Bindable
    public int getUserID() { return userID; }
    public void setUserID(int userID) {
        this.userID = userID;
        notifyPropertyChanged(BR.userID);
    }

    @Bindable
    public int getQuestionID() { return questionID; }
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
        notifyPropertyChanged(BR.questionID);
    }

    @Bindable
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) {
        this.answer = answer;
        notifyPropertyChanged(BR.answer);
    }
}