package com.vladkor.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vladkor.myapplication.MyClass.Person;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationForm extends Fragment {

    private Person person;

    private Button btn;
    private EditText plain;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private QuestionForm qf;



    public RegistrationForm() {

    }

    public Person getPerson(){
        return person;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.registration_form, null);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        btn = (Button) v.findViewById(R.id.doneBut);
        plain = (EditText) v.findViewById(R.id.nameText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = plain.getText().toString();
                if(!name.equals("")){
                    person = new Person(name);
                    qf = new QuestionForm(person);
                    ft.replace(R.id.fragmentForm, qf);
                    ft.commit();
                }
            }
        });
        // Inflate the layout for this fragment
        return v;

    }


}
