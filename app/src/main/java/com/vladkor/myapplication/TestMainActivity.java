package com.vladkor.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class TestMainActivity extends AppCompatActivity {

    RegistrationForm rf;
    FragmentTransaction ft;
    FragmentManager fm;


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
