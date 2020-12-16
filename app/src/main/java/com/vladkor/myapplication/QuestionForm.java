package com.vladkor.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vladkor.myapplication.MyClass.Answer;
import com.vladkor.myapplication.MyClass.Converter;
import com.vladkor.myapplication.MyClass.Person;
import com.vladkor.myapplication.MyClass.Question;

import org.json.JSONException;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionForm extends Fragment{

    private View v;

    private Person person;

    private int counter = 0;
    private Question myQuestion;
    private Answer myAnswer;

    private Button doneRegButton;
    private EditText plainText;

    private TextView textQuestion;
    private RadioGroup rg;
    private RadioButton[] buttons;
    private ImageButton nextBut;
    private String selected = null;

    private Converter conv;

    private int[] idsQuestions;

    private int difference = 0;

    public QuestionForm(Person p) {
        person = p;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.question_form, null);
        rg = v.findViewById(R.id.radioGroup);
        nextBut = v.findViewById(R.id.nextButton);
        textQuestion = v.findViewById(R.id.questionText);

        try {
            conv = new Converter(Converter.readText(v.getContext(), R.raw.data_test));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setData();

        StartQuestionForm();
        return v;
    }

    public void setData(){
        myAnswer = conv.getAnswerData(counter);
        myQuestion = conv.getQuestionData(counter);

        rg.removeAllViews();
        myAnswer.setViewAnswer(rg, getContext());
        textQuestion.setText(myQuestion.toString());
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = v.findViewById(checkedId);
                if(rb == null){
                    return;
                }
                selected = rb.getText().toString();
            }
        });
    }

    public void StartQuestionForm(){





        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difference += conv.getLengthAnswers(counter);
                counter++;
                if(selected == null){
                    person.score.addMissed();
                }else if(myAnswer.cheackCorrectAnswers(selected)) {
                    person.score.addCorrect();
                }else{
                    person.score.addUncorrect();
                }
                if(counter < conv.getLengthQuestions()){
                    setData();
                }else{
                    rg.removeAllViews();
                    startResultForm();
                    return;
                }
                selected = null;
            }
        });
    }


    protected void startResultForm(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentForm, new ResultForm(person));
        ft.commit();
}



}
