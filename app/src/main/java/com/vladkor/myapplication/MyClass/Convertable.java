package com.vladkor.myapplication.MyClass;

import com.vladkor.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

public interface Convertable {

    public Answer getAnswerData(int id);
    public Question getQuestionData(int id);
}
