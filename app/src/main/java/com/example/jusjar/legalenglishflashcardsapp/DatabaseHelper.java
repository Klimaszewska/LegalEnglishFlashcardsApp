package com.example.jusjar.legalenglishflashcardsapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class DatabaseHelper extends SQLiteAssetHelper {

    // database name and version
    private static final String DATABASE_NAME = "flashcardsDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // table names
    private static final String TABLE_NAME_COMMERCIAL = "commercialCode";
    private static final String TABLE_NAME_CIVIL = "civilCode";
    private static final String TABLE_NAME_LABOUR = "labourCode";
    // column names
    private static final String[] TABLE_COLUMNS = {"pl", "en"};

    // required constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<WordPairs> getCommercialCodeDbContent() {
        //getting the database reference
        SQLiteDatabase db = this.getReadableDatabase();

        //making the query
        Cursor cursor = db.query(TABLE_NAME_COMMERCIAL, new String[]{TABLE_COLUMNS[0], TABLE_COLUMNS[1]},  null, null, null, null, null);
        cursor.moveToFirst();

        // creating a list of the WordPairs type to store two Strings
        List<WordPairs> listOfWords = new ArrayList<WordPairs>();


        do {
            // setting the cursor on the colum with Polish words and English words, respectively
            String pl = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_COLUMNS[0]));
            String en = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_COLUMNS[1]));

            // creating a new WordPairs object and adding it to the list to be returned
            WordPairs wordPairs = new WordPairs(pl, en);
            listOfWords.add(wordPairs);
        }while (cursor.moveToNext());


        // closing the cursor
        cursor.close();

        //shuffling the list to get WordPairs in random order
        Collections.shuffle(listOfWords);

        // returning the listOfWords (of type WordPairs)
        return listOfWords;
    }

    // method to return the civilCode table content
    public List<WordPairs> getCivilCodeDatabaseContent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_CIVIL, new String[]{TABLE_COLUMNS[0], TABLE_COLUMNS[1]},  null, null, null, null, null);
        cursor.moveToFirst();
        List<WordPairs> listOfWords = new ArrayList<WordPairs>();
        do {
            String pl = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_COLUMNS[0]));
            String en = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_COLUMNS[1]));
            WordPairs wordPairs = new WordPairs(pl, en);
            listOfWords.add(wordPairs);
        }while (cursor.moveToNext());
        cursor.close();
        Collections.shuffle(listOfWords);
        return listOfWords;
    }

    // method to return the commercialCode table content
    public List<WordPairs> getLabourCodeDatabaseContent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_LABOUR, new String[]{TABLE_COLUMNS[0], TABLE_COLUMNS[1]},  null, null, null, null, null);
        cursor.moveToFirst();
        List<WordPairs> listOfWords = new ArrayList<WordPairs>();
        do {
            String pl = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_COLUMNS[0]));
            String en = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_COLUMNS[1]));
            WordPairs wordPairs = new WordPairs(pl, en);
            listOfWords.add(wordPairs);
        }while (cursor.moveToNext());
        cursor.close();
        Collections.shuffle(listOfWords);
        return listOfWords;
    }

}
