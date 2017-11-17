package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FlashCardsFinalScreenActivity extends AppCompatActivity {

    // declaring the fields
    TextView numberCorrectText;
    TextView numberNotSureText;
    TextView numberWrongText;
    TextView questionsTotalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards_final_screen);

        // intent-related strings for displaying the score
        String totalScore = getIntent().getStringExtra("total");
        String correctScore = getIntent().getStringExtra("correct");
        String notSureScore = getIntent().getStringExtra("notSure");
        String wrongScore = getIntent().getStringExtra("wrong");

        // call to the UI for score text views
        questionsTotalText = (TextView) findViewById(R.id.questionsTotal);
        numberCorrectText = (TextView) findViewById(R.id.numberCorrect);
        numberNotSureText = (TextView) findViewById(R.id.numberNotSure);
        numberWrongText= (TextView) findViewById(R.id.numberWrong);

        // setting text for the score text views
        questionsTotalText.setText(totalScore);
        numberCorrectText.setText(correctScore);
        numberNotSureText.setText(notSureScore);
        numberWrongText.setText(wrongScore);

        // call to the UI for the buttons
        Button buttonRetry = (Button) findViewById(R.id.buttonRetry);
        Button buttonMainMenu = (Button) findViewById(R.id.buttonMainMenu);


        // set OnClickListener for the buttons
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), FlashCardsActivity.class));
            }
        });

        buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainActivity.class));
            }
        });

    }
}
