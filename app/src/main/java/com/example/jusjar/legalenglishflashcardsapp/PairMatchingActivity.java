package com.example.jusjar.legalenglishflashcardsapp;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    private LinearLayout mainLayout;

    private LinearLayout leftColumn;
    private LinearLayout rightColumn;

    private TextView questionsTotalText;

    // TO DO: Change to a list of WordPairs.
    // random number 0 - 4 for the first left button
    private WordPairs[] words;

    private DatabaseHelper db;
    private List<WordPairs> wordPairsList;

    // TO DO: Add more to words list. New method for updating. Until the words array is empty.
    // TO DO: Change layout to columns instead of rows. Instead of global fields for buttons -> onCreate

    private int questionsTotal = 0;

    private String temp;
    private String tempEn;

    private Button leftButtonClicked;
    private Button rightButtonClicked;
    private Button buttonPl;
    private Button buttonEn;

    // setting both listeners, for both columns
    private CompositeListener leftListener = new CompositeListener() {
        public void onClick(View v) {

            if (v instanceof Button) {
                leftButtonClicked = (Button) v;
                temp = (String) leftButtonClicked.getText();
                Toast.makeText(PairMatchingActivity.this, "Polish", Toast.LENGTH_SHORT).show();

            }
            isMatchFound();
        }
    };



    private CompositeListener rightListener = new CompositeListener() {
        public void onClick(View v) {

            if (v instanceof Button) {
                rightButtonClicked = (Button) v;
                Toast.makeText(PairMatchingActivity.this, "English", Toast.LENGTH_SHORT).show();
                tempEn = (String) rightButtonClicked.getText();
            }
            isMatchFound();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_matching);


        words = new WordPairs[]{
                new WordPairs(getResources().getString(R.string.pairMatching1), getResources().getString(R.string.pairMatching2)),
                new WordPairs(getResources().getString(R.string.pairMatching3), getResources().getString(R.string.pairMatching4)),
                new WordPairs(getResources().getString(R.string.pairMatching5), getResources().getString(R.string.pairMatching6)),
                new WordPairs(getResources().getString(R.string.pairMatching7), getResources().getString(R.string.pairMatching8)),
                new WordPairs(getResources().getString(R.string.pairMatching9), getResources().getString(R.string.pairMatching10)),
        };

        wordPairsList = new ArrayList<>();
        wordPairsList.add(new WordPairs("Polskie 1", "Angielskie 1"));
        wordPairsList.add(new WordPairs("Polskie 2", "Angielskie 2"));
        wordPairsList.add(new WordPairs("Polskie 3", "Angielskie 3"));
        wordPairsList.add(new WordPairs("Polskie 4", "Angielskie 4"));
        wordPairsList.add(new WordPairs("Polskie 5", "Angielskie 5"));

        // call to the UI for intro text
        TextView introText = (TextView) findViewById(R.id.intro);
        questionsTotalText = (TextView) findViewById(R.id.questionsTotal);

        // call to the UI for the buttons
//        buttonPairMatching1 = (Button) findViewById(R.id.buttonPairMatching1);
//        buttonPairMatching2 = (Button) findViewById(R.id.buttonPairMatching2);
//        buttonPairMatching3 = (Button) findViewById(R.id.buttonPairMatching3);
//        buttonPairMatching4 = (Button) findViewById(R.id.buttonPairMatching4);
//        buttonPairMatching5 = (Button) findViewById(R.id.buttonPairMatching5);
//        buttonPairMatching6 = (Button) findViewById(R.id.buttonPairMatching6);
//        buttonPairMatching7 = (Button) findViewById(R.id.buttonPairMatching7);
//        buttonPairMatching8 = (Button) findViewById(R.id.buttonPairMatching8);
//        buttonPairMatching9 = (Button) findViewById(R.id.buttonPairMatching9);
//        buttonPairMatching10 = (Button) findViewById(R.id.buttonPairMatching10);

        // setting both temps to null
        temp = null;
        tempEn = null;

        setPairMatching();

//        buttonPairMatching1.setOnClickListener(leftListener);
//        buttonPairMatching2.setOnClickListener(rightListener);
//        buttonPairMatching3.setOnClickListener(leftListener);
//        buttonPairMatching4.setOnClickListener(rightListener);
//        buttonPairMatching5.setOnClickListener(leftListener);
//        buttonPairMatching6.setOnClickListener(rightListener);
//        buttonPairMatching7.setOnClickListener(leftListener);
//        buttonPairMatching8.setOnClickListener(rightListener);
//        buttonPairMatching9.setOnClickListener(leftListener);
//        buttonPairMatching10.setOnClickListener(rightListener);

        leftColumn = (LinearLayout)findViewById(R.id.left_buttons);
        rightColumn = (LinearLayout)findViewById(R.id.right_buttons);

        //words = db.getLabourCodeDatabaseContent().toArray(new WordPairs[0]);

        List<ButtonPair> buttonList = new ArrayList<>();

        for(int i=0; i<=4; i++) {
            buttonPl = new Button(this);
            buttonEn = new Button(this);

            //button.setText("ID: " + i);
            //if (i % 2 == 0) {
                //button.setText(words[i].getWordPl());
                buttonPl.setText(wordPairsList.get(i).getWordPl());
                buttonPl.setOnClickListener(leftListener);
                //leftColumn.addView(buttonPl);

            //} else {
                //button.setText(words[i].getWordEn());
                buttonEn.setText(wordPairsList.get(i).getWordEn());
                buttonEn.setOnClickListener(rightListener);
                //rightColumn.addView(buttonEn);
            //}
            buttonList.add(new ButtonPair(buttonPl, buttonEn));
            //buttonList.add(buttonPl);
            //buttonList.add(buttonEn);
        }

        //Collections.shuffle(buttonList);

        List<View> viewList = new ArrayList<>();

        for (int i = 0; i<=4; i++){
            viewList.add(buttonList.get(i).getButtonPl());
            //leftColumn.addView(buttonList.get(i).getButtonPl());
            rightColumn.addView(buttonList.get(i).getButtonEn());
        }

        Collections.shuffle(viewList);

        for (int i = 0; i <= 4; i++){
            leftColumn.addView(viewList.get(i));
        }


/*        for (int i = 0; i < 10; i++) {
            if (i%2==0) {
                leftColumn.addView(buttonList.get(i));
            } else {
                rightColumn.addView(buttonList.get(i));
            }
        }*/


/*        for(int i=0; i<10; i++) {
            if (i % 2 == 0) {
                rightColumn.addView(leftList.get(i));
            } else {
                leftColumn.addView(rightList.get(i));
            }
        }*/
    }

    private void setPairMatching() {

        //Setting buttons' texts
//
//        buttonPairMatching1.setTag(1);
//        buttonPairMatching2.setTag(1);
//

//        buttonPairMatching1.setText(R.string.pairMatching1);
//        buttonPairMatching3.setText(R.string.pairMatching3);
//        buttonPairMatching5.setText(R.string.pairMatching5);
//        buttonPairMatching7.setText(R.string.pairMatching7);
//        buttonPairMatching9.setText(R.string.pairMatching9);
//
//        buttonPairMatching2.setText(R.string.pairMatching2);
//        buttonPairMatching4.setText(R.string.pairMatching4);
//        buttonPairMatching6.setText(R.string.pairMatching6);
//        buttonPairMatching8.setText(R.string.pairMatching8);
//        buttonPairMatching10.setText(R.string.pairMatching10);
    }

    // method for checking if the word clicked (PL) matches the second word clicked (EN).


    private boolean isMatchFound() {
        if (temp != null && tempEn != null){

            for (int i = 0; i<words.length; i++){
                // words.getWordPl and so on + use the equals method for Strings
                if (temp.equals(wordPairsList.get(i).getWordPl()) && tempEn.equals(wordPairsList.get(i).getWordEn())){
                    leftButtonClicked.setBackgroundColor(Color.GREEN);
                    rightButtonClicked.setBackgroundColor(Color.CYAN);

/*                    if(leftButtonClicked.getTag() == rightButtonClicked.getTag()){
                        Toast.makeText(this, "Correct!",
                                Toast.LENGTH_LONG).show();
                    }*/

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



/*    private boolean isMatchFound() {
        if (temp != null && tempEn != null){

            for (int i = 0; i<words.length; i++){
                // words.getWordPl and so on + use the equals method for Strings
                if (temp.equals(words[i].getWordPl()) && tempEn.equals(words[i].getWordEn())){
                    leftButtonClicked.setBackgroundColor(Color.GREEN);
                    rightButtonClicked.setBackgroundColor(Color.CYAN);

*//*                    if(leftButtonClicked.getTag() == rightButtonClicked.getTag()){
                        Toast.makeText(this, "Correct!",
                                Toast.LENGTH_LONG).show();
                    }*//*

                    temp = null;
                    tempEn = null;
                    return true;
                }
            }
            temp = null;
            tempEn = null;
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


