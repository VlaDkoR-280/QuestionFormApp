package com.vladkor.myapplication.MyClass;

import android.widget.RadioButton;

import org.json.JSONObject;

public class Question {
    private String questionText;

    public Question(String questionText){
        setQuestion(questionText);
    }

    private void setQuestion(String question) {
        this.questionText = question;
    }

    @Override
    public String toString(){
        return questionText;
    }
}
