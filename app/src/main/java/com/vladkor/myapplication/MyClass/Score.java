package com.vladkor.myapplication.MyClass;

public class Score{
    private int correct = 0;
    private int uncorrect = 0;
    private int missed = 0;
    public int count(){
        return correct + uncorrect + missed;
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
    public void addMissed(){
        missed++;
    }
    public int getCorrect(){
        return correct;
    }
    public int getUncorrect(){
        return uncorrect;
    }
    public int getMissed(){
        return missed;
    }
    public void clearScore(){
        correct = 0;
        uncorrect = 0;
        missed = 0;
    }
}
