package com.vladkor.myapplication;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class Person {
    private String name;
    private float score;
    private int correct;
    private int uncorrect;

    public Person(String name){
        setName(name);
        correct = 0;
        uncorrect = 0;
        score = 0;

    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    private void setScore() {
        score = (float)correct / (uncorrect + correct);
    }

    public int getCorrect() {
        return correct;
    }

    public void addCorrect() {
        correct++;
        setScore();
    }

    public int getUncorrect() {
        return uncorrect;
    }

    public void addUncorrect() {
        uncorrect++;
        setScore();
    }
}
