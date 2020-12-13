package com.vladkor.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vladkor.myapplication.MyClass.Question;

import java.util.Objects;

public class TestMainActivity extends AppCompatActivity {

//    registration fragment
    RegistrationForm rf;
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




}
