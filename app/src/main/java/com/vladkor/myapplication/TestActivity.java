package com.vladkor.myapplication;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    String str[] = new String[]{
            "a", "b", "c", "d"
    };

    private String getName(){
        return "Vlad";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        RadioGroup rg = findViewById(R.id.rad);
        RadioButton[] rbs = new RadioButton[str.length];
        ProgressBar pb = findViewById(R.id.progressBarTest);
        pb.setProgress(70);
        for(int i = 0; i < rbs.length; i++){
            rbs[i] = new RadioButton(this);
            rbs[i].setText(str[i]);
            rg.addView(rbs[i]);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                Toast.makeText(getApplicationContext(),Integer.toString(radioButton.getId()), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
