package com.example.jusjar.legalenglishflashcardsapp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringDictionary {

    public String word;
    public int pairId;
    public Integer buttonId;

    // constructor
    public StringDictionary(String word, int pairId) {
        this.word = word;
        this.pairId = pairId;
    }

    public String getWord() {
        return word;
    }

    public int getPairId() {
        return pairId;
    }

    public Integer getButtonId() {
        return buttonId;
    }

    public void setButtonId(Integer buttonId) {
        this.buttonId = buttonId;
    }

    public boolean contains(Integer[] arr, Integer item){
        List<Integer> list = Arrays.asList(arr);
        Set<Integer> set = new HashSet<Integer>(list);
        return set.contains(item);
    }
}
