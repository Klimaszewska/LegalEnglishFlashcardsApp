package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlashCardsActivity extends AppCompatActivity {

    // creating database-related fields
    private DatabaseHelper db;


    // declaring fields
    private Button buttonCheck;
    private Button buttonCorrect;
    private Button buttonNotSure;
    private Button buttonWrong;

    private TextView wordInput;
    private TextView numberCorrectText;
    private TextView numberNotSureText;
    private TextView numberWrongText;
    private TextView questionsTotalText;
    private TextView methodAndModeText;

    // words array initialized as a global field
    private WordPairs[] words;

    private int currentIndex = 0;
    private int numberCorrect = 0;
    private int numberNotSure = 0;
    private int numberWrong = 0;
    private int questionsTotal = 0;

    boolean buttonCorrectClicked = false;
    boolean buttonNotSureClicked = false;
    boolean buttonWrongClicked = false;

    // field for the intent that sends the category name previously selected by the user
    private String category;

    // field for checking if user wants to have a Polish word input or an English word input
    private String selectedSourceLanguage;


    // field for setting the content of the wordInput textView (Polish word input or English word input)
    // not used for now - it was intended for the setSourceLanguage method, but the method does not work and is commented out for the time being.
    private String wordInputText;

    // field for setting the content of the Check button (Polish word input or English word input)
    // not used for now - it was intended for the setSourceLanguage method, but the method does not work and is commented out for the time being.
    private String buttonCheckText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        // method for initializing the database
        initializeDatabase();

        // assigning a variable for the word input TextView
        selectedSourceLanguage = getIntent().getStringExtra("wordInput");

        // commented out for now - the method is not working and it's commented out down below
        //setSourceLanguage();

        // call to the UI for sample word text view
        wordInput = (TextView) findViewById(R.id.wordInput);
        if (selectedSourceLanguage.equals("pl")){
            wordInput.setText(words[currentIndex].getWordPl());
        }else{
            wordInput.setText(words[currentIndex].getWordEn());
        }

        // call to the UI for score text views
        numberCorrectText = (TextView) findViewById(R.id.numberCorrect);
        numberNotSureText = (TextView) findViewById(R.id.numberNotSure);
        numberWrongText = (TextView) findViewById(R.id.numberWrong);
        questionsTotalText = (TextView) findViewById(R.id.questionsTotal);
        methodAndModeText = (TextView) findViewById(R.id.methodAndMode);
        methodAndModeText.setText(category);

        //String correctText = String.format(getResources().getString(R.string.numberCorrectText), numberCorrect, words.length);


        // call to the UI for the buttons
        buttonCorrect = (Button) findViewById(R.id.buttonCorrect);
        buttonNotSure = (Button) findViewById(R.id.buttonNotSure);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        buttonWrong = (Button) findViewById(R.id.buttonWrong);
        buttonCheck.setTag(0);

        // first run of the methods
        updateWordPair();
        initializeContent();


        // onCLickListener for the check button
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int status = (Integer) v.getTag();
                if (status == 1){
                    if (selectedSourceLanguage.equals("pl")){
                        buttonCheck.setText(words[currentIndex].getWordEn());
                    }else{
                        buttonCheck.setText(words[currentIndex].getWordPl());
                    }
                    v.setTag(0);
                }else{
                    buttonCheck.setText("Check");
                    v.setTag(1);
                }
            }
        });

        // onCLickListener for the correct button
        buttonCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // to do:
                // buttons appearing later
                // later on - making the layout more generic (e.g. for other devices)
                buttonCorrectClicked = true;

                if (currentIndex<words.length-1) {
                    currentIndex++;
                    updateWordPair();
                    setCounters();
                    buttonCorrectClicked = false;
                }else{
                    setCounters();
                    startActivity(new Intent(v.getContext(), FlashCardsFinalScreenActivity.class));
                    sendScore();
                }
            }
        });

        // onCLickListener for the not sure button
        buttonNotSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNotSureClicked = true;

                if (currentIndex<words.length-1) {
                    currentIndex++;
                    updateWordPair();
                    setCounters();
                    buttonNotSureClicked = false;
                }else{
                    setCounters();
                    startActivity(new Intent(v.getContext(), FlashCardsFinalScreenActivity.class));
                    sendScore();
                }
            }
        });

        // OnClickListener for the wrong button
        buttonWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonWrongClicked = true;

                if (currentIndex<words.length-1) {
                    currentIndex++;
                    updateWordPair();
                    setCounters();
                    buttonWrongClicked = false;
                }else{
                    setCounters();
                    startActivity(new Intent(v.getContext(), FlashCardsFinalScreenActivity.class));
                    sendScore();
                }
            }
        });
    }

    // TO DO: Figure out handling screen orientation
    // Commented out for now - refers to screen rotation.
/*    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null){
            wordInput.setText(savedInstanceState.getString("wordInput"));
            buttonCheck.setText(savedInstanceState.getString("buttonCheck"));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString("wordInput", wordInput.getText().toString());
        outState.putString("buttonCheck", buttonCheck.getText().toString());
    }*/

    private void updateWordPair(){
        //String question = words[currentIndex].getWordPl();
        //wordInput.setText(question);
        if (selectedSourceLanguage.equals("pl")){
            wordInput.setText(words[currentIndex].getWordPl());
        }else{
            wordInput.setText(words[currentIndex].getWordEn());
        }
        //wordInput.setText(wordInputText);

        //String answer = words[currentIndex].getWordEn();
        buttonCheck.setText("Check");
        buttonCheck.setTag(1);
    }

    private void initializeContent(){
        String correctText = String.format(getResources().getString(R.string.numberCorrectText), numberCorrect, words.length);
        numberCorrectText.setText(correctText);
        numberCorrect++;
        String notSureText = String.format(getResources().getString(R.string.numberNotSureText), numberNotSure, words.length);
        numberNotSureText.setText(notSureText);
        numberNotSure++;
        String wrongText = String.format(getResources().getString(R.string.numberWrongText), numberWrong, words.length);
        numberWrongText.setText(wrongText);
        numberWrong++;
        String totalText = String.format(getResources().getString(R.string.numberTotalText), questionsTotal, words.length);
        questionsTotalText.setText(totalText);
    }

    private void setCounters(){
        if (buttonCorrectClicked){
            String correctText = String.format(getResources().getString(R.string.numberCorrectText), numberCorrect, words.length);
            numberCorrectText.setText(correctText);
            numberCorrect++;

        }else if (buttonNotSureClicked){
            String notSureText = String.format(getResources().getString(R.string.numberNotSureText), numberNotSure, words.length);
            numberNotSureText.setText(notSureText);
            numberNotSure++;

        }else if (buttonWrongClicked){
            String wrongText = String.format(getResources().getString(R.string.numberWrongText), numberWrong, words.length);
            numberWrongText.setText(wrongText);
            numberWrong++;
        }

        questionsTotal++;
        String totalText = String.format(getResources().getString(R.string.numberTotalText), questionsTotal, words.length);
        questionsTotalText.setText(totalText);
    }

    // method for sending the score to the next activity
    private void sendScore(){
        Intent i = new Intent(FlashCardsActivity.this, FlashCardsFinalScreenActivity.class);
        String correctScore = numberCorrectText.getText().toString();
        String notSureScore = numberNotSureText.getText().toString();
        String wrongScore = numberWrongText.getText().toString();
        String questionsTotal = questionsTotalText.getText().toString();
        i.putExtra("correct", correctScore);
        i.putExtra("notSure", notSureScore);
        i.putExtra("wrong", wrongScore);
        i.putExtra("total", questionsTotal);
        i.putExtra("categorySelected", category);
        i.putExtra("wordInput", selectedSourceLanguage);
        startActivity(i);
    }

    // method for getting the content from the database
    private void initializeDatabase(){
        // initializing the database and getting content from the database
        db = new DatabaseHelper(this);

        //assigning database content to the words array
        // the passed intent is assigned to category and compared with button texts
        category = getIntent().getStringExtra("categorySelected");
        if (category.equals(String.format(getResources().getString(R.string.buttonCategory1)))){
            words = db.getCivilCodeDatabaseContent().toArray(new WordPairs[0]);

        }else if (category.equals(String.format(getResources().getString(R.string.buttonCategory2)))){
            words = db.getCommercialCodeDbContent().toArray(new WordPairs[0]);
        }else{
            words = db.getLabourCodeDatabaseContent().toArray(new WordPairs[0]);
        }
    }


    // method for stating which language the user selected as the source language. The method does not work and is commented out for the time being.
/*    private void setSourceLanguage(){

        if (selectedSourceLanguage.equals("pl")){
            wordInputText = words[currentIndex].getWordPl();
            buttonCheckText = words[currentIndex].getWordEn();

        }else{
            wordInputText = words[currentIndex].getWordEn();
            buttonCheckText = words[currentIndex].getWordPl();

        }
    }*/
}
