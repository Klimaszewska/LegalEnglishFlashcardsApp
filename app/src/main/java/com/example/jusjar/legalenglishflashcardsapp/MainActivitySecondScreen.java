package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivitySecondScreen extends AppCompatActivity {

    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second_screen);

        category = getIntent().getStringExtra("categorySelected");

        // set the buttons
        Button buttonPairMatching = (Button) findViewById(R.id.buttonPairMatching);
        Button buttonFlashcardsPl = (Button) findViewById(R.id.buttonFlashcardsPl);
        Button buttonFlashcardsEn = (Button) findViewById(R.id.buttonFlashcardsEn);


        // listener to proceed to the PairMatchingActivity
        buttonPairMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PairMatchingActivity.class));
            }
        });

        // listener to proceed to FlashCardsActivity
        // includes intent with extras (the category selected by the user)
        // TO DO: extras with info whether the user chose PL -> EN option or EN -> PL option
        buttonFlashcardsPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FlashCardsActivity.class);
                intent.putExtra("categorySelected", category);
                intent.putExtra("wordInput", "pl");
                startActivity(intent);
            }
        });

        // listener to proceed to FlashCardsActivity
        // includes intent with extras (the category selected by the user)
        // TO DO: extras with info whether the user chose PL -> EN option or EN -> PL option
        buttonFlashcardsEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FlashCardsActivity.class);
                intent.putExtra("categorySelected", category);
                intent.putExtra("wordInput", "en");
                startActivity(intent);
            }
        });
    }
}
