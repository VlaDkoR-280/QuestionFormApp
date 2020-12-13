package com.vladkor.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vladkor.myapplication.MyClass.Person;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultForm extends Fragment {

    View v;
    ProgressBar scoreBar;
    TextView scoreText;

    PersonLayout pl;
    Person myPerson;

    public ResultForm(Person person) {
        myPerson = person;
    }

    public ResultForm() {
        myPerson = new Person("Test");
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.result_form, null);
        scoreBar = (ProgressBar) v.findViewById(R.id.scoreBar);
        scoreText = (TextView) v.findViewById(R.id.scoreText);


        scoreText.setText(Float.toString(myPerson.score.getScore()));
        scoreBar.setMax(myPerson.score.count());
        scoreBar.setProgress(myPerson.score.getCorrect());


        pl = new PersonLayout(myPerson);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fragmentPerson, pl);
        ft.commit();
        return v;
    }
}
