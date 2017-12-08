package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;

public class FlashCardsActivity extends AppCompatActivity {

    // creating database-related fields
    private DatabaseHelper db;
    private Cursor wordsCursor;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        // calling the constructor of DatabaseHelper class. Commented out - it crashes the app.
       // db = new DatabaseHelper(this);
        // wordsCursor = db.getDbContent();

        words = new WordPairs[]{
                // have a look at it! getString
                new WordPairs(getResources().getString(R.string.wordInput1), "Sample EN 1"),
                new WordPairs(getResources().getString(R.string.wordInput2), "Sample EN 2"),
                new WordPairs(getResources().getString(R.string.wordInput3), "Sample EN 3")
        };


        // call to the UI for sample word text view
        wordInput = (TextView) findViewById(R.id.wordInput);
        wordInput.setText(words[currentIndex].getWordPl());

        // call to the UI for score text views
        numberCorrectText = (TextView) findViewById(R.id.numberCorrect);
        numberNotSureText = (TextView) findViewById(R.id.numberNotSure);
        numberWrongText = (TextView) findViewById(R.id.numberWrong);
        questionsTotalText = (TextView) findViewById(R.id.questionsTotal);

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
                //final int status = (Integer) v.getTag();
                final int status = (Integer) v.getTag();
                if (status == 1){
                    buttonCheck.setText(words[currentIndex].getWordEn());
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
                    //setCorrectCounter();
                    //setQuestionsTotalCounter();
                    buttonCorrectClicked = false;
                }else{

                    //setCorrectCounter();
                    //setQuestionsTotalCounter();
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
                    //setNotSureCounter();
                    setCounters();
                    //setQuestionsTotalCounter();
                    buttonNotSureClicked = false;
                }else{

                    //setNotSureCounter();
                    //setQuestionsTotalCounter();
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
                    //setWrongCounter();
                    setCounters();
                    //setQuestionsTotalCounter();
                    buttonWrongClicked = false;
                }else{

                    //setWrongCounter();
                    //setQuestionsTotalCounter();
                    setCounters();
                    startActivity(new Intent(v.getContext(), FlashCardsFinalScreenActivity.class));

                    sendScore();
                }
            }
        });
    }

    private void updateWordPair(){
        String question = words[currentIndex].getWordPl();
        wordInput.setText(question);

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
        startActivity(i);
    }
}
