package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FlashCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        // TEMPORARY - TO BE REMOVED - test button to proceed to the next screen
        Button buttonWrong = (Button) findViewById(R.id.buttonWrong);


        // TEMPORARY  - TO BE REMOVED - set OnClickListener for the test button
        buttonWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), FlashCardsFinalScreenActivity.class));
            }
        });
    }
}
