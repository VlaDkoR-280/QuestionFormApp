package com.vladkor.myapplication.MyClass;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Converter implements Convertable {


    private JSONObject object;
    private List<Answer> answerData;
    private List<Question> questionData;
    private JSONObject answerObj;
    private JSONObject questionObj;

    public Converter(String obj) throws JSONException {
        setObject(new JSONObject(obj));
        answerObj = object.getJSONObject("answers");
        questionObj = object.getJSONObject("questions");

        setAnswerData();
        setQuestionData();
    }
    @Override
    public Answer getAnswerData(int id) {
        return answerData.get(id);
    }

    private void setAnswerData() throws JSONException {
        answerData = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            String[] answers = new String[4];

            for(int j = 0; j < 4; j++){
                String name = "answers" + (i + 1);
                String name2 = "answer" + (j + 1);
                answers[j] = answerObj.getJSONObject(name).getString(name2).toString();
            }
            String name = "question" + (i + 1);
            String ci = questionObj.getJSONObject(name).getString("correctAnswerId");
            try{
                Answer a = new Answer(answers, Integer.parseInt(ci));
                answerData.add(a);
            }catch (Exception e){

            }


        }


    }
    @Override
    public Question getQuestionData(int id) {
        return questionData.get(id);
    }

    private void setQuestionData() throws JSONException {
        questionData = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            String name = "question" + (i + 1);
            questionData.add(new Question(questionObj.getJSONObject(name).getString("questionText")));
        }
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
