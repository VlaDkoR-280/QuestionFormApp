package com.vladkor.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vladkor.myapplication.MyClass.Person;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonLayout extends Fragment {

    View v;
    Person myPerson;

    TextView personName;

    public PersonLayout(Person person) {
        myPerson = person;
    }

    public PersonLayout() {
        myPerson = new Person("Test");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_person_layout, null);
        personName = v.findViewById(R.id.personName);
        int i = 0;
        personName.setText(myPerson.getName());
        // Inflate the layout for this fragment

        return v;
    }
}
