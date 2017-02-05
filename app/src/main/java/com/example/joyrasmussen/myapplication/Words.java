package com.example.joyrasmussen.myapplication;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class Words extends AppCompatActivity {
    ArrayList<String> wordLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        //getting screen size so that we can dynamically set the size of the scroll view.


        ScrollView scrollView = (ScrollView) findViewById(R.id.wordScroll);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutWord);

        wordLists = (ArrayList<String>)getIntent().getSerializableExtra(MainActivity.COUNTER);
        /*wordLists = new ArrayList<String>();
        for(int i = 0; i < 10; i++){
            wordLists.add("Words: " + i);

        }*/
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);


        for(String words: wordLists){
            TextView text = new TextView(this);
            text.setLayoutParams(layoutParams);
            text.setTextSize(16);
            text.setTextColor(Color.BLACK);
            text.setText(words);
            layout.addView(text);

        }


       /* for(int i = 0; i < 30; i++){
            TextView text = new TextView(this);
            text.setTextSize(16);
            text.setText(i +"");

        }*/



    }
    public void finishListener(View v){
        finish();

    }
}
