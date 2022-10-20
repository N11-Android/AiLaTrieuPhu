package com.example.androidbtl_db;

import static java.lang.Boolean.FALSE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String USER_TABLE = "USER_TABLE";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_SCORE = "USER_SCORE";

    private static final String QUESTION_TABLE = "QUESTION_TABLE";
    private static final String QUESTION_ID = "QUESTION_ID";
    private static final String QUESTION_CONTENT = "QUESTION_CONTENT";

    private static final String ANSWER_TABLE = "ANSWER_TABLE";
    private static final String ANSWER_ID = "ANSWER_ID";
    private static final String ANSWER_CONTENT = "ANSWER_CONTENT";
    private static final String IS_TRUE = "IS_TRUE";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "game.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT, " + USER_SCORE + " INTEGER)";
        String createQuestionTable = "CREATE TABLE " + QUESTION_TABLE + " ( " + QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION_CONTENT + " TEXT)";
        String createAnswerTable = "CREATE TABLE " + ANSWER_TABLE + " (" + ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ANSWER_CONTENT + " TEXT, " + IS_TRUE + " BOOL)";

        db.execSQL(createUserTable);
        db.execSQL(createQuestionTable);
        db.execSQL(createAnswerTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you need to add a column
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE ANSWER_TABLE  ADD COLUMN QUESTION_ID INTEGER Default 0");
        }
    }

    public void insertData(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues forUser = new ContentValues();
        ContentValues forQuestion = new ContentValues();
        ContentValues forAnswer = new ContentValues();

        forUser.put(USER_NAME, "Nghiep Hoang");
        forUser.put(USER_SCORE, 1000);
        db.insert(USER_TABLE, null, forUser);

        forQuestion.put(QUESTION_CONTENT, "HOW OLD ARE U?");
        db.insert(QUESTION_TABLE, null, forQuestion);

        forAnswer.put(ANSWER_CONTENT, "20");
        forAnswer.put(QUESTION_ID, 0);
        forAnswer.put(IS_TRUE, FALSE);
        db.insert(ANSWER_TABLE, null, forAnswer);
    }
}
