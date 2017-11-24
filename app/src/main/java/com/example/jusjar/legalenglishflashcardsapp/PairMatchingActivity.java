package com.example.jusjar.legalenglishflashcardsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PairMatchingActivity extends AppCompatActivity implements View.OnClickListener {

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

    private WordPairs[] words = new WordPairs[]{
            new WordPairs(R.string.pairMatching1, R.string.pairMatching2),
            new WordPairs(R.string.pairMatching3, R.string.pairMatching4),
            new WordPairs(R.string.pairMatching5, R.string.pairMatching6),
            new WordPairs(R.string.pairMatching7, R.string.pairMatching8),
            new WordPairs(R.string.pairMatching9, R.string.pairMatching10),
    };


    private int questionsTotal = 0;
    private int currentIndex = 0;

    private int[] wordsPl ={words[0].getPairMatchingPl(), words[1].getPairMatchingPl(), words[2].getPairMatchingPl(), words[3].getPairMatchingPl(), words[4].getPairMatchingPl()};
    private int[] wordsEn ={words[0].getPairMatchingEn(), words[1].getPairMatchingEn(), words[2].getPairMatchingEn(), words[3].getPairMatchingEn(), words[4].getPairMatchingEn()};


    private boolean matchFound = false;
    private int temp;
    private int tempEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_matching);

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

        setPairMatching();


        buttonPairMatching1.setOnClickListener(this);
        buttonPairMatching2.setOnClickListener(this);
        buttonPairMatching3.setOnClickListener(this);
        buttonPairMatching4.setOnClickListener(this);
        buttonPairMatching5.setOnClickListener(this);
        buttonPairMatching6.setOnClickListener(this);
        buttonPairMatching7.setOnClickListener(this);
        buttonPairMatching8.setOnClickListener(this);
        buttonPairMatching9.setOnClickListener(this);
        buttonPairMatching10.setOnClickListener(this);



        // TEMPORARY  - TO BE REMOVED - set OnClickListener for the test button
        buttonPairMatching10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PairMatchingFinalScreenActivity.class));
            }
        });
    }

    private void setPairMatching(){

        // Shuffling both columns. Works fine. But it's commented out until we get comparing the buttons to work properly.
        //shuffleArray(wordsPl);
        //shuffleArray(wordsEn);

        buttonPairMatching1.setText(wordsPl[0]);
        buttonPairMatching3.setText(wordsPl[1]);
        buttonPairMatching5.setText(wordsPl[2]);
        buttonPairMatching7.setText(wordsPl[3]);
        buttonPairMatching9.setText(wordsPl[4]);

        buttonPairMatching2.setText(wordsEn[0]);
        buttonPairMatching4.setText(wordsEn[1]);
        buttonPairMatching6.setText(wordsEn[2]);
        buttonPairMatching8.setText(wordsEn[3]);
        buttonPairMatching10.setText(wordsEn[4]);


// Commented out. Not useful now.
/*
        buttonPairMatching1.setText(words[0].getPairMatchingPl());
        buttonPairMatching3.setText(words[1].getPairMatchingPl());
        buttonPairMatching5.setText(words[2].getPairMatchingPl());
        buttonPairMatching7.setText(words[3].getPairMatchingPl());
        buttonPairMatching9.setText(words[4].getPairMatchingPl());

        buttonPairMatching2.setText(words[0].getPairMatchingEn());
        buttonPairMatching4.setText(words[1].getPairMatchingEn());
        buttonPairMatching6.setText(words[2].getPairMatchingEn());
        buttonPairMatching8.setText(words[3].getPairMatchingEn());
        buttonPairMatching10.setText(words[4].getPairMatchingEn());
*/


    }

    // method for checking if the word clicked (PL) matches the second word clicked (EN). Not finished yet.
    private boolean isMatchFound(){
        for (int i = 0; i<words.length; i++){
            if (temp == wordsPl[i] && tempEn == wordsEn[i]){
                return true;
            }
        }
        return false;
    }


    // method for shuffling arrays. Works fine. However, it's not used yet - it's commented out in lines 106 and 107.
    private static void shuffleArray(int[] array){
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
    }


    @Override
    public void onClick(View v) {

        if (v instanceof Button) {


            isMatchFound();

            if (isMatchFound()){
                v.setBackgroundColor(Color.GREEN);
            }else{
                Toast.makeText(PairMatchingActivity.this,"Try again!", Toast.LENGTH_SHORT).show();
            }
            // A problematic part. Just the beginning of a switch statement for all the buttons. Just button 1 and 2 for now.
            // I think I need a temp value for first button clicked. And a temp value for the second button clicked.
            // Then I need to check if the temp values match (if the user tapped a correct word pair).
            switch (v.getId()) {
                case R.id.buttonPairMatching1:
                    temp = wordsPl[0];
                    break;
                case R.id.buttonPairMatching2:
                    tempEn = wordsEn[0];
                    break;
                case R.id.buttonPairMatching3:
                    temp = wordsPl[1];
                    break;
                case R.id.buttonPairMatching4:
                    tempEn = wordsEn[1];
                    break;
            }
        }
    }
}
