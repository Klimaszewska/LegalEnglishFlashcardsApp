package com.example.jusjar.legalenglishflashcardsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class PairMatchingActivity extends AppCompatActivity {

    // declaring fields
    private Button buttonPairMatching1;
    private Button buttonPairMatching2;
    private Button buttonPairMatching3;
    private Button buttonPairMatching4;
    private Button buttonPairMatching5;
    private Button buttonPairMatching6;
    private Button buttonPairMatching7;
    private Button buttonPairMatching8;
    private Button buttonPairMatching9;
    private Button buttonPairMatching10;

    private TextView questionsTotalText;

    // Change to a list of WordPairs.
    // random number 0 - 4 for the first left button


    // words array left here for now. I'm trying to use an ArrayList instead of it. To be removed later.
    private WordPairs[] words;



    // newly added - add an ArrayList so as to use Collections later on.
    // It takes the StringDictionary type (nested class, down below)
/*
    ArrayList<StringDictionary> objPl = new ArrayList<StringDictionary>();
    SparseArray<StringDictionary> objEn = new SparseArray<>();
    ArrayList<StringDictionary> tmpObjEn = new ArrayList<StringDictionary>();
*/

    // TO DO: Add more to words list. New method for updating. Until the words array is empty.
    // TO DO: Change layout to columns instead of rows. Instead of global fields for buttons -> onCreate

    private int questionsTotal = 0;


    private String temp;
    private String tempEn;


    // newly added - two Integers added
/*    private Integer tmpPl;
    private Integer tmpEn;*/


    private Button leftButtonClicked;
    private Button rightButtonClicked;


    // setting both listeners, for both columns
    private CompositeListener leftListener = new CompositeListener() {
        public void onClick(View v) {

            if (v instanceof Button) {
                leftButtonClicked = (Button) v;
                temp = (String) leftButtonClicked.getText();
            }
            isMatchFound();
        }
    };
    private CompositeListener rightListener = new CompositeListener() {
        public void onClick(View v) {

            if (v instanceof Button) {
                rightButtonClicked = (Button) v;
                tempEn = (String) rightButtonClicked.getText();
            }
            isMatchFound();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_matching);

        // constructor
        words = new WordPairs[]{
                new WordPairs(getResources().getString(R.string.pairMatching1), getResources().getString(R.string.pairMatching2)),
                new WordPairs(getResources().getString(R.string.pairMatching3), getResources().getString(R.string.pairMatching4)),
                new WordPairs(getResources().getString(R.string.pairMatching5), getResources().getString(R.string.pairMatching6)),
                new WordPairs(getResources().getString(R.string.pairMatching7), getResources().getString(R.string.pairMatching8)),
                new WordPairs(getResources().getString(R.string.pairMatching9), getResources().getString(R.string.pairMatching10)),
        };

        // call to the UI for intro text
        TextView introText = (TextView) findViewById(R.id.intro);
        questionsTotalText = (TextView) findViewById(R.id.questionsTotal);

        // call to the UI for the buttons
        buttonPairMatching1 = (Button) findViewById(R.id.buttonPairMatching1);
        buttonPairMatching2 = (Button) findViewById(R.id.buttonPairMatching2);
        buttonPairMatching3 = (Button) findViewById(R.id.buttonPairMatching3);
        buttonPairMatching4 = (Button) findViewById(R.id.buttonPairMatching4);
        buttonPairMatching5 = (Button) findViewById(R.id.buttonPairMatching5);
        buttonPairMatching6 = (Button) findViewById(R.id.buttonPairMatching6);
        buttonPairMatching7 = (Button) findViewById(R.id.buttonPairMatching7);
        buttonPairMatching8 = (Button) findViewById(R.id.buttonPairMatching8);
        buttonPairMatching9 = (Button) findViewById(R.id.buttonPairMatching9);
        buttonPairMatching10 = (Button) findViewById(R.id.buttonPairMatching10);

        // setting both temps to null
        temp = null;
        tempEn = null;

        setPairMatching();

        buttonPairMatching1.setOnClickListener(leftListener);
        buttonPairMatching2.setOnClickListener(rightListener);
        buttonPairMatching3.setOnClickListener(leftListener);
        buttonPairMatching4.setOnClickListener(rightListener);
        buttonPairMatching5.setOnClickListener(leftListener);
        buttonPairMatching6.setOnClickListener(rightListener);
        buttonPairMatching7.setOnClickListener(leftListener);
        buttonPairMatching8.setOnClickListener(rightListener);
        buttonPairMatching9.setOnClickListener(leftListener);
        buttonPairMatching10.setOnClickListener(rightListener);


        // TEMPORARY  - TO BE REMOVED - set OnClickListener for the test button
        buttonPairMatching10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PairMatchingFinalScreenActivity.class));
            }
        });
    }

    private void setPairMatching() {

        //Setting buttons' texts
        buttonPairMatching1.setText(R.string.pairMatching1);
        buttonPairMatching3.setText(R.string.pairMatching3);
        buttonPairMatching5.setText(R.string.pairMatching5);
        buttonPairMatching7.setText(R.string.pairMatching7);
        buttonPairMatching9.setText(R.string.pairMatching9);

        buttonPairMatching2.setText(R.string.pairMatching2);
        buttonPairMatching4.setText(R.string.pairMatching4);
        buttonPairMatching6.setText(R.string.pairMatching6);
        buttonPairMatching8.setText(R.string.pairMatching8);
        buttonPairMatching10.setText(R.string.pairMatching10);
    }

    // method for checking if the word clicked (PL) matches the second word clicked (EN).
    private boolean isMatchFound() {
        if (temp != null && tempEn != null){

            for (int i = 0; i<words.length; i++){
                // words.getWordPl and so on + use the equals method for Strings
                if (temp.equals(words[i].getWordPl()) && tempEn.equals(words[i].getWordEn())){
                    leftButtonClicked.setBackgroundColor(Color.GREEN);
                    rightButtonClicked.setBackgroundColor(Color.CYAN);
                    temp = null;
                    tempEn = null;
                    return true;
                }
            }
            temp = null;
            tempEn = null;
        }
        return false;
    }


/*    public static void shuffle(WordPairs[] array){
        int x = array.length;

        for (int i = 0; i < x; i++){
            int s = i + (int)(Math.random()*(x-i));
            WordPairs temp = array[s];
            array[s] = array[i];
            array[i] = temp;
        }
    }*/


    // method for shuffling arrays. Does not work.

/*    private static void shuffleArray(WordPairs[] array){
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }*/

}


