package com.example.jusjar.legalenglishflashcardsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WordPairs extends AppCompatActivity {

    // fields for words in Polish and words in English
    private String wordPl;
    private String wordEn;

    private int pairMatchingPl;
    private int pairMatchingEn;


    // contructor created
    public WordPairs(String wordPl, String wordEn) {
        this.wordPl = wordPl;
        this.wordEn = wordEn;
    }

    // getters and setters
    public String getWordPl() {
        return wordPl;
    }

    public void setWordPl(String wordPl) {
        this.wordPl = wordPl;
    }

    public String getWordEn() {
        return wordEn;
    }

    public void setWordEn(String wordEn) {
        this.wordEn = wordEn;
    }

    // Remove this construcotr. Use the previous one.
/*    public WordPairs(int pairMatchingPl, int pairMatchingEn) {
        this.pairMatchingPl = pairMatchingPl;
        this.pairMatchingEn = pairMatchingEn;
    }*/

    public int getPairMatchingPl() {
        return pairMatchingPl;
    }

    public void setPairMatchingPl(int pairMatchingPl) {
        this.pairMatchingPl = pairMatchingPl;
    }

    public int getPairMatchingEn() {
        return pairMatchingEn;
    }

    public void setPairMatchingEn(int pairMatchingEn) {
        this.pairMatchingEn = pairMatchingEn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_pairs);
    }
}
