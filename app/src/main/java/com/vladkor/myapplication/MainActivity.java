package com.vladkor.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.vladkor.myapplication.MyClass.Converter;
import com.vladkor.myapplication.MyClass.Question;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.questionText);

        Converter conv = null;
        Question q = null;
        try {
            conv = new Converter(Converter.readText(getApplicationContext(), R.raw.data));
            q = conv.getQuestionData();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }






        tv.setText(q.toString());

    }

    private String readText(Context context, int resid) throws IOException {
        InputStream stream = context.getResources().openRawResource(resid);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while((s = br.readLine()) != null){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
