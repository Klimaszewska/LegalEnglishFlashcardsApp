package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FlashCardsFinalScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards_final_screen);

        // set the buttons
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
