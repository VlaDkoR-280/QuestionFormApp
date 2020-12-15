package com.vladkor.myapplication.MyClass;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Answer {
    private int[] idsAnswers;
    private String[] ansewrs;
    private int correctAnswerID;

    public Answer(String[] answers, int correct_answer){
        setAnsewrs(answers);
        setCorrectAnswerID(correct_answer);
        idsAnswers = GeneratorIds.GenerateRandomPosIds(answers.length - 2);
    }

    public int[] getIdsAnswers(){
        return idsAnswers;
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

    public boolean cheackCorrectAnswers(int id){
        return correctAnswerID == idsAnswers[id];
    }
    public boolean cheackCorrectAnswers(String str){
        return ansewrs[correctAnswerID] == str;
    }

    public void setViewAnswer(RadioButton[] buttons){
        for(int i = 0; i < getAnsewrs().length - 1; i++){
            buttons[i].setText(ansewrs[idsAnswers[i]]);
        }
    }

    public void setViewAnswer(RadioGroup rg, Context con){
        rg.clearCheck();
        rg.removeAllViews();

        for(int i = 0; i < getAnsewrs().length - 1; i++){
            RadioButton rb = new RadioButton(con);
            rb.setText(ansewrs[idsAnswers[i]]);
            rg.addView(rb);
        }
    }

}
