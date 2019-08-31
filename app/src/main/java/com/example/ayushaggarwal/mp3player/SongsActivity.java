package com.example.ayushaggarwal.mp3player;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume.
                // Pause playback and reset player to the start of the file. That way, we can
                // play the song from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        Button song = (Button) findViewById(R.id.btn_songs);
        Button artists = (Button) findViewById(R.id.btn_artists);
        Button albums = (Button) findViewById(R.id.btn_albums);

        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artistsIntent = new Intent(SongsActivity.this, ArtistsActivity.class);
                startActivity(artistsIntent);
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumsIntent = new Intent(SongsActivity.this, AlbumsActivity.class);
                startActivity(albumsIntent);
            }
        });

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        //Create a list of Songs
        final ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song(getString(R.string.silence), getString(R.string.marshmello_ft_khalid), R.drawable.silence, R.raw.marshmello_silence));
        songs.add(new Song(getString(R.string.whatever_it_takes), getString(R.string.imagine_dragons), R.drawable.whatever_takes, R.raw.whatever_it_takes));
        songs.add(new Song(getString(R.string.havana), getString(R.string.camila_cabello), R.drawable.havana, R.raw.havana));
        songs.add(new Song(getString(R.string.fireflies), getString(R.string.owl_city), R.drawable.fireflies, R.raw.fireflies));
        songs.add(new Song(getString(R.string.hall_of_fame), getString(R.string.the_script), R.drawable.hall_of_fame, R.raw.hall_of_fame));
        songs.add(new Song(getString(R.string.without_you), getString(R.string.avicii_sandro), R.drawable.without_you, R.raw.without_you));
        songs.add(new Song(getString(R.string.just_the_way_you_are), getString(R.string.bruno_mars), R.drawable.way_you_are, R.raw.way_you_are));
        songs.add(new Song(getString(R.string.sky_full_of_stars), getString(R.string.coldplay), R.drawable.sky_full_of_stars, R.raw.sky_full_of_stars));
        songs.add(new Song(getString(R.string.superheroes), getString(R.string.the_script), R.drawable.superheroes, R.raw.superheroes));
        songs.add(new Song(getString(R.string.maps), getString(R.string.maroon_five), R.drawable.maps, R.raw.maps));
        songs.add(new Song(getString(R.string.stereo_hearts), getString(R.string.adam_levine), R.drawable.stereo_hearts, R.raw.stereo_hearts));
        songs.add(new Song(getString(R.string.ritual), getString(R.string.marshmello), R.drawable.ritual, R.raw.ritual));
        songs.add(new Song(getString(R.string.wake_me_up), getString(R.string.avicii), R.drawable.wake_me_up, R.raw.wake_me_up));

        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list.
        ListView listView = (ListView) this.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link SongAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Song} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);

                // Release the media player if it currently exists because we are about to
                // play a different sound file.
                releaseMediaPlayer();

                // Request audio focus so in order to play the audio file.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current song
                    mMediaPlayer = MediaPlayer.create(getBaseContext(), song.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}