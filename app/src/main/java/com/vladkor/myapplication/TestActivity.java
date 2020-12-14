package com.vladkor.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    String str[] = new String[]{
            "a", "b", "c", "d"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        RadioGroup rg = findViewById(R.id.rad);
        RadioButton[] rbs = new RadioButton[str.length];
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
