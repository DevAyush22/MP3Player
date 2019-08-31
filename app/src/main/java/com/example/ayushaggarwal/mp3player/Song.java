package com.example.ayushaggarwal.mp3player;

/**
 * {@link Song} represents a song details that is visible to user.
 * It contains an image, name and description for that song.
 */
class Song {

    /**
     * Name of song
     */
    private String mSongName;

    /**
     * Description of song
     */
    private String mSongDesc;

    /**
     * Image resource ID for the song
     */
    private int mImageResourceId;

    /**
     * Audio resource ID for the song
     */
    private int mAudioResourceId;


    /**
     * Create a new Song object.
     *
     * @param SongName        is the name of song
     * @param SongDesc        is the description of the song
     * @param imageResourceId is the drawable resource id for the image associated
     * @param audioResourceId is the resource id for the audio file associated with the song
     */
    Song(String SongName, String SongDesc, int imageResourceId, int audioResourceId) {

        mSongName = SongName;
        mSongDesc = SongDesc;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }


    /**
     * Get the name of song.
     */
    String getSongName() {
        return mSongName;
    }

    /**
     * Get the description of song.
     */
    String getSongDesc() {
        return mSongDesc;
    }

    /**
     * Return the image resource ID of the song.
     */
    int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Return the audio resource ID of the song.
     */
    int getAudioResourceId() {
        return mAudioResourceId;
    }

}