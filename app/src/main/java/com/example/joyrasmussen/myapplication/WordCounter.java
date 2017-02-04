package com.example.joyrasmussen.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by joyrasmussen on 2/4/17.
 */

public class WordCounter extends AsyncTask<String, Void, Integer> {
    private final static String INPUT_FILE = "textfile.txt";
    private ArrayList<String> allwords;


    public WordCounter(Context context){
        InputStream in = null;
        allwords = new ArrayList<String>();
        try {
            in = context.getAssets().open(INPUT_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try{
            String line;
            while((line = reader.readLine()) != null){
                allwords.addAll(Arrays.asList(line.split("(?=[,.])|\\\\s+")));

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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Integer doInBackground(String... params) {
      if(params[2].equals("TRUE")){
          return  Collections.frequency(allwords, params[0]);
      }else{
          int count = 0;
         for(String words: allwords){
             if(words.equalsIgnoreCase(params[0])){
                 count++;
             }
         }
         return count;
      }
    }
}
