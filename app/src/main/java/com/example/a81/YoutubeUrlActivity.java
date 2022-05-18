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

        //link the variables to the ui elements
        playButton = findViewById(R.id.playButton);
        addToPlaylistButton = findViewById(R.id.addToPlaylistButton);
        myPlaylistButton = findViewById(R.id.myPlaylistButton);
        youtubeUrlTextEdit = findViewById(R.id.youtubeUrlTextEdit);

        //create the variable that allows connection to the database
        Database db = new Database(this);

        //set up the intents to allow swapping to other activities
        Intent playVideo = new Intent(this, VideoActivity.class);
        Intent showPlaylist = new Intent(this, PlaylistActivity.class);

        //on click event to swap to the play video activity
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
                //check that the input is not empty
                if (!youtubeUrlTextEdit.getText().toString().equals("")){
                    //split the input so that only the required section is sent to the database
                    String inputVideo = youtubeUrlTextEdit.getText().toString();
                    String[] split = inputVideo.split("/watch?v=");
                    //pull only the required section and insert it into the database table
                    db.InsertVideo(split[split.length-1]);
                }else{
                    //tell the user when input is empty
                    Toast.makeText(YoutubeUrlActivity.this, "Youtube video URL is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //on click event to swap to the playlist activity
        myPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(showPlaylist);
            }
        });
    }
}