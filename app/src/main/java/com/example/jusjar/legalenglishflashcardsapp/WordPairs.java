package com.example.jusjar.legalenglishflashcardsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WordPairs extends AppCompatActivity {

    // fields for words in Polish and words in English
    private String wordPl;
    private String wordEn;

    // contructor created
    public WordPairs(String wordPl, String wordEn) {
        this.wordPl = wordPl;
        this.wordEn = wordEn;
    }

    // getters
    public String getWordPl() {
        return wordPl;
    }

    public String getWordEn() {
        return wordEn;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_pairs);
    }
}
