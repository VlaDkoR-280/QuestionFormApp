package com.vladkor.myapplication.MyClass;

public class Person {
    private String name;
    public Score score;
    private int attemps;

    public Person(String name){
        setName(name);
        score = new Score();
        attemps = 0;
    }

    public void addAttemp(){
        attemps++;
    }
    public int getAttemp(){
        return attemps;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

}
