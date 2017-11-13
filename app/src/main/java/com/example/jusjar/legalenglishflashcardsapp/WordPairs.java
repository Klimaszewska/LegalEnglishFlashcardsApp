package com.example.jusjar.legalenglishflashcardsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WordPairs extends AppCompatActivity {

    // fields for words in Polish and words in English
    private int wordPl;
    private String wordEn;

    // contructor created
    public WordPairs(int wordPl, String wordEn) {
        this.wordPl = wordPl;
        this.wordEn = wordEn;
    }

    // getters and setters
    public int getWordPl() {
        return wordPl;
    }

    public void setWordPl(int wordPl) {
        this.wordPl = wordPl;
    }

    public String getWordEn() {
        return wordEn;
    }

    public void setWordEn(String wordEn) {
        this.wordEn = wordEn;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_pairs);
    }
}
