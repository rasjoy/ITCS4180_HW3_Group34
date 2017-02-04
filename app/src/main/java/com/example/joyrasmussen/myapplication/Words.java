package com.example.joyrasmussen.myapplication;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Words extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        //getting screen size so that we can dynamically set the size of the scroll view.


        ScrollView scrollView = (ScrollView) findViewById(R.id.wordScroll);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutWord);

       /* for(int i = 0; i < 30; i++){
            TextView text = new TextView(this);
            text.setTextSize(16);
            text.setText(i +"");
           layout.addView(text);
        }*/

        

    }
}
