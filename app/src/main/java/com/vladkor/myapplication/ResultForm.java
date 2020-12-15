package com.vladkor.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vladkor.myapplication.MyClass.Person;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultForm extends Fragment {

    private View v;
    private ProgressBar correctScoreBar;
    private ProgressBar uncorrectScoreBar;
    private ProgressBar missedScoreBar;
    private TextView scoreText;
    private ImageButton btnRestart;

    private PersonLayout pl;
    private Person myPerson;

    private FragmentManager fm;


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
        correctScoreBar = (ProgressBar) v.findViewById(R.id.CorrectProgressBar);
        uncorrectScoreBar = (ProgressBar) v.findViewById(R.id.UncorrectProgressBar);
        missedScoreBar = (ProgressBar) v.findViewById(R.id.MissedProgressBar);
        scoreText = (TextView) v.findViewById(R.id.scoreText);
        btnRestart =  v.findViewById(R.id.buttonRestart);

        int score =(int) (myPerson.score.getScore() * (float) 100);
        scoreText.setText(Integer.toString(score) + "%");
        correctScoreBar.setMax(myPerson.score.count());
        correctScoreBar.setProgress(myPerson.score.getCorrect());


        missedScoreBar.setMax(myPerson.score.count());
        missedScoreBar.setProgress(myPerson.score.getCorrect() + myPerson.score.getMissed());

        uncorrectScoreBar.setMax(myPerson.score.count());
        uncorrectScoreBar.setProgress(myPerson.score.count());



        pl = new PersonLayout(myPerson);
        fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fragmentPerson, pl);
        ft.commit();

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fm.beginTransaction();
                RegistrationForm rf = new RegistrationForm(myPerson);
                ft.replace(R.id.fragmentForm, rf);
                ft.commit();

            }
        });
        return v;
    }


}
