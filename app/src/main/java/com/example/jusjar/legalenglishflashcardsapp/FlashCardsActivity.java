package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.content.res.Resources;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FlashCardsActivity extends AppCompatActivity {

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

    private WordPairs[] words = new WordPairs[]{
        new WordPairs(R.string.wordInput1, "Sample EN 1"),
        new WordPairs(R.string.wordInput2, "Sample EN 2"),
        new WordPairs(R.string.wordInput3, "Sample EN 3")
    };

    private int currentIndex = 0;
    private int numberCorrect = 0;
    private int numberNotSure = 0;
    private int numberWrong = 0;
    private int questionsTotal = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);



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
        setCorrectCounter();
        setNotSureCounter();
        setWrongCounter();
        setQuestionsTotalCounter();

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

                if (currentIndex<words.length-1) {
                    currentIndex++;
                    updateWordPair();
                    setCorrectCounter();
                    setQuestionsTotalCounter();
                }else{
                    setCorrectCounter();
                    setQuestionsTotalCounter();
                    startActivity(new Intent(v.getContext(), FlashCardsFinalScreenActivity.class));
                    sendScore();
                }
            }
        });

        // onCLickListener for the not sure button
        buttonNotSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentIndex<words.length-1) {
                    currentIndex++;
                    updateWordPair();
                    setNotSureCounter();
                    setQuestionsTotalCounter();
                }else{
                    setNotSureCounter();
                    setQuestionsTotalCounter();
                    startActivity(new Intent(v.getContext(), FlashCardsFinalScreenActivity.class));
                    sendScore();
                }
            }
        });

        // OnClickListener for the wrong button
        buttonWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex<words.length-1) {
                    currentIndex++;
                    updateWordPair();
                    setWrongCounter();
                    setQuestionsTotalCounter();
                }else{
                    setWrongCounter();
                    setQuestionsTotalCounter();
                    startActivity(new Intent(v.getContext(), FlashCardsFinalScreenActivity.class));
                    sendScore();
                }
            }
        });
    }

    private void updateWordPair(){
        int question = words[currentIndex].getWordPl();
        wordInput.setText(question);

        //String answer = words[currentIndex].getWordEn();
        buttonCheck.setText("Check");
        buttonCheck.setTag(1);

    }

    private void setCorrectCounter(){
        numberCorrectText.setText("Correct: " + numberCorrect + "/" + words.length);
        numberCorrect++;
    }

    private void setNotSureCounter(){
        numberNotSureText.setText("Not sure: " + numberNotSure + "/" + words.length);
        numberNotSure++;
    }

    private void setWrongCounter(){
        numberWrongText.setText("Wrong: " + numberWrong + "/" + words.length);
        numberWrong++;
    }

    private void setQuestionsTotalCounter(){
        questionsTotalText.setText("Question: " + questionsTotal + "/" + words.length);
        questionsTotal++;
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
