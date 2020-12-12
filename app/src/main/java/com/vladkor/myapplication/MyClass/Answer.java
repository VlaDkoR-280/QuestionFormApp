package com.vladkor.myapplication.MyClass;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONObject;

public class Answer {
    private String[] ansewrs;
    private int correctAnswerID;

    public Answer(String[] answers, int correct_answer){
        setAnsewrs(answers);
        setCorrectAnswerID(correct_answer);
    }

    public String[] getAnsewrs() {
        return ansewrs;
    }

    private void setAnsewrs(String[] ansewrs) {
        this.ansewrs = ansewrs;
    }

    private int getCorrectAnswerID() {
        return correctAnswerID;
    }

    private void setCorrectAnswerID(int correctAnswerID) {
        this.correctAnswerID = correctAnswerID;
    }

    static void setViewAnswer(RadioButton[] buttons){

    }

}
