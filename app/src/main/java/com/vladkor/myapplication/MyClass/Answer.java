package com.vladkor.myapplication.MyClass;

import android.widget.RadioButton;

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

    public void setViewAnswer(RadioButton[] buttons){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setText(ansewrs[idsAnswers[i]]);
        }
    }

}
