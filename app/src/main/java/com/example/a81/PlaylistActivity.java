package com.example.a81;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a81.db.Database;

public class PlaylistActivity extends AppCompatActivity {

    ListView listViewPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        listViewPlaylist = findViewById(R.id.ListViewPlaylist);

        Database db = new Database(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, db.FetchAllVideosFull());
        listViewPlaylist.setAdapter(adapter);
    }
}