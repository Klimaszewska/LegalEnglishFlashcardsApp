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


    // words array left here for now. I'm trying to use an ArrayList instead of it. To be removed later.
    private WordPairs[] words;

    // newly added - add an ArrayList so as to use Collections later on.
    // It takes the StringDictionary type (nested class, down below)
    ArrayList<StringDictionary> objPl = new ArrayList<StringDictionary>();
    SparseArray<StringDictionary> objEn = new SparseArray<>();
    ArrayList<StringDictionary> tmpObjEn = new ArrayList<StringDictionary>();

    // TO DO: Add more to words list. New method for updating. Until the words array is empty.
    // TO DO: Change layout to columns instead of rows. Instead of global fields for buttons -> onCreate

    private int questionsTotal = 0;


    private String temp;
    private String tempEn;

    // newly added - two Integers added
    private Integer tmpPl;
    private Integer tmpEn;

    private Button leftButtonClicked;
    private Button rightButtonClicked;


    // setting both listeners, for both columns
    private CompositeListener leftListener = new CompositeListener() {
        public void onClick(View v) {

            if (v instanceof Button) {
                leftButtonClicked = (Button) v;
                temp = (String) leftButtonClicked.getText();

                // newly added
                final int size = objPl.size();
                for (int i = 0; i < size; i++) {
                    StringDictionary tmp = objPl.get(i);
                    if (tmp.getButtonId() == leftButtonClicked.getId()) {
                        tmpPl = tmp.getPairId();
                    }
                }
            }

            isMatchFound();
        }
    };
    private CompositeListener rightListener = new CompositeListener() {
        public void onClick(View v) {

            if (v instanceof Button) {
                rightButtonClicked = (Button) v;
                tempEn = (String) rightButtonClicked.getText();

                // newly added
                final int size = tmpObjEn.size();
                for (int i = 0; i < size; i++) {
                    StringDictionary tmp = tmpObjEn.get(i);
                    if (tmp.getButtonId() == rightButtonClicked.getId()) {
                        tmpEn = tmp.getPairId();
                    }
                }
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

        // newly added. Objects from the new constructor added to their respective ArrayLists
        objPl.add(new StringDictionary(getResources().getString(R.string.pairMatching1), 1));
        objPl.add(new StringDictionary(getResources().getString(R.string.pairMatching3), 2));
        objPl.add(new StringDictionary(getResources().getString(R.string.pairMatching5), 3));
        objPl.add(new StringDictionary(getResources().getString(R.string.pairMatching7), 4));
        objPl.add(new StringDictionary(getResources().getString(R.string.pairMatching9), 5));
/*        objPl.add(new StringDictionary("Polish 6", 6));
        objPl.add(new StringDictionary("Polish 7", 7));
        objPl.add(new StringDictionary("Polish 8", 8));
        objPl.add(new StringDictionary("Polish 9", 9));
        objPl.add(new StringDictionary("Polish 10", 10));*/

        objEn.put(1, new StringDictionary(getResources().getString(R.string.pairMatching2), 1));
        objEn.put(2, new StringDictionary(getResources().getString(R.string.pairMatching4), 2));
        objEn.put(3, new StringDictionary(getResources().getString(R.string.pairMatching6), 3));
        objEn.put(4, new StringDictionary(getResources().getString(R.string.pairMatching8), 4));
        objEn.put(5, new StringDictionary(getResources().getString(R.string.pairMatching10), 5));
/*        objEn.put(new StringDictionary("English 6", 6));
        objEn.add(new StringDictionary("English 7", 7));
        objEn.add(new StringDictionary("English 8", 8));
        objEn.add(new StringDictionary("English 9", 9));
        objEn.add(new StringDictionary("English 10", 10));*/


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

        // shuffle the left column
        Collections.shuffle(objPl);

        // setting up the left column
        tmpObjEn.clear();
        tmpObjEn.add(objEn.get(objPl.get(0).getPairId()));
        buttonPairMatching1.setText(objPl.get(0).getWord());
        objPl.get(0).setButtonId(buttonPairMatching1.getId());
        tmpObjEn.add(objEn.get(objPl.get(1).getPairId()));

        buttonPairMatching3.setText(objPl.get(1).getWord());
        objPl.get(1).setButtonId(buttonPairMatching3.getId());
        tmpObjEn.add(objEn.get(objPl.get(2).getPairId()));

        buttonPairMatching5.setText(objPl.get(2).getWord());
        objPl.get(2).setButtonId(buttonPairMatching5.getId());
        tmpObjEn.add(objEn.get(objPl.get(3).getPairId()));

        buttonPairMatching7.setText(objPl.get(3).getWord());
        objPl.get(3).setButtonId(buttonPairMatching7.getId());
        tmpObjEn.add(objEn.get(objPl.get(4).getPairId()));

        buttonPairMatching9.setText(objPl.get(4).getWord());
        objPl.get(4).setButtonId(buttonPairMatching9.getId());

        //shuffle the right column
        Collections.shuffle(tmpObjEn);

        // setting up the right column
        buttonPairMatching2.setText(tmpObjEn.get(0).getWord());
        tmpObjEn.get(0).setButtonId(buttonPairMatching2.getId());
        buttonPairMatching4.setText(tmpObjEn.get(1).getWord());
        tmpObjEn.get(1).setButtonId(buttonPairMatching4.getId());
        buttonPairMatching6.setText(tmpObjEn.get(2).getWord());
        tmpObjEn.get(2).setButtonId(buttonPairMatching6.getId());
        buttonPairMatching8.setText(tmpObjEn.get(3).getWord());
        tmpObjEn.get(3).setButtonId(buttonPairMatching8.getId());
        buttonPairMatching10.setText(tmpObjEn.get(4).getWord());
        tmpObjEn.get(4).setButtonId(buttonPairMatching10.getId());

        // Previous version for setting buttons' texts
/*        buttonPairMatching1.setText(R.string.pairMatching1);
        buttonPairMatching3.setText(R.string.pairMatching3);
        buttonPairMatching5.setText(R.string.pairMatching5);
        buttonPairMatching7.setText(R.string.pairMatching7);
        buttonPairMatching9.setText(R.string.pairMatching9);

        buttonPairMatching2.setText(R.string.pairMatching2);
        buttonPairMatching4.setText(R.string.pairMatching4);
        buttonPairMatching6.setText(R.string.pairMatching6);
        buttonPairMatching8.setText(R.string.pairMatching8);
        buttonPairMatching10.setText(R.string.pairMatching10);*/
    }

    // method for checking if the word clicked (PL) matches the second word clicked (EN). Not finished yet.
    // in isMatchFound method look if both temps are set
    // and reset them if the match is indeed found

    private boolean isMatchFound() {
        if (tmpPl != null && tmpEn != null) {

            if (tmpPl.equals(tmpEn)) {
                leftButtonClicked.setBackgroundColor(Color.GREEN);
                rightButtonClicked.setBackgroundColor(Color.CYAN);
                tmpPl = null;
                tmpEn = null;
                return true;
            }
            tmpPl = null;
            tmpEn = null;
        }
        return false;
    }


// PREVIOUS VERSION COMMENTED OUT
/*        if (temp != null && tempEn != null){

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
        }
        return false;
    }*/


/*    public static void shuffle(WordPairs[] array){
        int x = array.length;

        for (int i = 0; i < x; i++){
            int s = i + (int)(Math.random()*(x-i));
            WordPairs temp = array[s];
            array[s] = array[i];
            array[i] = temp;
        }
    }*/

    // method for shuffling arrays. Works fine. However, it's not used yet - it's commented out in lines 106 and 107.
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

    public class StringDictionary {
        // fields of the nested class
        private String word;
        private int pairId;
        private Integer buttonId;

        // constructor
        public StringDictionary(String word, int pairId) {
            this.word = word;
            this.pairId = pairId;
        }

        public String getWord() {
            return word;
        }

        public int getPairId() {
            return pairId;
        }

        public Integer getButtonId() {
            return buttonId;
        }

        public void setButtonId(Integer buttonId) {
            this.buttonId = buttonId;
        }


    }
}


