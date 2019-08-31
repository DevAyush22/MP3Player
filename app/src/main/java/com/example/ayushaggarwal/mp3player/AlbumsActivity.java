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

public class AlbumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        Button songs = (Button) findViewById(R.id.btn_songs);
        Button artists = (Button) findViewById(R.id.btn_artists);
        Button albums = (Button) findViewById(R.id.btn_albums);

        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songsIntent = new Intent(AlbumsActivity.this, SongsActivity.class);
                startActivity(songsIntent);
            }
        });

        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artistsIntent = new Intent(AlbumsActivity.this, ArtistsActivity.class);
                startActivity(artistsIntent);
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new AlbumsImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(AlbumsActivity.this, R.string.albums_toast_msg,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}