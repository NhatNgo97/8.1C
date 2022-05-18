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
        //create the queries for creating the tales in the database
        String CREATE_LOGIN_TABLE= "CREATE TABLE LOGIN(LOGINID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT, FULL_NAME TEXT)";
        String CREATE_YOUTUBE_VIDEO_TABLE= "CREATE TABLE YOUTUBE_VIDEO(VIDEOID INTEGER PRIMARY KEY AUTOINCREMENT, VIDEO TEXT)";
        //execute the queries and create the tables
        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_YOUTUBE_VIDEO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //create the queries to delete the tables
        String DROP_LOGIN_TABLE = "DROP TABLE IF EXISTS LOGIN";
        String DROP_YOUTUBE_VIDEO_TABLE = "DROP TABLE IF EXISTS YOUTUBE_VIDEO";
        //execute the queries to delete the tables
        sqLiteDatabase.execSQL(DROP_LOGIN_TABLE);
        sqLiteDatabase.execSQL(DROP_YOUTUBE_VIDEO_TABLE);
        //call the onCreate method to recreate the tables
        onCreate(sqLiteDatabase);
    }

    public long InsertVideo(String video){
        //create connection to database
        SQLiteDatabase db = this.getWritableDatabase();
        //create a query to insert a video string into the video table
        ContentValues Values = new ContentValues();
        Values.put("VIDEO", video);
        //execute the video insert query
        long row = db.insert("YOUTUBE_VIDEO",null, Values);
        //close the connection
        db.close();
        return row;
    }

    public ArrayList<String> FetchAllVideos(){
        //prepare an arraylist to store all the video strings
        ArrayList<String> VideoList = new ArrayList<>();
        //create a connection to the database
        SQLiteDatabase db = this.getReadableDatabase();
        //create a query to fetch all the video strings
        String SELECT_ALL_YOUTUBE_VIDEO = "SELECT * FROM YOUTUBE_VIDEO";
        //execute the query
        Cursor cursor = db.rawQuery(SELECT_ALL_YOUTUBE_VIDEO, null);
        if (cursor.moveToFirst()){
            do {
                //move the video strings into a temporary string
                String temp = cursor.getString(1);
                //add that temporary string into the arraylist
                VideoList.add(temp);
            }while(cursor.moveToNext());
        }
        //close the connection
        db.close();
        return VideoList;
    }

    public ArrayList<String> FetchAllVideosFull(){
        //prepare an arraylist to store all the video strings
        ArrayList<String> VideoList = new ArrayList<>();
        //create a connection to the database
        SQLiteDatabase db = this.getReadableDatabase();
        //create a query to fetch all the video strings
        String SELECT_ALL_YOUTUBE_VIDEO = "SELECT * FROM YOUTUBE_VIDEO";
        //execute the query
        Cursor cursor = db.rawQuery(SELECT_ALL_YOUTUBE_VIDEO, null);
        if (cursor.moveToFirst()){
            do {
                //move the video strings into a temporary string
                String temp = "https://www.youtube.com/watch?v="+cursor.getString(1);
                //add that temporary string into the arraylist
                VideoList.add(temp);
            }while(cursor.moveToNext());
        }
        //close the connection
        db.close();
        return VideoList;
    }

    public long InsertLogin(Account account){
        //create a connection to the database
        SQLiteDatabase db = this.getWritableDatabase();
        //create the query to insert the login data
        ContentValues Values = new ContentValues();
        Values.put("USERNAME", account.getUsername());
        Values.put("PASSWORD", account.getPassword());
        Values.put("FULL_NAME", account.getFullName());
        //execute the query
        long row = db.insert("LOGIN",null, Values);
        //close the connection
        db.close();
        return row;
    }

    public ArrayList<Account> FetchAllLogins() {
        //create an arraylist to store the login data
        ArrayList<Account> LoginList = new ArrayList<>();
        //create a connection to the database
        SQLiteDatabase db = this.getReadableDatabase();
        //create the query to get the login data from the database
        String SELECT_ALL_LOGIN = "SELECT * FROM LOGIN";
        //execute the query
        Cursor cursor = db.rawQuery(SELECT_ALL_LOGIN, null);
        if (cursor.moveToFirst()) {
            do {
                //move the login data into a temporary login variable
                Account temp = new Account(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                //add the login variable to the arraylist
                LoginList.add(temp);
            } while (cursor.moveToNext());
        }
        //close the connection
        db.close();
        return LoginList;
    }
}
