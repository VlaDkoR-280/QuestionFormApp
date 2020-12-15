package com.vladkor.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vladkor.myapplication.MyClass.Converter;
import com.vladkor.myapplication.MyClass.Person;

import org.json.JSONException;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFormRecycler extends Fragment {

    private View v;
    private RecyclerView rv;
    private Button nextButton;

    private Person myPerson;
    private MyRecycler myRecycler;
    private Converter myConverter;
    public QuestionFormRecycler(Person person) {
        // Required empty public constructor
        myPerson = person;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_question_form_recycler, null);
        rv = v.findViewById(R.id.recyclerV);
        nextButton = v.findViewById(R.id.buttonEnd2);


        try {
            myConverter = new Converter(Converter.readText(v.getContext(), R.raw.data_test));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myRecycler = new MyRecycler(myConverter, myPerson);
        rv.hasFixedSize();
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(myRecycler);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartResultForm();
            }
        });

        return v;
    }

    private void StartResultForm(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Person p = myRecycler.getPerson();
        p.addAttemp();
        ft.replace(R.id.fragmentForm, new ResultForm(p));
        ft.commit();
    }
}
