package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FlashCardsActivity extends AppCompatActivity {

    // fields

    private Button buttonCheck;
    private Button buttonCorrect;
    private TextView wordInput;

    private WordPairs[] words = new WordPairs[]{
        new WordPairs(R.string.wordInput1, "Sample EN 1"),
        new WordPairs(R.string.wordInput2, "Sample EN 2"),
        new WordPairs(R.string.wordInput3, "Sample EN 3")
    };

    private int currentIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);



        // call to the UI for textview
        wordInput = (TextView) findViewById(R.id.wordInput);
        wordInput.setText(words[currentIndex].getWordPl());

        // call to the UI for the buttons
        buttonCorrect = (Button) findViewById(R.id.buttonCorrect);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        buttonCheck.setTag(0);

        updateWordPair();

        // onCLickListener for the correct button
        buttonCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if ; else -> second screen of Flashcards
                // combine correct with changing status
                // add numbers to texts (e.g. correct answer number)
                // buttons appearing later
                // later on - making the layout more generic (e.g. for other devices)

                if (currentIndex<words.length-1) {
                    currentIndex++;
                    updateWordPair();


                }else{
                    Toast.makeText(FlashCardsActivity.this, "Voila!", Toast.LENGTH_LONG).show();
                }
            }
        });

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

                //buttonCheck.setText(words[currentIndex].getWordEn());
            }
        });


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

    private void updateWordPair(){
        int question = words[currentIndex].getWordPl();
        wordInput.setText(question);

        //String answer = words[currentIndex].getWordEn();
        buttonCheck.setText("Check");
        buttonCheck.setTag(1);

    }
}
