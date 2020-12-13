package com.vladkor.myapplication.MyClass;

public class Score{
    private int correct = 0;
    private int uncorrect = 0;
    public int count(){
        return correct + uncorrect;
    }
    public float getScore(){
        return (float)correct / count();
    }
    public void addCorrect(){
        correct++;
    }
    public void addUncorrect(){
        uncorrect++;
    }
    public int getCorrect(){
        return correct;
    }
}
