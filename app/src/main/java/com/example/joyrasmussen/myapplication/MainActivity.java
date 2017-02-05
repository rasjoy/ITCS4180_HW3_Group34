package com.example.joyrasmussen.myapplication;
/**
 *Homework 3
 * MainActivity.java
 *Group 34
 * Joy Rasmussen and Robert Holt
 *
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab1, fab2, fab3, fab4, fab5, fab6, fab7, fab8, fab9, fab10,
            fab11, fab12, fab13, fab14, fab15, fab16, fab17, fab18, fab19, fab20;
    ArrayList<FloatingActionButton> fabs;

    EditText word1, word2, word3, word4, word5, word6, word7, word8, word9, word10,
            word11, word12, word13, word14, word15, word16, word17, word18, word19, word20;
    ArrayList<EditText> editTexts;

    LinearLayout row1, row2, row3, row4, row5, row6, row7, row8, row9, row10,
            row11, row12, row13, row14, row15, row16, row17, row18, row19, row20;
    ArrayList<LinearLayout> rows;

    ArrayList<String> words; //Search terms
    ArrayList<String> textsForViews;

    ProgressDialog progressDialog;

    boolean matchCase;
    final static String COUNTER = "COUNTER";

    final static String INPUT_FILE = "textfile.txt";
    ArrayList<String> allwords;

    public void fabClick(View view) {

        if ((String) view.getTag(R.id.status) == "plus") {
            int tag = (int) view.getTag(R.id.number);


            if (tag < 20) {
                rows.get(tag).setVisibility(View.VISIBLE);
            }

            view.setTag(R.id.status, "minus");

            FloatingActionButton fab = (FloatingActionButton) view;
            fab.setImageResource(R.drawable.bottom_bar);

        } else if ((String) view.getTag(R.id.status) == "minus") {

            int tag = (int) view.getTag(R.id.number);

            if (tag - 1 > 0) {
                shiftText(tag - 1);
            }
        }

    }

    public void shiftText(int index) {

        boolean lastHidden = false;

        for (int i = index; i < 19; i++) {
            editTexts.get(i).setText(editTexts.get(i + 1).getText());

        }

        for (int j = 19; j >= 0; j--) {
            if (!lastHidden) {
                if (rows.get(j).getVisibility() == View.VISIBLE) {
                    rows.get(j).setVisibility(View.GONE);
                    lastHidden = true;
                    fabs.get(j - 1).setImageResource(R.drawable.ic_menu_add);
                    fabs.get(j - 1).setTag(R.id.status, "plus");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAndHideRows();
        words = new ArrayList<String>();

        textsForViews = new ArrayList<String>();
        readInFile();

    }


    public void buttonListener(View v) {

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        matchCase = checkBox.isChecked(); //match case or not? True or false.

        words.clear(); //In case the user returns after finishing the 2nd activity

        textsForViews.clear();

        for(EditText e : editTexts){
            String word = e.getText().toString();

            if(!word.isEmpty()){
                words.add(word);
            }
        }
        if(words.size() == 0) {
            Toast.makeText(this, "Please provide at least one word to search", Toast.LENGTH_LONG).show();
            return;
        }
            //words = the ArrayList of search terms
            //matchCase = boolean for matching case
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Calculating Frequency!");
            progressDialog.setCancelable(false);
            progressDialog.setMax(words.size());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();

            for (String word : words) {
                new WordCounter().execute(word);
            }
    }

    public void createAndHideRows() {

        row1 = (LinearLayout) findViewById(R.id.row1);
        row2 = (LinearLayout) findViewById(R.id.row2);
        row3 = (LinearLayout) findViewById(R.id.row3);
        row4 = (LinearLayout) findViewById(R.id.row4);
        row5 = (LinearLayout) findViewById(R.id.row5);
        row6 = (LinearLayout) findViewById(R.id.row6);
        row7 = (LinearLayout) findViewById(R.id.row7);
        row8 = (LinearLayout) findViewById(R.id.row8);
        row9 = (LinearLayout) findViewById(R.id.row9);
        row10 = (LinearLayout) findViewById(R.id.row10);
        row11 = (LinearLayout) findViewById(R.id.row11);
        row12 = (LinearLayout) findViewById(R.id.row12);
        row13 = (LinearLayout) findViewById(R.id.row13);
        row14 = (LinearLayout) findViewById(R.id.row14);
        row15 = (LinearLayout) findViewById(R.id.row15);
        row16 = (LinearLayout) findViewById(R.id.row16);
        row17 = (LinearLayout) findViewById(R.id.row17);
        row18 = (LinearLayout) findViewById(R.id.row18);
        row19 = (LinearLayout) findViewById(R.id.row19);
        row20 = (LinearLayout) findViewById(R.id.row20);

        rows = new ArrayList<LinearLayout>(Arrays.asList(row1, row2, row3, row4, row5, row6, row7, row8, row9, row10,
                row11, row12, row13, row14, row15, row16, row17, row18, row19, row20));

        for (LinearLayout row : rows) {

            if (rows.indexOf(row) != 1) {
                row.setVisibility(View.GONE);
            }
        }

        word1 = (EditText) findViewById(R.id.word1);
        word2 = (EditText) findViewById(R.id.word2);
        word3 = (EditText) findViewById(R.id.word3);
        word4 = (EditText) findViewById(R.id.word4);
        word5 = (EditText) findViewById(R.id.word5);
        word6 = (EditText) findViewById(R.id.word6);
        word7 = (EditText) findViewById(R.id.word7);
        word8 = (EditText) findViewById(R.id.word8);
        word9 = (EditText) findViewById(R.id.word9);
        word10 = (EditText) findViewById(R.id.word10);
        word11 = (EditText) findViewById(R.id.word11);
        word12 = (EditText) findViewById(R.id.word12);
        word13 = (EditText) findViewById(R.id.word13);
        word14 = (EditText) findViewById(R.id.word14);
        word15 = (EditText) findViewById(R.id.word15);
        word16 = (EditText) findViewById(R.id.word16);
        word17 = (EditText) findViewById(R.id.word17);
        word18 = (EditText) findViewById(R.id.word18);
        word19 = (EditText) findViewById(R.id.word19);
        word20 = (EditText) findViewById(R.id.word20);
        editTexts = new ArrayList<EditText>(Arrays.asList(word1, word2, word3, word4, word5, word6, word7, word8, word9, word10,
                word11, word12, word13, word14, word15, word16, word17, word18, word19, word20));


        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab5 = (FloatingActionButton) findViewById(R.id.fab5);
        fab6 = (FloatingActionButton) findViewById(R.id.fab6);
        fab7 = (FloatingActionButton) findViewById(R.id.fab7);
        fab8 = (FloatingActionButton) findViewById(R.id.fab8);
        fab9 = (FloatingActionButton) findViewById(R.id.fab9);
        fab10 = (FloatingActionButton) findViewById(R.id.fab10);
        fab11 = (FloatingActionButton) findViewById(R.id.fab11);
        fab12 = (FloatingActionButton) findViewById(R.id.fab12);
        fab13 = (FloatingActionButton) findViewById(R.id.fab13);
        fab14 = (FloatingActionButton) findViewById(R.id.fab14);
        fab15 = (FloatingActionButton) findViewById(R.id.fab15);
        fab16 = (FloatingActionButton) findViewById(R.id.fab16);
        fab17 = (FloatingActionButton) findViewById(R.id.fab17);
        fab18 = (FloatingActionButton) findViewById(R.id.fab18);
        fab19 = (FloatingActionButton) findViewById(R.id.fab19);
        fab20 = (FloatingActionButton) findViewById(R.id.fab20);

        fabs = new ArrayList<FloatingActionButton>(Arrays.asList(fab1, fab2, fab3, fab4, fab5, fab6, fab7, fab8, fab9, fab10,
                fab11, fab12, fab13, fab14, fab15, fab16, fab17, fab18, fab19, fab20));

        int num = 1;

        for (FloatingActionButton fab : fabs) {
            fab.setTag(R.id.status, "plus");
            fab.setTag(R.id.number, num);
            num++;
        }

    }
    public void readInFile(){


        InputStream in = null;
        allwords = new ArrayList<String>();
        try {
            in = getAssets().open(INPUT_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try{
            String line;
            while((line = reader.readLine()) != null){
                allwords.addAll(Arrays.asList(line.split("(?=[,.:;])|\\s")));

            }

        }catch (IOException e){
            e.printStackTrace();

        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public class WordCounter extends AsyncTask<String, Void, Void> {



        public WordCounter(){

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.setProgress(progressDialog.getProgress() + 1);
            if(progressDialog.getProgress() == progressDialog.getMax()){
                progressDialog.dismiss();
                Intent i = new Intent("com.example.joyrasmussen.myapplication.intent.action.WORDS");
                i.putExtra(COUNTER,textsForViews );
                startActivity(i);

            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected Void doInBackground(String... params) {
            if(matchCase){
                int count = Collections.frequency(allwords, params[0]);
                textsForViews.add(params[0] + ":  " + count);
            }else{
                int count = 0;
                for(String word: allwords){
                    if(word.equalsIgnoreCase(params[0])){
                        count++;
                    }
                }
                textsForViews.add(params[0] + ":  " + count);
            }
            return null;


        }
    }


}

