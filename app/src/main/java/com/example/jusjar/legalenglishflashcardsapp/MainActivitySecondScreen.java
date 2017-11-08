package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivitySecondScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second_screen);

        // set the buttons
        Button buttonPairMatching = (Button) findViewById(R.id.buttonPairMatching);
        Button buttonFlashcardsPl = (Button) findViewById(R.id.buttonFlashcardsPl);
        Button buttonFlashcardsEn = (Button) findViewById(R.id.buttonFlashcardsEn);

        // set OnClickListener for the buttons
        buttonPairMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PairMatchingActivity.class));
            }
        });

        buttonFlashcardsPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), FlashCardsActivity.class));
            }
        });

        buttonFlashcardsEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), FlashCardsActivity.class));
            }
        });
    }
}
