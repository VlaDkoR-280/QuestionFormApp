package com.vladkor.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.vladkor.myapplication.MyClass.Answer;
import com.vladkor.myapplication.MyClass.Converter;
import com.vladkor.myapplication.MyClass.GenerateIds;
import com.vladkor.myapplication.MyClass.Question;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int counter = 0;



    private Question myQuestion;
    private Answer myAnswersTest = new Answer(new String[]{"dedefe", "fefefef", "mefemef", "vlad"}, 3);
    private Answer myAnswer;


    private TextView textQuestion;
    private RadioButton[] buttons;
    private ImageButton nextBut;
    private int selected = 0;

    private Converter conv;

    private int[] idsQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textQuestion = findViewById(R.id.questionText);
        buttons = new RadioButton[]{
                findViewById(R.id.radioButton),
                findViewById(R.id.radioButton2),
                findViewById(R.id.radioButton3),
                findViewById(R.id.radioButton4)
        };
        nextBut = findViewById(R.id.nextButton);
        idsQuestions = GenerateIds.GenerateRandomPosIds(buttons.length - 1);


        buttons[0].setChecked(true);


        try {
            conv = new Converter(Converter.readText(getApplicationContext(), R.raw.data));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(counter == 0){
            setData();
        }

        textQuestion.setText(myQuestion.toString());

        myAnswer.setViewAnswer(buttons);

        for(int i = 0; i < idsQuestions.length; i++){
            buttons[i].setOnClickListener(this);
        }

        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), myAnswer.cheackCorrectAnswers(selected) ? "Верно" : "Не верно", Toast.LENGTH_SHORT).show();
                if(counter < 3){
                    counter++;
                    setData();
                }else{
                    Toast.makeText(getApplicationContext(), "Вы закончили тест, поздравляем!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void setData(){
        myAnswer = conv.getAnswerData(counter);
        myQuestion = conv.getQuestionData(counter);
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
        int i = 1;
    }

    public void StartQuestion(){

    }
}
