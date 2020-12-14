package com.vladkor.myapplication.MyClass;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Converter implements Convertable {

    private ArrayList<Form> formsData;
    private JSONObject object;
    private int[] idsQuestions;



    public Converter(String obj) throws JSONException {
        setObject(new JSONObject(obj));
        formsData = new ArrayList<>();

        setFormData(object);
        idsQuestions = GeneratorIds.GenerateRandomPosIds(getLengthQuestions() - 1);
    }

    public int getLengthQuestions(){
        return formsData.size();
    }
    public int getLengthAnswers(int id){
        return formsData.get(idsQuestions[id]).answer.getAnsewrs().length - 1;
    }


    private void setFormData(JSONObject obj) throws JSONException {
        int length = obj.getJSONObject("forms").length();
        for(int i = 0; i < length; i++){
            String name = "form" + (i + 1);
            JSONObject form = obj.getJSONObject("forms").getJSONObject(name);
            String questionText = form.getString("questionText");
            Question myQuestion = new Question(questionText);
            JSONObject answers = form.getJSONObject("answers");
            String[] answerArray = new String[answers.length()];
            for(int j = 0; j < answers.length() - 1; j++){
                String answerName = "answer" + (j + 1);
                answerArray[j] = answers.getString(answerName);
            }
            int correctAnswer = Integer.parseInt(answers.getString("correctAnswerId"));
            Answer myAnswer = new Answer(answerArray, correctAnswer);
            formsData.add(new Form(myQuestion, myAnswer));
        }
    }

    @Override
    public Answer getAnswerData(int id) {
        return formsData.get(idsQuestions[id]).answer;
    }


    @Override
    public Question getQuestionData(int id) {
        return formsData.get(idsQuestions[id]).question;
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