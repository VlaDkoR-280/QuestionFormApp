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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vladkor.myapplication.MyClass.Answer;
import com.vladkor.myapplication.MyClass.Converter;
import com.vladkor.myapplication.MyClass.GeneratorIds;
import com.vladkor.myapplication.MyClass.Person;
import com.vladkor.myapplication.MyClass.Question;

import org.json.JSONException;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionForm extends Fragment implements View.OnClickListener {

    private View v;

    private Person person;

    private int counter = 0;
    private Question myQuestion;
    private Answer myAnswer;

    private Button doneRegButton;
    private EditText plainText;

    private TextView textQuestion;
    private RadioButton[] buttons;
    private ImageButton nextBut;
    private int selected = 0;

    private Converter conv;

    private int[] idsQuestions;

    public QuestionForm(Person p) {
        person = p;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.question_form, null);
        StartQuestionForm();
        return v;
    }

    public void setData(){
        myAnswer = conv.getAnswerData(counter);
        myQuestion = conv.getQuestionData(counter);
        myAnswer.setViewAnswer(buttons);
        textQuestion.setText(myQuestion.toString());
    }

    public void StartQuestionForm(){
        textQuestion = v.findViewById(R.id.questionText);
        buttons = new RadioButton[]{
                v.findViewById(R.id.radioButton),
                v.findViewById(R.id.radioButton2),
                v.findViewById(R.id.radioButton3),
                v.findViewById(R.id.radioButton4)
        };
        nextBut = v.findViewById(R.id.nextButton);
        idsQuestions = GeneratorIds.GenerateRandomPosIds(buttons.length - 1);

        buttons[0].setChecked(true);


        try {
            conv = new Converter(Converter.readText(v.getContext(), R.raw.data));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(counter == 0){
            setData();
        }

        for(int i = 0; i < idsQuestions.length; i++){
            buttons[i].setOnClickListener(this);
        }

        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(myAnswer.cheackCorrectAnswers(selected)) {
                    person.score.addCorrect();
                }else{
                    person.score.addUncorrect();
                }
                if(counter < 4){

//                    String score = Float.toString(person.getScore());
//                    Toast.makeText(v.getContext(), score, Toast.LENGTH_SHORT).show();

                    if(counter < 4) setData();
                }else{
                    startResultForm();
//                    Toast.makeText(v.getContext(), "Вы закончили тест, поздравляем!", Toast.LENGTH_LONG).show();
                }
                buttons[0].setChecked(true);
                selected = 0;
            }
        });
    }

    protected void startResultForm(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentForm, new ResultForm(person));
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        RadioButton rb = (RadioButton) v;
        switch (rb.getId()){
            case R.id.radioButton: selected = 0;
                break;
            case R.id.radioButton2: selected = 1;
                break;
            case R.id.radioButton3: selected = 2;
                break;
            case R.id.radioButton4: selected = 3;
                break;
            default: return;
        }
    }
}
