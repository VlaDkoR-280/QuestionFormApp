package com.vladkor.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.vladkor.myapplication.MyClass.Person;

public class TestMainActivity extends AppCompatActivity {

//    registration fragment
    RegistrationForm rf;
    ResultForm resf;
    QuestionForm qf;
    FragmentTransaction ft;
    Button doneRegButton;
    FragmentManager fm;
//    registration fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        onStartRegistration();
    }

    protected void onStartRegistration(){
        rf = new RegistrationForm();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.fragmentForm, rf);
        ft.commit();
    }

    public Person getPerson(){
        return rf.getPerson();
    }




}
