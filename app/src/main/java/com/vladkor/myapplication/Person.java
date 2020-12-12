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
        score = uncorrect == 0 ? 0f : (float)(correct / uncorrect);
    }

    public int getCorrect() {
        return correct;
    }

    private void addCorrect() {
        correct++;
        setScore();
    }

    public int getUncorrect() {
        return uncorrect;
    }

    private void addUncorrect() {
        uncorrect++;
        setScore();
    }
}
