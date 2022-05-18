package com.example.a81.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a81.model.Account;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "Youtube_Player_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//this method runs on creation
        String CREATE_LOGIN_TABLE= "CREATE TABLE LOGIN(LOGINID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT, FULL_NAME TEXT)";
        String CREATE_YOUTUBE_VIDEO_TABLE= "CREATE TABLE YOUTUBE_VIDEO(VIDEOID INTEGER PRIMARY KEY AUTOINCREMENT, VIDEO TEXT)";
        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_YOUTUBE_VIDEO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_LOGIN_TABLE = "DROP TABLE IF EXISTS LOGIN";
        String DROP_YOUTUBE_VIDEO_TABLE = "DROP TABLE IF EXISTS YOUTUBE_VIDEO";
        sqLiteDatabase.execSQL(DROP_LOGIN_TABLE);
        sqLiteDatabase.execSQL(DROP_YOUTUBE_VIDEO_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long InsertVideo(String video){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("VIDEO", video);
        long row = db.insert("YOUTUBE_VIDEO",null, Values);
        db.close();
        return row;
    }

    public ArrayList<String> FetchAllVideos(){
        ArrayList<String> VideoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_ALL_YOUTUBE_VIDEO = "SELECT * FROM YOUTUBE_VIDEO";
        Cursor cursor = db.rawQuery(SELECT_ALL_YOUTUBE_VIDEO, null);
        if (cursor.moveToFirst()){
            do {
                String temp = cursor.getString(1);
                VideoList.add(temp);
            }while(cursor.moveToNext());
        }
        db.close();
        return VideoList;
    }

    public ArrayList<String> FetchAllVideosFull(){
        ArrayList<String> VideoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_ALL_YOUTUBE_VIDEO = "SELECT * FROM YOUTUBE_VIDEO";
        Cursor cursor = db.rawQuery(SELECT_ALL_YOUTUBE_VIDEO, null);
        if (cursor.moveToFirst()){
            do {
                String temp = "https://www.youtube.com/watch?v="+cursor.getString(1);
                VideoList.add(temp);
            }while(cursor.moveToNext());
        }
        db.close();
        return VideoList;
    }

    public long InsertLogin(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("USERNAME", account.getUsername());
        Values.put("PASSWORD", account.getPassword());
        Values.put("FULL_NAME", account.getFullName());
        long row = db.insert("LOGIN",null, Values);
        db.close();
        return row;
    }

    public ArrayList<Account> FetchAllLogins() {
        ArrayList<Account> LoginList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_ALL_LOGIN = "SELECT * FROM LOGIN";
        Cursor cursor = db.rawQuery(SELECT_ALL_LOGIN, null);
        if (cursor.moveToFirst()) {
            do {
                Account temp = new Account(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                LoginList.add(temp);
            } while (cursor.moveToNext());
        }
        db.close();
        return LoginList;
    }
}
