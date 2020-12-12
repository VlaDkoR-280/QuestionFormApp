package com.vladkor.myapplication.MyClass;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Converter {


    private JSONObject object;
    private Answer answerData;
    private Question questionData;

    public Answer getAnswerData() {
        return answerData;
    }

    private void setAnswerData(Answer answerData) {
        this.answerData = answerData;
    }

    public Question getQuestionData() {
        return questionData;
    }

    private void setQuestionData(Question questionData) {
        this.questionData = questionData;
    }

    public Converter(String obj) throws JSONException {
        setObject(new JSONObject(obj));
        Question quest = new Question(object.getString("Text"));
        setQuestionData(quest);
    }

    private void setObject(JSONObject obj){
        object = obj;
    }



    public static String readText(Context context, int resid) throws IOException {
        InputStream stream = context.getResources().openRawResource(resid);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while((s = br.readLine()) != null){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
