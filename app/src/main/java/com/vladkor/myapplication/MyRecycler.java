package com.vladkor.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vladkor.myapplication.MyClass.Answer;
import com.vladkor.myapplication.MyClass.Converter;
import com.vladkor.myapplication.MyClass.Person;
import com.vladkor.myapplication.MyClass.Question;

public class MyRecycler extends RecyclerView.Adapter<MyRecycler.ViewHolder> {

    private Converter myConverter;
    private Person myPerson;
    private int[] myAnswersId;
    private String[] myAnswersStr;
//    private int myDifference = 0;

    public MyRecycler(Converter converter, Person person){

        myConverter = converter;
        myPerson = person;
        myAnswersId = new int[myConverter.getLengthQuestions()];
        myAnswersStr = new String[myConverter.getLengthQuestions()];
//        int numAnswers = 0;
//        for(int i = 0; i < myConverter.getLengthQuestions(); i++){
//            numAnswers += myConverter.getLengthAnswers(i);
//        }
//        myDifference = myPerson.getAttemp() * numAnswers;
        for(int i = 0; i < myAnswersId.length; i++){
            myAnswersId[i] = -1;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        int myDifferenceHolder = myDifference;
//        for(int i = 0; i < position; i++){
//            myDifferenceHolder += myConverter.getLengthAnswers(i);
//        }
//        holder.difference = myDifferenceHolder;
        Answer myAnswer = myConverter.getAnswerData(position);
        Question myQuestion = myConverter.getQuestionData(position);
        RadioGroup rg = holder.radioGroup;
        rg.invalidate();
        myAnswer.setViewAnswer(rg, holder.myView.getContext());
        holder.questionText.setText(myQuestion.toString());

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = holder.myView.findViewById(checkedId);
                myAnswersStr[position] = rb.getText().toString();
//                myAnswersId[position] = checkedId - (1 + holder.difference);
//                Log.d("CheakID", "onCheckedChanged: " + checkedId + " | " + (checkedId - (1 + holder.difference)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return myConverter.getLengthQuestions();
    }

    public Person getPerson(){
        for(int i = 0; i < myAnswersStr.length; i++){
            if(myAnswersStr[i] == null){
                myPerson.score.addMissed();
            }else if(myConverter.getAnswerData(i).cheackCorrectAnswers(myAnswersStr[i])){
                myPerson.score.addCorrect();
            }else{
                myPerson.score.addUncorrect();
            }
        }
//        for(int i = 0; i < myAnswersId.length; i++){
//            if(myAnswersId[i] < 0){
//                myPerson.score.addMissed();
//            }else if(myConverter.getAnswerData(i).cheackCorrectAnswers(myAnswersId[i])){
//                myPerson.score.addCorrect();
//            }else{
//                myPerson.score.addUncorrect();
//            }
//        }
        return myPerson;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View myView;
        public TextView questionText;
        public RadioGroup radioGroup;
        public int difference = 0;

        public ViewHolder(View v){
            super(v);
            myView = v;
            questionText = v.findViewById(R.id.questionTextR);
            radioGroup = v.findViewById(R.id.radioGroupR);
        }
    }
}
