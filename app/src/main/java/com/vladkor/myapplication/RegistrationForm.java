package com.vladkor.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vladkor.myapplication.MyClass.Person;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationForm extends Fragment implements View.OnClickListener {

    private Person person;

    private Button btn;
    private EditText plain;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private RadioButton[] rButtons;

    private int selected = 0;

    private QuestionFormRecycler qfr;
    private QuestionForm qf;

    public RegistrationForm(Person person) {
        person.score.clearScore();
        this.person = person;

    }
    public RegistrationForm(){
        person = null;
    }

    public Person getPerson(){
        return person;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.registration_form, null);
        rButtons = new RadioButton[2];
        rButtons[0] = v.findViewById(R.id.firstRadButton);
        rButtons[1] = v.findViewById(R.id.secondRadButon);
        rButtons[0].setChecked(true);
        rButtons[0].setOnClickListener(this);
        rButtons[1].setOnClickListener(this);

        btn = (Button) v.findViewById(R.id.doneBut);
        plain = (EditText) v.findViewById(R.id.nameText);
        if(person != null){
            plain.setHint(person.getName());
            plain.setEnabled(false);

        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!plain.getText().toString().equals("") || person != null){
                    StartQuestionForm();
                }
            }
        });


        // Inflate the layout for this fragment
        return v;

    }

    private void StartQuestionForm(){

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        if(person == null){
            person = new Person(plain.getText().toString());
        }

        if(selected == 0){
            qf = new QuestionForm(person);
            ft.replace(R.id.fragmentForm, qf);
        }else if(selected == 1){
            qfr = new QuestionFormRecycler(person);
            ft.replace(R.id.fragmentForm, qfr);
        }
        ft.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.firstRadButton: selected = 0;
            break;
            case R.id.secondRadButon: selected = 1;
            break;
        }
    }
}
