package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PairMatchingActivity extends AppCompatActivity {

    private static int MAX_PAIRS = 4;

    // declaring fields
    private LinearLayout leftColumn;
    private LinearLayout rightColumn;

    private TextView questionsTotalText;
    private int currentQuestion;
    private int totalQuestions;

    // field for the intent that sends the category name previously selected by the user
    private String category;

    private DatabaseHelper db;
    private List<WordPairs> wordPairsList;
    private List<WordPairs> redundantWordPairsList;
    private List<ButtonPair> buttonList;
    private List<View> viewList;

    // TO DO: Add more to words list. New method for updating. Until the words array is empty.

    private int counter = 0;

    private String temp;
    private String tempEn;

    private Button leftButtonClicked;
    private Button rightButtonClicked;
    private Button buttonPl;
    private Button buttonEn;

    private CompositeListener leftListener;
    private CompositeListener rightListener;


/*    // setting both listeners, for both columns
    private CompositeListener leftListener = new CompositeListener() {
        public void onClick(View v) {

            if (v instanceof Button) {
                leftButtonClicked = (Button) v;
                temp = (String) leftButtonClicked.getText();
                //Toast.makeText(PairMatchingActivity.this, "Polish", Toast.LENGTH_SHORT).show();

            }
            isMatchFound();
            updatePairMatching();
            //updatePairMatching();
        }
    };


    private CompositeListener rightListener = new CompositeListener() {
        public void onClick(View v) {

            if (v instanceof Button) {
                rightButtonClicked = (Button) v;
                //Toast.makeText(PairMatchingActivity.this, "English", Toast.LENGTH_SHORT).show();
                tempEn = (String) rightButtonClicked.getText();
            }
            isMatchFound();
            updatePairMatching();

            //updatePairMatching();
        }
    };*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_matching);


        // setting both listeners, for both columns
        leftListener = new CompositeListener() {
            public void onClick(View v) {

                if (v instanceof Button) {
                    leftButtonClicked = (Button) v;
                    temp = (String) leftButtonClicked.getText();
                }

                isMatchFound();

                if (counter == MAX_PAIRS+1){
                    updatePairMatching();
                }
            }
        };


        rightListener = new CompositeListener() {
            public void onClick(View v) {

                if (v instanceof Button) {
                    rightButtonClicked = (Button) v;
                    tempEn = (String) rightButtonClicked.getText();
                }

                isMatchFound();

                if (counter == MAX_PAIRS+1){
                    updatePairMatching();
                }
            }
        };

        //getting the category intent
        category = getIntent().getStringExtra("categorySelected");

        // database reference
        initializeDatabase();


        redundantWordPairsList = new ArrayList<>();

        // call to the UI for TextViews
        TextView introText = (TextView) findViewById(R.id.intro);
        questionsTotalText = (TextView) findViewById(R.id.questionsTotal);
        TextView methodAndModeText = (TextView) findViewById(R.id.methodAndMode);


        // setting TextViews
        currentQuestion = 1;
        totalQuestions = wordPairsList.size()/5;
        introText.setText(R.string.pairMatchingIntroText);
        methodAndModeText.setText(category);

        // setting both temps to null (for the isMatchFound method)
        temp = null;
        tempEn = null;

        // setting the layouts
        leftColumn = (LinearLayout)findViewById(R.id.left_buttons);
        rightColumn = (LinearLayout)findViewById(R.id.right_buttons);

        // method for setting the initial content
        initiatePairMatching();

    }

    // method for checking if the Polish word clicked matches the English word clicked (and vice versa)
    private boolean isMatchFound() {
        if (temp != null && tempEn != null){
            for (int i = 0; i<wordPairsList.size(); i++){
                // words.getWordPl and so on + use the equals method for Strings
                if (temp.equals(wordPairsList.get(i).getWordPl()) && tempEn.equals(wordPairsList.get(i).getWordEn())){
                    leftButtonClicked.setBackgroundColor(Color.GREEN);
                    rightButtonClicked.setBackgroundColor(Color.GREEN);
                    temp = null;
                    tempEn = null;
                    counter++;
                    return true;
                }
            }
            temp = null;
            tempEn = null;
        }
        return false;
    }

    // method for setting the initial content of the questionsTotalText TextView and both button columns
    private void initiatePairMatching(){
        questionsTotalText.setText(String.format(getResources().getString(R.string.pairMatchingTotalText), currentQuestion, totalQuestions));
        buttonList = new ArrayList<>();
        viewList = new ArrayList<>();

        // generating buttons
        for(int i=0; i<= MAX_PAIRS; i++) {
            buttonPl = new Button(this);
            buttonEn = new Button(this);

            buttonPl.setText(wordPairsList.get(i).getWordPl());
            buttonPl.setBackgroundColor(Color.rgb(179,229,252));
            buttonPl.setOnClickListener(leftListener);

            buttonEn.setText(wordPairsList.get(i).getWordEn());
            buttonEn.setBackgroundColor(Color.rgb(179,229,252));
            buttonEn.setOnClickListener(rightListener);

            buttonList.add(new ButtonPair(buttonPl, buttonEn));
            redundantWordPairsList.add(wordPairsList.get(i));
        }

        // assigning buttons to views and columns
        for (int i=0; i<=MAX_PAIRS; i++){
            viewList.add(buttonList.get(i).getButtonPl());
            rightColumn.addView(buttonList.get(i).getButtonEn());
        }

        //shuffling the left column buttons
        Collections.shuffle(viewList);

        // assigning buttons to the left column
        for (int i=0; i <= MAX_PAIRS; i++){
            leftColumn.addView(viewList.get(i));
        }
    }

    // method for updating both columns and establishing when to advance to the next screen
    private void updatePairMatching() {
        wordPairsList.removeAll(redundantWordPairsList);
        counter = 0;

        if (wordPairsList.size() >= MAX_PAIRS+1) {
            rightColumn.removeAllViews();
            leftColumn.removeAllViews();
            counter = 0;
            currentQuestion++;
            //questionsTotalText.setText(String.format(getResources().getString(R.string.pairMatchingTotalText), currentQuestion, totalQuestions));
            initiatePairMatching();
        } else {
            advance();
        }
    }

    //method for advancing to the next screen and transferring intent extras
    private void advance(){
        Intent i = new Intent(this, PairMatchingFinalScreenActivity.class);
        i.putExtra("categorySelected", category);
        startActivity(i);
    }

    // method for getting the content from the database
    private void initializeDatabase(){
        // initializing the database and getting content from the database
        db = new DatabaseHelper(this);

        //assigning database content to the words array
        // the passed intent is assigned to category and compared with button texts
        if (category.equals(String.format(getResources().getString(R.string.buttonCategory1)))){
            wordPairsList = db.getCivilCodeDatabaseContent();
        }else if (category.equals(String.format(getResources().getString(R.string.buttonCategory2)))){
            wordPairsList = db.getCommercialCodeDbContent();
        }else{
            wordPairsList = db.getLabourCodeDatabaseContent();
        }
    }




    // foreach, too loop through EN, PL without directly naming them
    // for, languages: ['en', 'pl'], (langue) =>
    // button = new Button();
    // button.setText(wordPairsList.get(i).getWord(language);

}


