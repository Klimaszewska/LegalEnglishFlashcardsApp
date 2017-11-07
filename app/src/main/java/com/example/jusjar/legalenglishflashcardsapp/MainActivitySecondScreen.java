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

        Button buttonPairMatching = (Button) findViewById(R.id.buttonPairMatching);
        buttonPairMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PairMatchingActivity.class));
            }
        });
    }
}
