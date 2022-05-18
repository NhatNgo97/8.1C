package com.example.a81;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a81.db.Database;

public class YoutubeUrlActivity extends AppCompatActivity {

    Button playButton, addToPlaylistButton, myPlaylistButton;
    EditText youtubeUrlTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_url);

        playButton = findViewById(R.id.playButton);
        addToPlaylistButton = findViewById(R.id.addToPlaylistButton);
        myPlaylistButton = findViewById(R.id.myPlaylistButton);
        youtubeUrlTextEdit = findViewById(R.id.youtubeUrlTextEdit);

        Database db = new Database(this);

        Intent playVideo = new Intent(this, VideoActivity.class);
        Intent showPlaylist = new Intent(this, PlaylistActivity.class);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url  = youtubeUrlTextEdit.getText().toString();
                playVideo.putExtra("url", url);
                startActivity(playVideo);
                Toast.makeText(YoutubeUrlActivity.this, "Play click", Toast.LENGTH_SHORT).show();

            }
        });

        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!youtubeUrlTextEdit.getText().toString().equals("")){
                    String inputVideo = youtubeUrlTextEdit.getText().toString();
                    String[] split = inputVideo.split("/watch?v=");
                    db.InsertVideo(split[split.length-1]);
                }else{
                    Toast.makeText(YoutubeUrlActivity.this, "Youtube video URL is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(showPlaylist);
            }
        });
    }
}