package com.example.ayushaggarwal.mp3player;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class ArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        Button songs = (Button) findViewById(R.id.btn_songs);
        Button artists = (Button) findViewById(R.id.btn_artists);
        Button albums = (Button) findViewById(R.id.btn_albums);

        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songsIntent = new Intent(ArtistsActivity.this, SongsActivity.class);
                startActivity(songsIntent);
            }
        });

        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumsIntent = new Intent(ArtistsActivity.this, AlbumsActivity.class);
                startActivity(albumsIntent);
            }
        });

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ArtistsImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(ArtistsActivity.this, R.string.artists_toast_msg,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}