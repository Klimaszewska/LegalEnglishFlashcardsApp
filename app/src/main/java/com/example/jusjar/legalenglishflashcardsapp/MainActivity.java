package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // fields for the button that was clicked by the used and the text (String) of this button
    private Button categorySelected;
    private String category;

    // listener for proceeding to the next activity
    private View.OnClickListener openSecondScreenListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            categorySelected = (Button)v;
            category = categorySelected.getText().toString();

            // intent with extras passed to the next activity
            Intent intent = new Intent(v.getContext(), MainActivitySecondScreen.class);
            intent.putExtra("categorySelected", category);
            startActivity(intent);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);

        Button buttonCategory1 = (Button) findViewById(R.id.buttonCategory1);
        buttonCategory1.setOnClickListener(openSecondScreenListener);

        Button buttonCategory2 = (Button) findViewById(R.id.buttonCategory2);
        buttonCategory2.setOnClickListener(openSecondScreenListener);

        Button buttonCategory3 = (Button) findViewById(R.id.buttonCategory3);
        buttonCategory3.setOnClickListener(openSecondScreenListener);
    }

/*    protected void sendSelectedCategory(){
        Intent categoryIntent = new Intent(MainActivity.this, FlashCardsActivity.class);
        categoryIntent.putExtra("categorySelected", category);
        startActivity(categoryIntent);
    }*/
}
