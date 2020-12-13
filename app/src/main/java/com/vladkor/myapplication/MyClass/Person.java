package com.vladkor.myapplication.MyClass;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class Person {
    private String name;
    public Score score;

    public Person(String name){
        setName(name);
        score = new Score();

    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

}
