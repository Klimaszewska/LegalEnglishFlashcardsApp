package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);

        Button buttonCategoryOne = (Button) findViewById(R.id.buttonCategory1);

        buttonCategoryOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityScreenTwo.class);
                startActivity(intent);
            }
        });

    }


/*    public void configureButtonLearningMethod (){

        buttonNextScreen.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivityScreenTwo.class));
           }
        });
        Intent displayScreenTwo = new Intent(this, MainActivityScreenTwo.class);
        startActivity(displayScreenTwo);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
