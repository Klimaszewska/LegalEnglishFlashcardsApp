package com.example.jusjar.legalenglishflashcardsapp;


import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class ButtonPair extends AppCompatActivity {

    private Button buttonPl;
    private Button buttonEn;

    public ButtonPair(Button buttonPl, Button buttonEn) {
        this.buttonPl = buttonPl;
        this.buttonEn = buttonEn;
    }

    public Button getButtonPl() {
        return buttonPl;
    }

    public void setButtonPl(Button buttonPl) {
        this.buttonPl = buttonPl;
    }

    public Button getButtonEn() {
        return buttonEn;
    }

    public void setButtonEn(Button buttonEn) {
        this.buttonEn = buttonEn;
    }
}
